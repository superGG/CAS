$(document).ready(function(){
  showUser();
})
$(window).resize(function(){
    if ($("#loginFrame").length>0) {
      setFrameWH("loginFrame") ;
    }
    if ($("#mask").length>0) {
      setMaskWH();
    }
});
/*登录页面内容*/
function showMask() {
    if ($("#mask").length>0) {
      $("#mask").remove();
    }
    var maskStr = "<div id='mask'></div>";
    $("body").append(maskStr);
    setMaskWH();
}
//显示登录面板
function showLoginFrame() {
    showMask();
    if ($("#loginFrame").length>0) {
      $("#loginFrame").remove();
    }
    var formStr = "<div id='loginFrame'><div class='hd'><a class='close'></a><div id='login-tab' class='tab-active'>登录</div><div id='register-tab'>注册</div></div>";
    formStr+="<form id='loginForm' class='' method='POST'><div class='login-tip'></div>";
    formStr+="<div class='form-input'><label class='input-icon icon-account'></label><input name='account' class='input' type='text' maxlength='16' placeholder='账号'></div>"
    formStr+="<div class='form-input'><label class='input-icon icon-password'></label><input name='password' class='input' type='password' maxlength='16' placeholder='密码'></div>";
    formStr+="<div class='form-input login-input-verifiCode'><label class='input-icon icon-verifiCode'></label><input name='verifyCode' class='input input-verifiCode' type='text' maxlength='4' placeholder='验证码'><img src='' id='verifiImg' title='点击换一张'></div>";
    formStr+="<div id='login-btn'>登录</div></form>";
    formStr+="<form id='registerForm' class='' method='POST'><div class='register-tip'></div>";
    formStr+="<div class='form-input'><label class='input-icon icon-account'></label><input name='account' class='input' type='text' maxlength='16' placeholder='账号:至少6位'></div>"
    formStr+="<div class='form-input'><label class='input-icon icon-password'></label><input name='password' class='input' type='password' maxlength='16' placeholder='密码:至少6位'></div>"
    formStr+="<div class='form-input'><label class='input-icon icon-password'></label><input name='password2' class='input' type='password' placeholder='再次输入密码'></div>"
    formStr+="<div class='form-input login-input-verifiCode'><label class='input-icon icon-verifiCode'></label><input name='verifyCode' class='input input-verifiCode' type='text' maxlength='4' placeholder='验证码'><img src='' id='rVerifiImg' title='点击换一张'></div>";
    formStr+="<div id='register-btn'>注册</div></form></div>";
    $("body").append(formStr);
    setFrameWH("loginFrame");
    initLoginFrameBtn();
    getCheckCode("verifiImg");
}
// 显示注册
function showRegisterFrame(){
  showLoginFrame();
  $("#register-tab").click();
}
//设置遮幕宽高
function setMaskWH() {
  $("#mask").width($(document).width()).height($(document).height());
}
//设置弹出面板宽高
function setFrameWH(frameId) {
   $("#"+frameId).css({"left":($(window).width()- $("#"+frameId).width())/2,"top":($(window).height()- $("#"+frameId).height())/2})
}

// 页面按钮点击事件
function initLoginFrameBtn(){
  $(".close").click(function() {
    $("#loginFrame").remove();
    $("#mask").remove();
  });
  $("#login-tab").click(function () {
    $("#register-tab").removeClass("tab-active");
    $("#login-tab").addClass("tab-active");
    $("#loginForm").show();
    $("#registerForm").hide();
    getCheckCode("verifiImg");
  });
  $("#register-tab").click(function () {
    $("#login-tab").removeClass("tab-active");
    $("#register-tab").addClass("tab-active");
    $("#loginForm").hide();
    $("#registerForm").show();
    getCheckCode("rVerifiImg");
  });
  $("#verifiImg").click(function () {
    getCheckCode("verifiImg");
  });
  $("#rVerifiImg").click(function () {
    getCheckCode("rVerifiImg");
  });
  $("#login-btn").click(function () {
    $("#loginForm .login-tip").html("");
    if (verifyLoginData()) {
      login();
    }
  });
  $("#register-btn").click(function () {
    if(verifyRegisterData()){
      register();
    }
  });
}

//获取验证码
function getCheckCode(id) {
  // var url ="/ClubSystem/users/getImgVerifyCode";
  // $.get(url,function (data) {
  //   $("#"+id).attr("src","/ClubSystem/"+data.resultParm.path);
  // });
  $.ajax({url:"/ClubSystem/users/getImgVerifyCode",async:false,type:'GET',success:function (data) {
    $("#"+id).attr("src","/ClubSystem/"+data.resultParm.path);
  }});
}

//校验登陆数据
function verifyLoginData() {
  var account=$("#loginFrame .form-input input[name='account']");
  var psw = $("#loginFrame .form-input input[name='password']");
  var verifyCode=$("#loginFrame .form-input input[name='verifyCode']");
  var pattern = new RegExp("[`~!\\s@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");

  if (pattern.test(account.val())) {
    account.focus();
    $("#loginForm .login-tip").html("×用户名位不能含有空格或特殊符号");
    return false;
  }
  if(account.val().trim().length<6|| account.val().trim().length > 16){
    account.focus();
    $("#loginForm .login-tip").html("×用户名位6-16字符");
    return false;
  }
  if (psw.val().length<6) {
    psw.focus();
    $("#loginForm .login-tip").html("×密码至少6位");
    return false;
  }
  if (verifyCode.val().replace(" ","").length<4) {
    verifyCode.focus();
    $("#loginForm .login-tip").html("×请输入4位验证码");
    return false;
  }
  return true;
}
//校验注册数据
function verifyRegisterData() {
  var account=$("#registerForm .form-input input[name='account']");
  var psw = $("#registerForm .form-input input[name='password']");
  var psw2 = $("#registerForm .form-input input[name='password2']");
  var verifyCode=$("#registerForm .form-input input[name='verifyCode']");
  var pattern = new RegExp("[`~!\\s@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");

  if (pattern.test(account.val())) {
    account.focus();
    $("#registerForm .register-tip").html("×用户名位不能含有空格或特殊符号");
    return false;
  }
  if(account.val().trim().length<6|| account.val().trim().length > 16){
    account.focus();
    $("#registerForm .register-tip").html("×用户名位6-16字符");
    return false;
  }
  if (psw.val().length<6) {
    psw.focus();
    $("#registerForm .register-tip").html("×密码至少6位");
    return false;
  }
  if(psw.val()!=psw2.val()){
    psw2.focus();
    $("#registerForm .register-tip").html("×两次密码不一致");
    return false;
  }
  if (verifyCode.val().replace(" ","").length<4) {
    verifyCode.focus();
    $("#registerForm .register-tip").html("×请输入4位验证码");
    return false;
  }
  return true;
}
//登录提交
function login() {
  var url ="/ClubSystem/users/login";
  var sendData = $("#loginForm").serialize();
  $.post(url,sendData,function (data) {
    if (data.serviceResult) {
    	var userDatail = data.resultParm.userDetail;
    	setCookieUserData(userDatail);
    	location.reload();
    }else{
      $("#loginForm .login-tip").html(data.resultInfo);
    }
  });
}
//退出登录
function loginOut(){
	delectCookieUserData();
  // location.reload();
  window.location.href="/ClubSystem/views/index.html";
}

//注册提交
function register() {
  var url ="/ClubSystem/users/register";
  var sendData = $("#registerForm").serialize();
  $.post(url,sendData,function (data) {
    alert(data.resultInfo);
    // location.reload();
    if(data.serviceResult){ 
      $("#login-tab").click();
    }
  });
}

//显示登录用户
function showUser(){
  var userData =getCookieUserData();
  if (userData!=null) {
    var str = "<div class='userImg'><img src='/ClubSystem"+userData.headPath+"'></div>";
    str += "<div class='userName'>"+userData.name+"</div>";
    str += "<div class='list'><li><a href='/ClubSystem/views/user/personalcenter.html?"+userData.name+"'>个人中心</a></li>";
    str +="<li><a href='javaScript:loginOut()'>退出</a></li></div>"
    $(".top .login").addClass("user").removeClass("login").html(str);
  }
}
//删除Cookie
function delectCookieUserData(){
	//获取当前时间 
	  var date=new Date(); 
	  //将date设置为过去的时间 
	  date.setTime(date.getTime()-3600*1000);
	  //将userId这个cookie删除 
	  var oldCookie = document.cookie;
	  document.cookie=oldCookie+"; path=/ClubSystem/; expires="+date.toGMTString();
}
//获取登录Cookie
function getCookieUserData() {
  var strCookie = document.cookie;
  var arrCookie = strCookie.split("; ");
  var userData ;
  for (var i = 0; i < arrCookie.length; i++) {
    var arr = arrCookie[i].split("=");
    if (arr[0]=="USER") {
      userData = arr[1];
      return JSON.parse(unescape(userData));
    }
  }
}

//设置Cookie
function setCookieUserData(data){
	var date=new Date(); 
    date.setTime(date.getTime()+3600*1000); 
    var mCookies = JSON.stringify(data);
    document.cookie="USER="+escape(mCookies)+"; path=/ClubSystem/; expires="+date.toGMTString();
}

//替换转义
function html_encode(str) {
    var s = "";
    if (str.length == 0)
        return "";
    s=str.replace(/%/g,"[p];");
    s=s.replace(/&/g,"[a];");
    s=s.replace(/\+/g,"[ad];");
    return s;
}
//替换转义
function html_decode(str) {
  var s = "";
  if (str.length == 0) {
    return "";
  }
  s = str.replace(/&lt;/g, "<");
  s = s.replace(/&gt;/g, ">");
  s=s.replace(/\[p\];/g,"%");
    s=s.replace(/\[a\];/g,"&");
    s=s.replace(/\[ad\];/g,"+");
  return s;
}
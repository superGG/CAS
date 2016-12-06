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
    formStr+="<form id='loginForm' class='' method='POST'>";
    formStr+="<div class='form-input'><label class='input-icon icon-account'></label><input name='account' class='input' type='text' placeholder='账号'></div>"
    formStr+="<div class='form-input'><label class='input-icon icon-password'></label><input name='password' class='input' type='password' placeholder='密码'></div>";
    formStr+="<div class='form-input login-input-verifiCode'><label class='input-icon icon-verifiCode'></label><input name='verifyCode' class='input input-verifiCode' type='text' maxlength='4' placeholder='验证码'><img src='../../verifyCode/N2Qz.jpg' id='verifiImg' title='点击换一张'></div>";
    formStr+="<div id='login-btn'>登录</div></form>";
    formStr+="<form id='registerForm' class='' method='POST'>";
    formStr+="<div class='form-input'><label class='input-icon icon-account'></label><input name='account' class='input' type='text' placeholder='账号'></div>"
    formStr+="<div class='form-input'><label class='input-icon icon-password'></label><input name='password' class='input' type='password' placeholder='密码'></div>"
    formStr+="<div class='form-input'><label class='input-icon icon-password'></label><input name='password2' class='input' type='password' placeholder='再次输入密码'></div>"
    formStr+="<div class='form-input login-input-verifiCode'><label class='input-icon icon-verifiCode'></label><input name='verifyCode' class='input input-verifiCode' type='text' maxlength='4' placeholder='验证码'><img src='../../verifyCode/N2Qz.jpg' id='rVerifiImg' title='点击换一张'></div>";
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
    $("#loginForm .login-tip").remove();
    login();
  });
  $("#register-btn").click(function () {
    register();
  });
}

//获取验证码
function getCheckCode(id) {
  var url ="/ClubSystem/users/getImgVerifyCode";
  $.get(url,function (data) {
    $("#"+id).attr("src","/ClubSystem/"+data.resultParm.path);
  });
}

//登录提交
function login() {
  var url ="/ClubSystem/users/login";
  var sendData = $("#loginForm").serialize();
  $.post(url,sendData,function (data) {
    console.log(data.serviceResult);
    if (data.serviceResult) {
      var date=new Date(); 
      date.setTime(date.getTime()+1800*1000); 
      var mCookies = JSON.stringify(data.resultParm.userDetail);
      document.cookie="USER="+escape(mCookies)+"; path=/ClubSystem/; expires="+date.toGMTString();
      location.reload();
    }else{
      $("#loginForm").prepend("<div class='login-tip'>"+data.resultInfo+"</div>");
    }
  });
}
//退出登录
function loginOut(){
  //获取当前时间 
  var date=new Date(); 
  //将date设置为过去的时间 
  date.setTime(date.getTime()-1800*1000);
  //将userId这个cookie删除 
  var oldCookie = document.cookie;
  document.cookie=oldCookie+"; path=/ClubSystem/; expires="+date.toGMTString();
  location.reload();
}

//注册提交
function register() {
  var url ="/ClubSystem/users/register";
  var sendData = $("#registerForm").serialize();
  console.log(sendData);
  $.post(url,sendData,function (data) {
    alert(data.resultInfo);
    location.reload();
  });
}

//显示登录用户
function showUser(){
  var userData =getCookieUserData();
  if (userData!=null) {
    var str = "<div class='userImg'><img src='/ClubSystem"+userData.headPath+"'></div>";
    str += "<div class='userName'>"+userData.name+"</div>";
    str += "<div class='list'><li><a href='user/personalcenter.html?"+userData.name+"'>个人中心</a></li>";
    str +="<li><a href='javaScript:loginOut()'>退出</a></li></div>"
    $(".top .login").addClass("user").removeClass("login").html(str);
  }
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
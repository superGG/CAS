$(document).ready(function(){

})
$(window).resize(function(){
    if ($("#loginFrame").length>0) {
      setFrameWH("loginFrame") ;
    }
    if ($("#registerFrame").length>0) {
      setFrameWH("registerFrame");
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
function showLoginFrame() {
    showMask();
    if ($("#loginFrame").length>0 ||$("#registerFrame").length>0) {
      $("#loginFrame").remove();
      $("#registerFrame").remove();
    }
    var formStr = "<div id='loginFrame'><div class='hd'><a class='close'></a><div id='login-tab' class='tab-active'>登录</div><div id='register-tab'>注册</div></div>";
    formStr+="<form id='loginForm' class='' method='POST'>";
    formStr+="<div class='form-input'><label class='input-icon icon-account'></label><input name='account' class='input' type='text' placeholder='账号'></div>"
    formStr+="<div class='form-input'><label class='input-icon icon-password'></label><input name='password' class='input' type='password' placeholder='密码'></div>";
    formStr+="<div class='form-input login-input-verifiCode'><label class='input-icon icon-verifiCode'></label><input name='verifiCode' class='input input-verifiCode' type='text' maxlength='4' placeholder='验证码'><img src='../verifyCode/N2Qz.jpg' id='verifiImg' title='点击换一张'></div>";
    formStr+="<div id='login-btn'>登录</div></form></div>";
    $("body").append(formStr);
    setFrameWH("loginFrame");
    initLoginFrameBtn();
    getCheckCode("verifiImg");
}
function setMaskWH() {
  $("#mask").width($(document).width()).height($(document).height());
}
function setFrameWH(id) {
   $("#"+id).css({"left":($(window).width()- $("#"+id).width())/2,"top":($(window).height()- $("#"+id).height())/2})
}

function initLoginFrameBtn(){
  $(".close").click(function() {
    $("#loginFrame").remove();
    $("#registerFrame").remove();
    $("#mask").remove();
  });
  $("#login-tab").click(function () {
    $("#loginFrame").show();
  });
  $("#register-tab").click(function () {
    $("#loginFrame").hide();
    showRegisterFrame();
  });
  $("#verifiImg").click(function () {
    getCheckCode("verifiImg");
  });
  $("#login-btn").click(function () {
    login();
  });
}

function getCheckCode(id) {
  var url ="/ClubSystem/users/getImgVerifyCode";
  $.get(url,function (data) {
    $("#"+id).attr("src","/ClubSystem/"+data.resultParm.path);
  });
}

function login(argument) {
  var url ="/ClubSystem/users/login";
  var sendData = $("#loginForm").serialize();
  console.log(sendData);
  $.post(url,sendData,function (data) {
    //登录处理
  });
}

/*注册页面内容*/
function showRegisterFrame() {
  showMask();
  if ($("#registerFrame").length>0) {
    $("#registerFrame").remove();
    $("#loginFrame").hide();
  }
  var formStr = "<div id='registerFrame'><div class='hd'><a class='close'></a><div id='common-tab' class='tab-active'>普通注册</div><div id='phone-tab'>手机注册</div></div>";
  formStr+="<form id='commonForm' class='' method='POST'>";
  formStr+="<div class='form-input'><label class='input-icon icon-account'></label><input name='rAccount' class='input' type='text' placeholder='账号'></div>"
  formStr+="<div class='form-input'><label class='input-icon icon-name'></label><input name='rNmae' class='input' type='text' placeholder='昵称'></div>"
  formStr+="<div class='form-input'><label class='input-icon icon-password'></label><input name='rPassword' class='input' type='password' placeholder='密码'></div>"
  formStr+="<div class='form-input'><label class='input-icon icon-password'></label><input name='rPassword2' class='input' type='password' placeholder='再次输入密码'></div>"
  formStr+="<div class='form-input login-input-verifiCode'><label class='input-icon icon-verifiCode'></label><input name='rVerifiCode' class='input input-verifiCode' type='text' maxlength='4' placeholder='验证码'><img src='../verifyCode/N2Qz.jpg' id='rVerifiImg' title='点击换一张'></div>";
  formStr+="<div id='common-btn'>注册</div></form></div>";
  $("body").append(formStr);
  setFrameWH("registerFrame");
  initConmonFrameBtn();
  getCheckCode("rVerifiImg");
}

function initConmonFrameBtn() {
  $(".close").click(function () {
    $("#loginFrame").remove();
    $("#registerFrame").remove();
    $("#mask").remove();
  });
  $("#common-tab").click(function () {
    $("#phone-tab").removeClass("tab-active");
    $("#common-tab").addClass("tab-active");
    $("#commonForm").show();
    $("#phoneForm").hide();

  });
  $("#phone-tab").click(function () {
    $("#common-tab").removeClass("tab-active");
    showPhoneForm();
    $("#commonForm").hide();
    $("#phone-tab").addClass("tab-active");
  });
  $("#rVerifiImg").click(function () {
    getCheckCode("rVerifiImg");
  })
  $("#common-btn").click(function () {
    commonRegister();
  });
}

//手机注册
function showPhoneForm(argument) {
  if ($("#phoneForm").length>0) {
    $("#phoneForm").show();
    return;
  }
  var formStr="<form id='phoneForm' class='' method='POST'>";
  formStr+="<div class='form-input'><label class='input-icon icon-account'></label><input name='pPhone' class='input' type='text' placeholder='手机号码'></div>"
  formStr+="<div class='form-input'><label class='input-icon icon-password'></label><input name='pPassword' class='input' type='password' placeholder='密码'></div>"
  formStr+="<div class='form-input'><label class='input-icon icon-password'></label><input name='pPassword2' class='input' type='password' placeholder='再次输入密码'></div>"
  formStr+="<div class='form-input login-input-verifiCode'><label class='input-icon icon-verifiCode'></label><input name='pVerifiCode' style='width:50%;' class='input input-verifiCode' type='text' maxlength='4' placeholder='手机验证码'>";
  formStr+="<button id='getPhoneCode'>获取</button></div>";
  formStr+="<div id='phone-btn'>注册</div></form>";
  $("#registerFrame").append(formStr);
  initPhoneFormBtn();
}
function initPhoneFormBtn () {
  $("#getPhoneCode").click(function () {
    $(this).attr("disabled",true).css({"cursor":"default","background-color":"#ccc"});
    var i= 59;
    var mInterval = setInterval(function () {
        $("#getPhoneCode").html(i);
        i--;
      },1000);
    setTimeout(function () {
       clearInterval(mInterval);
       $("#getPhoneCode").attr("disabled",false).css({"cursor":"pointer","background-color":"#12b7f5"}).html("获取");
    },61000);
    // sendPhoneCode();
  })
}

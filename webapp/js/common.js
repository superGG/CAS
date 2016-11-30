$(document).ready(function(){

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
    formStr+="<div class='form-input login-input-verifiCode'><label class='input-icon icon-verifiCode'></label><input name='verifyCode' class='input input-verifiCode' type='text' maxlength='4' placeholder='验证码'><img src='../../verifyCode/N2Qz.jpg' id='verifiImg' title='点击换一张'></div>";
    formStr+="<div id='login-btn'>登录</div></form>";
    formStr+="<form id='registerForm' class='' method='POST'>";
    formStr+="<div class='form-input'><label class='input-icon icon-account'></label><input name='account' class='input' type='text' placeholder='账号'></div>"
    formStr+="<div class='form-input'><label class='input-icon icon-password'></label><input name='password' class='input' type='password' placeholder='密码'></div>"
    formStr+="<div class='form-input'><label class='input-icon icon-password'></label><input name='password2' class='input' type='password' placeholder='再次输入密码'></div>"
    formStr+="<div class='form-input login-input-verifiCode'><label class='input-icon icon-verifiCode'></label><input name='verifyCode' class='input input-verifiCode' type='text' maxlength='4' placeholder='验证码'><img src='../../verifyCode/N2Qz.jpg' id='rVerifiImg' title='点击换一张'></div>";
    formStr+="<div id='register-btn'>注册</div></form></div>";
    $("body").append(formStr);
    setFrameWH();
    initLoginFrameBtn();
    getCheckCode("verifiImg");
}
function setMaskWH() {
  $("#mask").width($(document).width()).height($(document).height());
}
function setFrameWH() {
   $("#loginFrame").css({"left":($(window).width()- $("#loginFrame").width())/2,"top":($(window).height()- $("#loginFrame").height())/2})
}

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
    login();
  });
  $("#register-btn").click(function () {
    register();
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
    alert(data.resultInfo)
  });
}

function register(argument) {
  var url ="/ClubSystem/users/register";
  var sendData = $("#registerForm").serialize();
  console.log(sendData);
  $.post(url,sendData,function (data) {
    alert(data.resultInfo)
  });
}

$(document).ready(function(){
	initUserInformation();
	uploadImg();
});
function initUserInformation(){
	 var userData = getCookieUserData();
	 console.log(userData);
	 $("#head").attr("src","../.."+userData.headPath);
	 $("#id").val(userData.id);
	 $("#userName").text(userData.name);
	 $("#portrait").attr("src","../.."+userData.headPath);
	 $("#name").val(userData.name);
//	 $("#sex").val(userData.sex);
	 if(userData.sex){
		 $("#male").attr("checked",true);
	 }else{
		 $("#female").attr("checked",true);
	 }
	 $("#phone").val(userData.phone);
	 $("#email").val(userData.email);
	 $("#hobby").text(userData.hobby);
	 $("#singnation").text(userData.hobby);
}
function updatePassword(){
	var account = $("#account").val();
	var oldPassword = $("#old_password").val();
	var newPassword = $("#new_password").val();
	var url = "/ClubSystem/users/updatePassword";
	var parm = "account="+account+"&oldPassword="+oldPassword+"&newPassword="+newPassword;
	console.log(account+"-"+oldPassword+"-"+newPassword);
	$.post(url,parm,function(data){
		if(data.serviceResult){
			alert(data.resultInfo);
		}else{
			alert(data.resultInfo);
		}
	});
}
function showUpdatePassword(){
	$(".lwd_main_iframe").append('<div class="update_password_box"><table><tr><td><input type="text" name="account" id="account" placeholder="帐号"></td></tr><tr><td><input type="password" name="oldPassword" id="old_password" placeholder="旧密码"></td></tr><tr><td><input type="password" placeholder="新密码" name="newPassword" id="new_password"></td></tr><tr><td><button onclick="updatePassword();removeBox();">确定</button> <button onclick="removeBox()">取消</button></td></tr></table></div>');
}
function removeBox(){
	$(".update_password_box").remove();
}
function uploadImg(){
	$("#uploadHead").change(function(){
		objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
		if (objUrl) {
			$("#portrait").attr("src", objUrl) ;
		}
	});
}
//建立一個可存取到該file的url
function getObjectURL(file) {
	var url = null ;
	if (window.createObjectURL!=undefined) { // basic
		url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file) ;
	}
	return url ;
}
//修改社徽
function doUpload() {
	 var formData = new FormData($( "#uploadForm" )[0]);  
	 if($("#uploadHead").val() != ''){
	    $.ajax({  
	         url: '/ClubSystem/userDetails/updateUserImage' ,  
	         type: 'POST',  
	         data: formData,  
	         async: false,  
	         cache: false,  
	         contentType: false,  
	         processData: false,  
	         success: function (data) {  	               
		     },  
	         error: function (data) {  
	             alert(data.resultInfo);  
	         }  
	   }); 
	 }else{
		 console.log("不更改头像，只更新文本信息。");
	 }
}
function updateDetails(){
	var url = "/ClubSystem/userDetails/update";
	var parm = 
}

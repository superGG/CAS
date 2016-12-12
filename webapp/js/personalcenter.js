$(document).ready(function(){
	initUserInformation();
});
function initUserInformation(){
	 var userData = getCookieUserData();
	 $("#head").attr("src","../.."+userData.headPath);
	 id = userData.id;
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
	 $("#singnation").text(userData.singnation);
}
function updatePassword(){
	var oldPassword = $("#old_password").val();
	var newPassword = $("#new_password").val();
	var url = "/ClubSystem/users/updatePassword";
	var parm = "detailId="+id+"&oldPassword="+oldPassword+"&newPassword="+newPassword;
	$.post(url,parm,function(data){
		if(data.serviceResult){
			alert(data.resultInfo);
		}else{
			alert(data.resultInfo);
		}
	});
}
function showUpdatePassword(){
	$(".lwd_main_iframe").append('<div class="update_password_box"><table><tr><td><input type="password" name="oldPassword" id="old_password" placeholder="旧密码"></td></tr><tr><td><input type="password" placeholder="新密码" name="newPassword" id="new_password"></td></tr><tr><td><button onclick="updatePassword();removeBox();">确定</button> <button onclick="removeBox()">取消</button></td></tr></table></div>');
}
function removeBox(){
	$(".update_password_box").remove();
}
//修改社徽
function doUpload() {
	 var formData = new FormData($( "#uploadForm" )[0]);  
	 formData.append("id",id);
    $.ajax({  
         url: '/ClubSystem/userDetails/updateUserImage' ,  
         type: 'POST',  
         data: formData,  
         async: false,  
         cache: false,  
         contentType: false,  
         processData: false,  
         success: function (data) { 
        	 alert(data.resultInfo);  
        	 var oldUserData = getCookieUserData();
 			 delectCookieUserData();//删除cookie
 			 var newUserData = oldUserData;
 			 newUserData.headPath = data.resultParm.headPath;
 			 console.log(data.resultParm.headPath);
 			 setCookieUserData(newUserData);//设置更新后的cookie
 			 $(".userImg img").attr("src","../.."+data.resultParm.headPath);
 			 initUserInformation();
	     },  
         error: function (data) {  
             alert(data.resultInfo);  
         }  
   }); 
}
function updateDetails(){
	var id = getCookieUserData().id;
	var name = document.getElementById('name').value;
	var sex;
	 if($('input:radio[name="sex"]:checked').attr("id")=="male"){
		 sex = 1;
	 }
	 else{
		 sex = 0;
	 }
	var phone = document.getElementById('phone').value;
	var email = document.getElementById('email').value;
	var hobby = document.getElementById('hobby').value;
	var singnation = document.getElementById('singnation').value;
	
	var url = "/ClubSystem/userDetails/update";
	var parm = "id="+id+"&name="+name+"&sex="+sex+"&phone="+phone+"&email="+email+"&hobby="+hobby+"&singnation="+singnation;
	$.post(url,parm,function(data){
		if(data.serviceResult){
			console.log(data);
			var newUserData = data.resultParm.userDetail;
			delectCookieUserData();//删除cookie
			setCookieUserData(newUserData);//设置更新后的cookie
			$(".user .userName").text(data.resultParm.userDetail.name);
			initUserInformation();
			alert(data.resultInfo);
		}else{
			alert(data.resultInfo);
		}
	});
}

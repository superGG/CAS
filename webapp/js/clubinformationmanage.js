var clubId;
$(document).ready(function(){
	initClubInformation();
	uploadImg();
	updateClub();
});
function initClubInformation(){
	userData = getCookieUserData();
	if (userData==null) {
		alert("请先登录");
		showLoginFrame();
		return;
	}
	var url = "/ClubSystem/club/getMyClub";
	var parm = "detailId="+userData.id;
	var typeUrl = "/ClubSystem/clubtype/getAlls";
	$.get(url,parm,function(data){
		if(data.serviceResult){
			var club = data.resultParm.club;
			$("#id").val(club.id);
			clubId = club.id;
			$("#typeName").val(club.typeName);
			$("#badge").attr("src","../.."+club.badge);
			$("#schoolName").val(club.schoolName);
			$("#name").val(club.name);
			
		//	$("#type").val(club.typeName);
			$.get(typeUrl,function(data){
				if(data.serviceResult){
					var typeList = data.resultParm.clubType;
					showClubType(typeList);
					$("#type option:contains('"+club.typeName+"')").attr("selected", true);
				}
			});
			$("#createtime").val(club.createtime.split(" ")[0]);
			$("#leader").val(club.leader);
			
			$("#number").val(club.number);
			$("#phone").val(club.phone);
			$("#email").val(club.email);
			$("#introduce").val(club.introduce);
		}else{
			alert(data.resultInfo);
		}
	});
}
function showClubType(typeList){
	for(var i = 0;i<typeList.length;i++){
		$("#type").append('<option value ="'+(i+1)+'">'+typeList[i].name+'</option>');
	}
}
//修改社团文本信息
function updateClub(){
	$("#update").click(function(){		
		var index = document.getElementById('type').selectedIndex;
		var id = clubId;
		var name = document.getElementById('name').value;
		var school = document.getElementById('schoolName').value;
		var typeName = document.getElementById('type').options[index].text;
		var phone = document.getElementById('phone').value;
		var email = document.getElementById('email').value;
		var introduce = document.getElementById('introduce').value;
		var updateUrl = "/ClubSystem/club/updateMyClub";
		var updateParm = "id="+id+"&schoolName="+school+"&name="+name+"&typeName="+typeName+"&phone="+phone+"&email="+email+"&introduce="+introduce;
		$.post(updateUrl,updateParm,function(data){
			if(data.serviceResult){
				doUpload();
				$("#type option").remove();
				initClubInformation();
				alert(data.resultInfo);
			}else{
				alert(data.resultInfo);
			}
		});
	});
}
function uploadImg(){
	$("#uploadImg").change(function(){
		objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
		if (objUrl) {
			$("#badge").attr("src", objUrl) ;
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
	 if($("#uploadImg").val() != ''){
	    $.ajax({  
	         url: '/ClubSystem/club/updateBadge' ,  
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
		 console.log("没有更改社徽");
	 }
}
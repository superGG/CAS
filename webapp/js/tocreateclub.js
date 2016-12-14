var isValidate_1;
var isValidate_2;
var isValidate_3;
$(document).ready(function(){
	uploadImg();
	
});
function showTable(){
	initTable();
	$("#applytable").show();
	//校验表单
	isValidate_1 = $('#form_1').validate({  
		onsubmit: false,
		onkeyup: false, 
		onfocusout: function(element){
	       $(element).valid();
	   },
	   errorPlacement: function(error, element) {  
	   		 element.val('');
		         element.attr("placeholder",error.html());
	   },
		rules:{
			name:{
				required:true,
				minlength:2,
				maxlength:10
			}
		},
		messages:{
			name:{
				required:"不能为空",
				minlength:"长度不能小于2",
				maxlength:"长度不能大于10"
			}
		}		
	}); 
	
	isValidate_2 = $('#form_2').validate({  
		onsubmit: false,
		onkeyup: false, 
		onfocusout: function(element){
	       $(element).valid();
	   },
	   errorPlacement: function(error, element) {  
	   		 element.val('');
		         element.attr("placeholder",error.html());
	   },
		rules:{
			phone:{
				isPhone:true,
				required:true
			}
		},
		messages:{
			phone:{
				required:"不能为空"
			}
		}
	}); 
	
	isValidate_3 = $('#form_3').validate({  
		onsubmit: false,
		onkeyup: false, 
		onfocusout: function(element){
	       $(element).valid();
	   },
	   errorPlacement: function(error, element) {  
	   		 element.val('');
		         element.attr("placeholder",error.html());
	   },
		rules:{
			reason:{
				required:true
			}
		},
	   messages:{
		   reason:{
				required:"不能为空"
			}
		}
	}); 
	
}
function closeTable(){
	$("#schoolName").children().remove();
	$("#typeName").children().remove();
	$("#applytable").hide();
}
function initTable(){
	initSchoolList();
	initTypeList();
}
function initSchoolList(){
	var url = "/ClubSystem/school/getAlls";
	$.get(url,function(data){
		if(data.serviceResult){
			var schoolList = data.resultParm.school;
			showSchoolList(schoolList);
		}else{
			alert(data.resultInfo);
		}
	});
}
function initTypeList(){
	var url = "/ClubSystem/clubtype/getAlls";
	$.get(url,function(data){
		if(data.serviceResult){
			var typeList = data.resultParm.clubType;
			showTypeList(typeList);
		}else{
			alert(data.resultInfo);
		}
	});
}
function showSchoolList(schoolList){
	for(var i = 0;i<schoolList.length;i++){
		$("#schoolName").append('<option value="'+schoolList[i].name+'">'+schoolList[i].name+'</option>');
	}
}
function showTypeList(typeList){
	for(var i = 0;i<typeList.length;i++){
		$("#typeName").append('<option value="'+typeList[i].name+'">'+typeList[i].name+'</option>');
	}
}
function createClub(){
		var userDate = getCookieUserData();
		var detaliId = userDate.id;
		var schoolIndex = document.getElementById('schoolName').selectedIndex;
		var schoolName = document.getElementById('schoolName').options[schoolIndex].text;
		var typeIndex = document.getElementById('typeName').selectedIndex;
		var typeName = document.getElementById('typeName').options[typeIndex].text;
		var name = $("#name").val();
		var phone = $("#phone").val();
		var introduce = $("#reason").val();
		var majorClass = $("#major_class").val();
		var phone = $("#phone").val();
//		var url = "/ClubSystem/club/create";
//		var parm = "detailId="+detaliId+"&schoolName="+schoolName+"&clubType="+typeName+"&name="+name+"&phone="+phone+"&introduce="+introduce;
//		$.post(url,parm,function(data){
//			if(data.serviceResult){
//				alert(data.resultInfo);
//				window.location.href ="/ClubSystem/views/club/clubinformationmanage.html";
//			}else{
//				alert(data.resultInfo);
//			}
//		});
		if(isValidate_1.checkForm() && isValidate_2.checkForm() && isValidate_3.checkForm()){
						var formData = new FormData($( "#uploadForm" )[0]);
						formData.append("detailId",detaliId);
						formData.append("schoolName",schoolName);
						formData.append("clubType",typeName);
						formData.append("name",name);
						formData.append("phone",phone);
						formData.append("introduce",introduce);
						$.ajax({  
							url: '/ClubSystem/club/create' ,  
							type: 'POST',  
							data: formData,  
							async: false,  
							cache: false,  
							contentType: false,  
							processData: false,  
							success: function(data) {  
								alert(data.resultInfo); 
								if(data.serviceResult){
									window.location.href ="/ClubSystem/views/club/clubinformationmanage.html";
								}
							},  
							error: function(data) {  
								alert(data.resultInfo);
							}  
						});
		}
//		else{
//			alert(不能填写空信息);
//			showTable();
//		}
//		}
		else{
			alert("请按照格式填写");
			isValidate_1.showErrors();
			isValidate_2.showErrors();
			isValidate_3.showErrors();
			showTable();
		}
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

var clubId;
var isValidate;
$(document).ready(function(){
	setPositionHref();
	initClubInformation();
	uploadImg();
	updateClub();
	
	//表单验证
    isValidate = $('#update_club').validate({  
    	onsubmit: false,
    	onkeyup: false, 
    	onfocusout: function(element){
    	    $(element).valid();
    	},
    	errorPlacement: function(error, element) {  
    		error.appendTo(element.next());
    	},
    	rules:{
    		name:{
    			required:true,
    			minlength:2,
    			maxlength: 10
    		},
    		leader:{
    			required:true,
    			minlength:2,
    			maxlength: 8
    		},
    		email:{
    			required:true,
    			email:true
    		},
    		phone:{
    			required:true,
    			isPhone:true
    		},
    		introduce:{
    			required:true,		
    		}
    	},
    	messages:{
    		name:{
    			required:"不能为空!",
    			minlength:"长度不能小于2",
    			maxlength:"长度不能大于8"
    		},
    		email:{
    			required:"不能为空!",
    			email:"example@163.com"
   			},
   			leader:{
   				required:"不能为空!",
   				minlength:"不能少于2个字"
   			},
   			introduce:{
   				required:"不能为空!",
   			},
   			phone:{
   				required:"不能为空!",
   			}
   		}
   	}); 
	
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
		var leader = document.getElementById('leader').value;
		var email = document.getElementById('email').value;
		var introduce = document.getElementById('introduce').value;
		var updateUrl = "/ClubSystem/club/updateMyClub";
		var updateParm = "id="+id+"&schoolName="+school+"&name="+name+"&typeName="+typeName+"&leader="+leader+"&phone="+phone+"&email="+email+"&introduce="+introduce;
		if(isValidate.checkForm()){
			$.post(updateUrl,updateParm,function(data){
				if(data.serviceResult){
					$("#type option").remove();
					initClubInformation();
					alert(data.resultInfo);
				}else{
					alert(data.resultInfo);
				}
			});
		}
		else{
			alert("请按照格式填写");
			isValidate.showErrors();
		}
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
	        	 alert(data.resultInfo); 
		     },  
	         error: function (data) {  
	             alert(data.resultInfo);  
	         }  
	   }); 
	 }else{
		 console.log("没有更改社徽");
	 }
}
function clubDelete(){
	var con;
	con = confirm("是否注销？");
	if(con){
		var url = "/ClubSystem/club/delete";
		var parm = "detailId="+getCookieUserData().id;
		$.post(url,parm,function(data){
			if(data.serviceResult){
				alert(data.resultInfo); 
				window.location.href ="/ClubSystem/views/club/tocreateclub.html";
			}else{
				alert(data.resultInfo); 
			}
		});
	}else{
		return false;
	}	
//表单验证
function validate(){
	//表单验证
    var isValidate = $('#update_club').validate({  
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
    			chinese:true,
    			minlength:2,
    			maxlength: 10
    		},
    		leader:{
    			required:true,
    			chinese:true,
    			minlength:2,
    			maxlength: 8
    		},
    		email:{
    			email:true
    		},
    		phone:{
    			required:true,
    			isPhone:true
    		},
    		introduce:{
    			required:true,		
    		}
    	},
    	messages:{
    		name:{
    			required:"不能为空!",
    			chinese:"请输入汉字",
    			minlength:"长度不能小于2",
    			maxlength:"长度不能大于8"
    		},
    		email:{
    			email:"example@163.com"
   			},
   			introduce:{
   				required:"不能为空!",
   			}
   		}
   	}); 
    return isValidate;
}
}
var memberId;
var isValidate;
$(document).ready(function(){
	setMyJoinedClubHref();
	initInformation();
	//校验表单
	isValidate = $('#form_prInfo').validate({  
		onsubmit: false,
		onkeyup: false, 
		debug:true,
		onfocusout: function(element){
	       $(element).valid();
	   },
	   errorPlacement: function(error, element) {  
		   error.appendTo(element.next());  
	   },
		rules:{
			nation:{
				required:true,
				chinese:true
			},
			name:{
				required:true,
				minlength:2,
				maxlength: 8
			},
			major_class:{
				required:true,
			},
			email:{
				required:true,
				email:true
			},
			phone:{
				required:true,
				isPhone:true
			},
			age:{
				required:true,
				age:true,
			},
			reason:{
				required:true
			}
		},
		messages:{
			nation:{
				required:"不能为空!",
			},
			name:{
				required:"不能为空!",
				minlength:"长度不能小于2",
				maxlength:"长度不能大于8"
			},
			major_class:{
				required:"不能为空!"
			},
			email:{
				required:"不能为空!",
				email:"example@163.com"
			},
			reason:{
				required:"不能为空!",
			},
			age:{
				required:"不能为空!",
			},
			phone:{
				required:"不能为空!",
			}
		}	
	}); 
});

function initInformation(){
	var url = "/ClubSystem/apply/memberDetail";
	var parm = "clubId="+getUrlParam("clubId")+"&detailId="+getCookieUserData().id;
	$.get(url,parm,function(data){
		if(data.serviceResult){
			var member = data.resultParm.apply;
			memberId = member.id;
			$("#name").val(member.name);
			$("#email").val(member.email);
			if(member.sex){
				$("#sex").val("男");
			}else{
				$("#sex").val("女");
			}
			$("#age").val(member.age);
			$("#major_class").val(member.majorClass);
			$("#nation").val(member.nation);
			$("#hobby").val(member.hobby);
			$("#phone").val(member.phone);
			$("#createtime").val(member.createtime.split(" ")[0]);
			$("#introduce").val(member.introduce);
			$("#position").val(member.positionName);
		}else{
			alert(data.resultInfo);
		}
	});
}

function updateInformation(){
	
	var name = $("#name").val();
	var email = $("#email").val();
	var sex;
	if($("#sex").val()=="男"){
		sex = 1;
	}else{
		sex = 0;
	}
	var age = $("#age").val();
	var majorClass = $("#major_class").val();
	var nation = $("#nation").val();
	var hobby = $("#hobby").val();
	var phone = $("#phone").val();
	var introduce = $("#introduce").val();
	var url = "/ClubSystem/apply/update";
	var parm = "id="+memberId+"&name="+name+"&email="+email+"&sex="+sex+"&age="+age+"&majorClass="+majorClass+"&nation="+nation+"&hobby="+hobby+"&phone="+phone+"&introduce="+introduce;
	if(isValidate.checkForm()){
		$.post(url,parm,function(data){
			if(data.serviceResult){
				alert(data.resultInfo);
				initInformation();
			}else{
				alert(data.resultInfo);
			}
		});
	}
	else{
		alert("请正确填写表单");
		isValidate.showErrors();
		
	}
}


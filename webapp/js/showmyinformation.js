var memberId;
$(document).ready(function(){
	initInformation();
});
function initInformation(){
	var url = "/ClubSystem/apply/memberDetail";
	var parm = "clubId="++"&detailId="+;
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
	var url = "";
	var parm = "id="+memberId+"&name="+name+"&email="+email+"&sex="+sex+"&age="+age+"&majorClass="+majorClass+"&nation="+nation+"&hobby="+hobby+"&phone="+phone+"&introduce="+introduce;
	$.post(url,parm,function(data){
		if(data.serviceResult){
			alert(data.resultInfo);
			initInformation();
		}else{
			alert(data.resultInfo);
		}
	});
}
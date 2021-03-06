var memberId;
var detailId;
$(document).ready(function(){
	setPositionHref();
	initMemberInformation();
});
function initMemberInformation(){
	var url = "/ClubSystem/apply/getMemberDetail";
	var parm = "applyId="+getUrlParam('applyId');
	$.get(url,parm,function(data){
		if(data.serviceResult){
			var member = data.resultParm.apply;
			memberId = member.id;
			detailId = member.detailId;
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
			
			var positionUrl = "/ClubSystem/position/getByClubId";
			var positionParm = "id="+member.clubId;
			
			$.get(positionUrl,positionParm,function(data){
				if(data.serviceResult){
					var positionList = data.resultParm.position;
					showClubPosition(positionList);
					$("#position option:contains('"+member.positionName+"')").attr("selected", true);
				}
			});
			
		}else{
			alert(data.resultInfo);
		}
	});
}
function showClubPosition(positionList){
	$("#position").append('<option value="0"> </option>');
	for(var i = 0;i<positionList.length;i++){
		$("#position").append('<option value ="'+positionList[i].id+'">'+positionList[i].name+'</option>');
	}
}
//修改职位
function updatePosition(){
	var index = document.getElementById('position').selectedIndex;
	var positionId = document.getElementById('position').options[index].getAttribute("value");
	var url = "/ClubSystem/apply/updateposition";
	var parm;
	if(positionId == 0){
		parm = "id="+memberId;
	}else{
		parm = "id="+memberId+"&positionId="+positionId;
	}
	$.post(url,parm,function(data){
		if(data.serviceResult){
			initMemberInformation();
			alert(data.resultInfo);
		}else{
			alert(data.resultInfo);
		}
	});
}
//删除成员
function del(){
	var con;  
	con=confirm("确认删除?"); //在页面上弹出对话框  
	if(con==true){
		var url = "/ClubSystem/apply/deleteMember";
		var parm = "applyId="+memberId;
		$.post(url,parm,function(data){
			window.location.href="/ClubSystem/views/club/membermanage.html";
		});
	}else{
		return false;
	}  
}
function back(){
	window.location.href="/ClubSystem/views/club/membermanage.html";
}

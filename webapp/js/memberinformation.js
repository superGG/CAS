var memberId;
var detailId;
$(document).ready(function(){
	initMemberInformation();
});
function initMemberInformation(){
	var url = "/ClubSystem/apply/memberDetail";
	var parm = "applyId=9";
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
			
			var positionUrl = "/ClubSystem/position/getNameByClubId";
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
	for(var i = 0;i<positionList.length;i++){
		$("#position").append('<option value ="'+(i+1)+'">'+positionList[i]+'</option>');
	}
}
//修改职位
function updatePosition(){
	var index = document.getElementById('position').selectedIndex;
	var positionName = document.getElementById('position').options[index].text;
	var url = "/ClubSystem/apply/updateposition";
	var parm = "detailId="+detailId+"&applyId="+memberId+"&positionName="+positionName;
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
			history.go(-1);
		});
	}else{
		return false;
	}  
}
function back(){
	history.go(-1);
}

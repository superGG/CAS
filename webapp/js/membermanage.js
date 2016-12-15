var applyId;
$(document).ready(function(){
	setPositionHref();
	initMemberList();
});
function showMemberList(memberList){
	var positionName = " ";
	var majorClass = " ";
	var phone = " ";
	for(var i = 0; i<memberList.length; i++){
		if(memberList[i].positionName==undefined){
			positionName = " ";
		}else{
			positionName = memberList[i].positionName;
		}
		if(memberList[i].majorClass==undefined){
			majorClass = " ";
		}else{
			majorClass = memberList[i].majorClass;
		}
		if(memberList[i].phone==undefined){
			phone = " ";
		}else{
			phone = memberList[i].phone;
		}
		$("#membertable").append('<tr id="'+memberList[i].id+'"><td>'+(i+1)+'</td><td>'+memberList[i].name+'</td><td>'+positionName+'</td><td>'+majorClass+'</td><td>'+phone+'</td><td>'+memberList[i].createtime.split(" ")[0]+'</td><td><a href="memberinformation.html?applyId='+memberList[i].id+'"><button>查看</button></a> <button onclick="memberDelete(this)">删除</button></td></tr>');
		
	}
}
function initMemberList(){
	var url = "/ClubSystem/apply/displayClubApplyIsOk";
	var parm = "detailId="+getCookieUserData().id;
	$.get(url,parm,function(data){
		if(data.serviceResult){
			var memberList = data.resultParm.apply;
			showMemberList(memberList);
		}else{
			alert(data.resultInfo);
		}
	});
}
function memberDelete(which){
	var con;
	con = confirm("确定要删除吗？");
	if(con){
		var url ="/ClubSystem/apply/deleteMember";
		var parm = "applyId="+$(which).parent().parent().attr("id");
		$.post(url,parm,function(data){
			if(data.serviceResult){
				$(which).parent().parent().remove();
				alert(data.resultInfo);
			}else{
				alert(data.resultInfo);
			}
		});
	}else{
		return false;
	}
}
function memberSearch(){
	var name = $("#search").val();
	var url = "/ClubSystem/apply/searchMember";
	var parm = "detailId="+getCookieUserData().id+"&name="+name;
	$.get(url,parm,function(data){
		if(data.serviceResult){
			$("#membertable").children("tr").remove();
			var searchList = data.resultParm.apply;
			showMemberList(searchList);
		}else{
			alert(data.resultInfo);
		}
	});
}




var applyId;
var index;
$(document).ready(function(){
	index = 1;
	initMemberList(index);
	moreMember(index);
});
function showMemberList(memberList){
	for(var i = 0; i<memberList.length; i++){
		$("#membertable").append('<tr id="'+memberList[i].applyId+'"><td>'+(i+1)+'</td><td>'+memberList[i].name+'</td><td>'+memberList[i].position+'</td><td>'+memberList[i].majorClass+'</td><td>'+memberList[i].tel+'</td><td>'+memberList[i].createtime.split(" ")[0]+'</td><td><a href="memberinformation.html"><button>查看</button></a> <button onclick="memberDelete(this)">删除</button></td></tr>');
	}
}
function initMemberList(index){
	var url = "/ClubSystem/apply/displayPageMember";
	var parm = "detailId=2&indexPageNum="+index+"&size=6";
	$.get(url,parm,function(data){
		if(data.serviceResult){
			var memberList = data.resultParm.memberlist;
			showMemberList(memberList);
			if($("#membertable tr").length >= data.resultParm.totalCount){
				$("#more").remove();
				$(".lwd_main_iframe").append('<div class="noMore">没有更多数据...</div>');
			}
		}else{
			alert(data.resultInfo);
		}
	});
}
function moreMember(index){
	$("#more").click(function(){
		index = index+1;
		initMemberList(index);
	});
}
function memberDelete(which){
	var con;
	con = confirm("确定要删除吗？");
	if(con){
		console.log($(which).parent().parent().attr("name"));
		var url ="/ClubSystem/apply/deleteMember";
		var parm = "applyId="+$(which).parent().parent().attr("id");
		console.log($(which).parent().parent().attr("id"));
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
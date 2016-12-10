var index;
$(document).ready(function(){
	index = 1;
	initMemberList(index);
	moreMember(index);
});
function showMemberList(memberList){
	for(var i = 0; i<memberList.length; i++){
		$("#membertable").append('<tr id="'+memberList[i].id+'"><td>'+(i+1)+'</td><td>'+memberList[i].name+'</td><td>'+memberList[i].position+'</td><td>'+memberList[i].majorClass+'</td><td>'+memberList[i].tel+'</td><td>'+memberList[i].createtime.split(" ")[0]+'</td><td><a href="memberinformation.html"><button>查看</button></a> <button>删除</button></td></tr>');
	}
}
function initMemberList(index){
	var url = "/ClubSystem/apply/displayPageMember";
	var parm = "detailId=4&indexPageNum="+index+"&size=6";
	$.get(url,parm,function(data){
		console.log(data);
		if(data.serviceResult){
			var memberList = data.resultParm.memberlist;
			showMemberList(memberList);
		}else{
			$("#more").remove();
			$(".lwd_main_iframe").append('<div class="noMore">没有更多数据...</div>');
		}
	});
}
function moreMember(index){
	$("#more").click(function(){
		index = index+1;
		initMemberList(index);
	});
}
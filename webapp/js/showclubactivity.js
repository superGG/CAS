var index;
$(document).ready(function(){
	index = 1;
	setMyJoinedClubHref();
	initActivityList(index);
	moreActivity(index);
});
function showActivityList(activityList){
	for(var i = 0; i<activityList.length; i++){
		var time = activityList[i].createtime.split(" ")[0];
		$(".activity_list").append('<div class="activity_item"><img src="../../images/folder_new.gif" width="17px" height="17px"><a href="#">'+activityList[i].title+'</a><div class="num_count"><span>发布时间：'+time+'</span></div></div>');
	}
}
function initActivityList(index){
	var url = "/ClubSystem/activity/getClubActivity";
	var parm = "indexPageNum="+index+"&size=6&clubId="+getUrlParam("clubId");
	$.get(url,parm,function(data){
		console.log(index+data);
		if(data.serviceResult){
			var activityList = data.resultParm.activity;
			showActivityList(activityList);
		}else{
			alert("没有更多数据了！");
		}
	});
}
function moreActivity(index){
	$("#more").click(function(){
		index = index+1;
		initActivityList(index);
	});
}
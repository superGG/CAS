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
		$(".activity_list").append('<div class="activity_item" id="'+activityList[i].id+'"><img src="../../images/folder_new.gif" width="17px" height="17px"><a href="javascript:void(0);" onclick="showActivityContent(this)">'+activityList[i].title+'</a><div class="num_count"><span>发布时间：'+time+'</span></div></div>');
	}
}
function initActivityList(index){
	var url = "/ClubSystem/activity/getClubActivity";
	var parm = "indexPageNum="+index+"&size=6&clubId="+getUrlParam("clubId");
	$.get(url,parm,function(data){
		if(data.serviceResult){
			$("#more").show();
			var activityList = data.resultParm.activity;
			showActivityList(activityList);
		}else{
			$("#more").hide();
			$(".activity_list").append('<div class="noMore">'+data.resultInfo+'</div>');
		}
	});
}
function moreActivity(index){
	$("#more").click(function(){
		index = index+1;
		initActivityList(index);
	});
}
function showActivityContent(which){
	$(".activity_list").children(".activity_item").hide();
	var url = "/ClubSystem/activity/getDetails";
	var parm = "id="+$(which).parent().attr("id");
	$.get(url,parm,function(data){
		if(data.serviceResult){
			$(".activity_list").append('<div id="activityContent"><br><div><button onclick="back()">返回</button></div><h2>标题：'+data.resultParm.activity.title+'</h2><h4>社团：'+data.resultParm.activity.clubName+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布时间：'+data.resultParm.activity.createtime.split(" ")[0]+'</h2><h3>内容:</h3>'+html_decode(data.resultParm.activity.content)+'</div>');
			$("#more").hide();
		}else{
			alert(data.resultInfo);
		}
	});
}
//替换转义
function html_decode(str) {
	var s = "";
	if (str.length == 0) {
		return "";
	}
	s = str.replace(/&lt;/g, "<");
	s = s.replace(/&gt;/g, ">");
	s=s.replace(/\[p\];/g,"%");
    s=s.replace(/\[a\];/g,"&");
    s=s.replace(/\[ad\];/g,"+");
	return s;
}
function back(){
	$(".activity_list").children("#activityContent").remove();
	$(".activity_list").children(".activity_item").show();
	$("#more").show();
}
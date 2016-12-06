$(document).ready(function(){
	index = 1;
	showActivityList(index);
})

function showActivityList(index){
	var url="/ClubSystem/activity/getAlls";
	var parm = "indexPageNum="+index+"&size=9";
	$.get(url, parm, function(data){
		if (data.serviceResult) {
			var str="";
			var lsit = data.resultParm.activity;
			for (var i = 0; i <lsit.length; i++) {
				str+="<li><div class='activity-title'><a href='clubDetail.html/clubId="+lsit[i].clubId+"&activityId="+lsit[i].id+"'>"+lsit[i].title+"</a></div>";
				str+="<div class='activity-club'><a href='clubDetail.html/clubId="+lsit[i].clubId+"'>"+lsit[i].clubName+"</a></div>";
				str+="<div class='activity-time'><a href='clubDetail.html/clubId="+lsit[i].clubId+"'>"+lsit[i].createtime+"</a></div></li>";
			}
			$(".activity .activityList").append(str);
		}else{
			alert(data.resultInfo);
		}
	})
}
function getActivityList(key){
	switch(key){
		case "1":console.log("全部");break;
		case "2":console.log("最新");break;
		default: console.log(key);break;
	}
}
function selectChange(){
	getActivityList($('.activity-select option:selected').val());
}

function searchActivity() {
	var key = $("input[name='key']").val();
	getActivityList(key);
}
function loadMoreActivity() {
	index++;
	showActivityList(index);
}
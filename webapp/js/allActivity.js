$(document).ready(function(){
	showActivityList(1);
})

function showActivityList(index){
	var url="/ClubSystem/activity/getAlls";
	var parm = "indexPageNum="+index+"&size=9";
	$.get(url, parm, function(data){
		if (data.serviceResult) {
			var str="";
			var total = data.resultParm.total;
			var listData = data.resultParm.activity;
			for (var i = 0; i <listData.length; i++) {
				str+="<li><div class='activity-title'><a href='clubDetail.html?clubId="+listData[i].clubId+"&activityId="+listData[i].id+"'>"+listData[i].title+"</a></div>";
				str+="<div class='activity-club'><a href='clubDetail.html?clubId="+listData[i].clubId+"'>"+listData[i].clubName+"</a></div>";
				str+="<div class='activity-school'><a href='clubDetail.html?clubId="+listData[i].clubId+"'>"+listData[i].schoolName+"</a></div>";
				var time = listData[i].createtime.split(" ")[0];
				str+="<div class='activity-time'><a href='clubDetail.html?clubId="+listData[i].clubId+"'>"+time+"</a></div></li>";
			}
			$(".activity .activityList").append(str);
			if (listData.length<9||$(".activityList li").length==total) {
				$("#loadMoreActivity").html("已经没有了！").attr("onclick","javascipt:void(0);");
				return;
			}
		}
	})
}
// function getActivityList(key){
// 	switch(key){
// 		case "1":console.log("全部");break;
// 		case "2":console.log("最新");break;
// 		default: console.log(key);break;
// 	}
// }
// function selectChange(){
// 	getActivityList($('.activity-select option:selected').val());
// }

function searchActivity() {
	var key = $("input[name='key']").val();
	// getActivityList(key);
}
function loadMoreActivity(index) {
	showActivityList(index);
	$("#loadMoreActivity").attr("onclick","loadMoreActivity("+(++index)+")");
}
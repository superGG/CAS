$(document).ready(function () {
	getClubList();
})
//获取社团列表
function getClubList() {
	var listDate = {"clubs":[{"title":"网球社","proprieter":"张三","population":"50","position":"广东海洋大学","pic":"../../images/001.jpg"},
							{"title":"篮球社","proprieter":"李四","population":"20","position":"广州大学","pic":"../../images/002.png"},
							{"title":"足球社","proprieter":"王五","population":"30","position":"广东海洋大学","pic":"../../images/003.jpg"},
							{"title":"排球社","proprieter":"小七","population":"100","position":"广东海洋大学","pic":"../../images/004.jpg"},
							{"title":"乒乓球社","proprieter":"吴六","population":"33","position":"广东海洋大学","pic":"../../images/001.jpg"},
							{"title":"散打社","proprieter":"老九","population":"50","position":"广东海洋大学","pic":"../../images/003.jpg"}]};
	showClubList(listDate);
}
//显示社团列表
function showClubList(listDate) {
	var str = "";
	for (var i = 0; i <listDate.clubs.length; i++) {
		str+="<li class=\"clubBox\"><a href=\"javascript:\"><div class=\"imgBox\"><img src=\""+listDate.clubs[i].pic+"\"></div>";
		str+="<div class=\"info-area\"><h3>"+listDate.clubs[i].title+"</h3>";
		str+="<span class=\"proprieter\">"+listDate.clubs[i].proprieter+"</span>";
		str+="<span class=\"associator\">"+listDate.clubs[i].population+"</span>";
		str+="<span class=\"position\">"+listDate.clubs[i].position+"</span></div></a></li>";
	}
	$("#clubList").append(str);
}
//加载社团
function loadMore(index) {
	var listDate = {"clubs":[{"title":"网球社","proprieter":"张三","population":"50","position":"广东海洋大学","pic":"../../images/001.jpg"},
							{"title":"篮球社","proprieter":"李四","population":"20","position":"广州大学","pic":"../../images/002.png"},
							{"title":"足球社","proprieter":"王五","population":"30","position":"广东海洋大学","pic":"../../images/003.jpg"},
							{"title":"排球社","proprieter":"小七","population":"100","position":"广东海洋大学","pic":"../../images/004.jpg"},
							{"title":"乒乓球社","proprieter":"吴六","population":"33","position":"广东海洋大学","pic":"../../images/001.jpg"},
							{"title":"散打社","proprieter":"老九","population":"50","position":"广东海洋大学","pic":"../../images/003.jpg"}]};
	showClubList(listDate);
	$(".loadMore").attr("onclick","loadMore("+(index+1)+")");
}


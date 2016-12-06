$(document).ready(function () {
	getClubList();
})
//获取社团列表
function getClubList() {
	var listDate = {"clubs":[{"clubId":"1","title":"网球社","proprieter":"张三","population":"50","position":"广东海洋大学","pic":"../../images/001.jpg"},
							{"clubId":"1","title":"篮球社","proprieter":"李四","population":"20","position":"广州大学","pic":"../../images/002.png"},
							{"clubId":"1","title":"足球社","proprieter":"王五","population":"30","position":"广东海洋大学","pic":"../../images/003.jpg"},
							{"clubId":"1","title":"排球社","proprieter":"小七","population":"100","position":"广东海洋大学","pic":"../../images/004.jpg"},
							{"clubId":"1","title":"乒乓球社","proprieter":"吴六","population":"33","position":"广东海洋大学","pic":"../../images/001.jpg"},
							{"clubId":"1","title":"散打社","proprieter":"老九","population":"50","position":"广东海洋大学","pic":"../../images/003.jpg"}]};
	showClubList(listDate);
}
//显示社团列表
function showClubList(listDate) {
	var str = "";
	for (var i = 0; i <listDate.clubs.length; i++) {
		str+="<li class=\"clubBox\"><a href=\"clubDetail.html?clubId="+listDate.clubs[i].clubId+"\"><div class=\"imgBox\"><img src=\""+listDate.clubs[i].pic+"\"></div>";
		str+="<div class=\"info-area\"><h3>"+listDate.clubs[i].title+"</h3>";
		str+="<span class=\"proprieter\">"+listDate.clubs[i].proprieter+"</span>";
		str+="<span class=\"associator\">"+listDate.clubs[i].population+"</span>";
		str+="<span class=\"position\">"+listDate.clubs[i].position+"</span></div></a></li>";
	}
	$("#clubList").append(str);
}
//加载社团
function loadMore(index) {
	var listDate = {"clubs":[{"clubId":"1","title":"网球社","proprieter":"张三","population":"50","position":"广东海洋大学","pic":"../../images/001.jpg"},
							{"clubId":"1","title":"篮球社","proprieter":"李四","population":"20","position":"广州大学","pic":"../../images/002.png"},
							{"clubId":"1","title":"足球社","proprieter":"王五","population":"30","position":"广东海洋大学","pic":"../../images/003.jpg"},
							{"clubId":"1","title":"排球社","proprieter":"小七","population":"100","position":"广东海洋大学","pic":"../../images/004.jpg"},
							{"clubId":"1","title":"乒乓球社","proprieter":"吴六","population":"33","position":"广东海洋大学","pic":"../../images/001.jpg"},
							{"clubId":"1","title":"散打社","proprieter":"老九","population":"50","position":"广东海洋大学","pic":"../../images/003.jpg"}]};
	showClubList(listDate);
	$(".loadMore").attr("onclick","loadMore("+(index+1)+")");
}

//获取全部社团类型
function showClubType(){
	var url="/ClubSystem/clubtype/getAlls";
	$.get(url, function(data){
		if (data.serviceResult) {
			var list = data.resultParm.clubType;
			var str="";
			for (var i = 0; i <list.length; i++) {
				str+="<li><a href='javascript:void(0);' onclick='findClubByType("+list[i].id+")'>"+list[i].name+"</a></li>"
			}
			$(".club-type-list").html(str);
		}
	})
}

//通过类型查找社团
function findClubByType(tpyeId){
	var url="/ClubSystem/clubs/getClubListByType"
	var parm = "clubType="+tpyeId;
	$.get(url, parm, function(data){
		if (data.serviceResult) {
			var list=data.resultParm.clubs;
			var str="";
			for (var i = 0; i <list.length; i++) {
				str+="<li class=\"clubBox\"><a href=\"clubDetail.html?clubId="+list[i].clubId+"\"><div class=\"imgBox\"><img src=\""+list[i].pic+"\"></div>";
				str+="<div class=\"info-area\"><h3>"+list[i].title+"</h3>";
				str+="<span class=\"proprieter\">"+list[i].proprieter+"</span>";
				str+="<span class=\"associator\">"+list[i].population+"</span>";
				str+="<span class=\"position\">"+list[i].position+"</span></div></a></li>";
			}
			$("#clubList").html(str);
		}
	})
}

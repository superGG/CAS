$(document).ready(function () {
	getClubList(1);
	showClubType();
})
//获取社团列表
function getClubList(index) {
	var url="/ClubSystem/club/getAlls";
	var parm="size=6&indexPageNum="+index;
	$.get(url, parm, function (data) {
		console.log(data);
		if (data.serviceResult) {
			var listData = data.resultParm.club;
			showClubList(listData);
		}
	})
}
//显示社团列表
function showClubList(listData) {
	if (listData.length==0) {
		$(".loadMore").html("已经没有了！").attr("onclick","javascipt:void(0);");
		return;
	}
	var str = "";
	for (var i = 0; i <listData.length; i++) {
		var color=""
		switch(i%6){
			case 0:color="myblue" ;break;
			case 1:color="mygreen" ;break;
			case 2:color="myred" ;break;
			case 3:color="myoringe" ;break;
			case 4:color="myyellow" ;break;
			default:color="myzise" ;break;
		}
		str+="<li class=\"clubBox "+color+"\"><a href=\"club/clubDetail.html?clubId="+listData[i].id+"\"><div class=\"imgBox\"><img src=\"/ClubSystem"+listData[i].badge+"\"></div>";
		str+="<div class=\"info-area\"><div class='info-area_box'><h3>"+listData[i].name+"</h3>";
		str+="<span class=\"proprieter\">"+listData[i].leader+"</span>";
		str+="<span class=\"associator\">"+listData[i].number+"</span>";
		str+="<span class=\"position\">"+listData[i].schoolName+"</span></div></div></a></li>";
	}
	$("#clubList").append(str);
}
//加载社团
function loadMore(index) {
	getClubList(index);
	$(".loadMore").attr("onclick","loadMore("+(index+1)+")");
}

//获取全部社团类型
function showClubType(){
	var url="/ClubSystem/clubtype/getAlls";
	$.get(url, function(data){
		if (data.serviceResult) {
			var listData = data.resultParm.clubType;
			var str="";
			for (var i = 0; i <listData.length; i++) {
				str+="<li><a href='javascript:void(0)' onclick=\"findClubByType('"+listData[i].name+"',1)\">"+listData[i].name+"</a></li>"
			}
			$(".club-type-list").html(str);
		}
	})
}

//通过类型查找社团
function findClubByType(tpyeName,index){
	var url="/ClubSystem/club/getByTypeName"
	var parm = "typeName="+tpyeName+"&size=6&indexPageNum="+index;
	$.get(url, parm, function(data){
		console.log(data);
		if (data.serviceResult) {
			var listData=data.resultParm.clublist;
			var str="";
			if (listData.length==0) {
				$(".loadMore").html("已经没有了！").attr("onclick","javascipt:void(0);");
			};
			for (var i = 0; i <listData.length; i++) {
				var color=""
				switch(i%6){
					case 0:color="myblue" ;break;
					case 1:color="mygreen" ;break;
					case 2:color="myred" ;break;
					case 3:color="myoringe" ;break;
					case 4:color="myyellow" ;break;
					default:color="myzise" ;break;
				}
				str+="<li class=\"clubBox "+color+"\"><a href=\"club/clubDetail.html?clubId="+listData[i].id+"\"><div class=\"imgBox\"><img src=\"/ClubSystem"+listData[i].badge+"\"></div>";
				str+="<div class=\"info-area\"><div class='info-area_box'><h3>"+listData[i].name+"</h3>";
				str+="<span class=\"proprieter\">"+listData[i].leader+"</span>";
				str+="<span class=\"associator\">"+listData[i].number+"</span>";
				str+="<span class=\"position\">"+listData[i].schoolName+"</span></div></div></a></li>";
			}
			if (index==1) {
				$("#clubList").html(str);
			}else{
				$("#clubList").append(str);
			}
			$(".loadMore").attr("onclick","findClubByType(\""+tpyeName+"\","+(++index)+")");
		}
	})
}

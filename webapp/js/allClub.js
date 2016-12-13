$(document).ready(function() {
		getClubList(1);
		showClubType();
	})
	//获取社团列表
function getClubList(index) {
	var url = "/ClubSystem/club/getAlls";
	var parm = "size=6&indexPageNum=" + index;
	$.get(url, parm, function(data) {
		if (data.serviceResult) {
			var listData = data.resultParm.club;
			showClubList(listData);
		}
	})
}
//显示社团列表
function showClubList(listData) {
	var str = "";
	for (var i = 0; i < listData.length; i++) {
		var color = ""
		switch (i % 6) {
			case 0:
				color = "myblue";
				break;
			case 1:
				color = "mygreen";
				break;
			case 2:
				color = "myred";
				break;
			case 3:
				color = "myoringe";
				break;
			case 4:
				color = "myyellow";
				break;
			default:
				color = "myzise";
				break;
		}
		str += "<li class=\"clubBox " + color + "\"><a href=\"clubDetail.html?clubId=" + listData[i].id + "\"><div class=\"imgBox\"><img src=\"/ClubSystem" + listData[i].badge + "\"></div>";
		str += "<div class=\"info-area\"><div class='info-area_box'><h3>" + listData[i].name + "</h3>";
		str += "<span class=\"proprieter\">" + listData[i].leader + "</span>";
		str += "<span class=\"associator\">" + listData[i].number + "</span>";
		str += "<span class=\"position\">" + listData[i].schoolName + "</span></div></div></a></li>";
	}
	$("#clubList").append(str);
	if (listData.length == 0) {
		$(".loadMore").html("没有数据").attr("onclick", "javascipt:void(0);");
		return;
	}
	if (listData.length < 6) {
		$(".loadMore").html("已经没有了！").attr("onclick", "javascipt:void(0);");
		return;
	}
}
//加载社团
function loadMore(index) {
	getClubList(index);
	$(".loadMore").attr("onclick", "loadMore(" + (index + 1) + ")");
}

//获取全部社团类型
function showClubType() {
	var url = "/ClubSystem/clubtype/getAlls";
	$.get(url, function(data) {
		if (data.serviceResult) {
			var listData = data.resultParm.clubType;
			var str = "";
			for (var i = 0; i < listData.length; i++) {
				str += "<li><a href='javascript:void(0)' onclick=\"findClubByType('" + listData[i].name + "',1)\">" + listData[i].name + "</a></li>"
			}
			$(".club-type-list").html(str);
		}
	})
}

//通过类型查找社团
function findClubByType(tpyeName, index) {
	var url = "/ClubSystem/club/getByTypeName"
	var parm = "typeName=" + tpyeName + "&size=6&indexPageNum=" + index;
	$.get(url, parm, function(data) {
		if (data.serviceResult) {
			var listData = data.resultParm.clublist;
			var str = "";
			for (var i = 0; i < listData.length; i++) {
				var color = ""
				switch (i % 6) {
					case 0:
						color = "myblue";
						break;
					case 1:
						color = "mygreen";
						break;
					case 2:
						color = "myred";
						break;
					case 3:
						color = "myoringe";
						break;
					case 4:
						color = "myyellow";
						break;
					default:
						color = "myzise";
						break;
				}
				str += "<li class=\"clubBox " + color + "\"><a href=\"clubDetail.html?clubId=" + listData[i].id + "\"><div class=\"imgBox\"><img src=\"/ClubSystem" + listData[i].badge + "\"></div>";
				str += "<div class=\"info-area\"><div class='info-area_box'><h3>" + listData[i].name + "</h3>";
				str += "<span class=\"proprieter\">" + listData[i].leader + "</span>";
				str += "<span class=\"associator\">" + listData[i].number + "</span>";
				str += "<span class=\"position\">" + listData[i].schoolName + "</span></div></div></a></li>";
			}
			if (index == 1) {
				$("#clubList").html(str);
			} else {
				$("#clubList").append(str);
			}
			if (listData.length == 0) {
				$(".loadMore").html("没有数据").attr("onclick", "javascipt:void(0);");
				return;
			}
			if (listData.length < 6) {
				$(".loadMore").html("已经没有了！").attr("onclick", "javascipt:void(0);");
				return;
			} else {
				$(".loadMore").attr("onclick", "findClubByType(\"" + tpyeName + "\"," + (++index) + ")").html("加载更多");
			}
		}
	})
}
//关键词搜索
function keySearch(index) {
	//排除输入框内容有变化但通过加载更多来获取数据的造成混乱
	if (index == 1) {
		key = $(".club-search input[name='key']").val();
	}
	if (key == "") {
		$("#clubList").html(""); //清空面板
		getClubList(1);
		$(".loadMore").attr("onclick", "loadMore(2)").html("加载更多");
		return;
	}
	var url = "/ClubSystem/club/getBySearch";
	var parm = "search=" + html_encode(key) + "&size=6&indexPageNum=" + index;
	$.get(url, parm, function(data) {
		if (data.serviceResult) {
			var listData = data.resultParm.clublist;
			var str = "";
			for (var i = 0; i < listData.length; i++) {
				var color = ""
				switch (i % 6) {
					case 0:
						color = "myblue";
						break;
					case 1:
						color = "mygreen";
						break;
					case 2:
						color = "myred";
						break;
					case 3:
						color = "myoringe";
						break;
					case 4:
						color = "myyellow";
						break;
					default:
						color = "myzise";
						break;
				}
				str += "<li class=\"clubBox " + color + "\"><a href=\"clubDetail.html?clubId=" + listData[i].id + "\"><div class=\"imgBox\"><img src=\"/ClubSystem" + listData[i].badge + "\"></div>";
				str += "<div class=\"info-area\"><div class='info-area_box'><h3>" + listData[i].name + "</h3>";
				str += "<span class=\"proprieter\">" + listData[i].leader + "</span>";
				str += "<span class=\"associator\">" + listData[i].number + "</span>";
				str += "<span class=\"position\">" + listData[i].schoolName + "</span></div></div></a></li>";
			}
			if (index == 1) {
				$("#clubList").html(str);
			} else {
				$("#clubList").append(str);
			}
			if (listData.length==0) {
				$(".loadMore").html("没有数据").attr("onclick","javascipt:void(0);");
				return;
			}
			if (listData.length < 6) {
				$(".loadMore").html("已经没有了！").attr("onclick", "javascipt:void(0);");
				return;
			} else {
				$(".loadMore").attr("onclick", "keySearch(" + (++index) + ")").html("加载更多");
			}
		}
	})
}
//获取最新社团,页面默认为最新的排序
function getNewClubList() {
	$("#clubList").html(""); //清空面板
	getClubList(1);
	$(".loadMore").attr("onclick", "loadMore(2)").html("加载更多");
	return;
}
//获取热门社团按人数
function getClubListByNum(index) {
	$(".loadMore").attr("onclick", "getClubListByNum(2)").html("加载更多");
	var url = "/ClubSystem/club/getAllsByRank";
	var parm = "size=6&indexPageNum=" + index;
	$.get(url, parm, function(data) {
		if (data.serviceResult) {
			var listData = data.resultParm.club;
			var str = "";
			for (var i = 0; i < listData.length; i++) {
				var color = ""
				switch (i % 6) {
					case 0:
						color = "myblue";
						break;
					case 1:
						color = "mygreen";
						break;
					case 2:
						color = "myred";
						break;
					case 3:
						color = "myoringe";
						break;
					case 4:
						color = "myyellow";
						break;
					default:
						color = "myzise";
						break;
				}
				str += "<li class=\"clubBox " + color + "\"><a href=\"clubDetail.html?clubId=" + listData[i].id + "\"><div class=\"imgBox\"><img src=\"/ClubSystem" + listData[i].badge + "\"></div>";
				str += "<div class=\"info-area\"><div class='info-area_box'><h3>" + listData[i].name + "</h3>";
				str += "<span class=\"proprieter\">" + listData[i].leader + "</span>";
				str += "<span class=\"associator\">" + listData[i].number + "</span>";
				str += "<span class=\"position\">" + listData[i].schoolName + "</span></div></div></a></li>";
			}
			if (index == 1) {
				$("#clubList").html(str);
			} else {
				$("#clubList").append(str);
			}
			if (listData.length==0) {
				$(".loadMore").html("没有数据").attr("onclick","javascipt:void(0);");
				return;
			}
			if (listData.length < 6) {
				$(".loadMore").html("已经没有了！").attr("onclick", "javascipt:void(0);");
				return;
			} else {
				$(".loadMore").attr("onclick", "getClubListByNum(" + (++index) + ")").html("加载更多");
			}
		}
	});
}
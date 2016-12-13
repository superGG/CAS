$(document).ready(function(){
	getPhotoList();
	initClubNav();
	showActiviity();
})

//获取照片墙资源
function getPhotoList() {
	var imgList = ["../images/PhotoWall/PhotoWall0.png","../images/PhotoWall/PhotoWall1.png",
					"../images/PhotoWall/PhotoWall2.png","../images/PhotoWall/PhotoWall3.png"];
	initPhotoWall(imgList);//初始化照片墙
	playPhotoWall(0);//滚动照片墙
	playByBtn();//初始化照片按钮
}
//初始化照片墙
function initPhotoWall(imgList) {
	var strPhoto= "";
	var strBtn = "";
	showindex = imgList.length+2;
	//生成照片墙元素
	strPhoto+="<li style=\"display:block;z-index:"+showindex+";opacity:1;\"><a href=\"javascript:void(0)\"><img src=\""+imgList[0]+"\"></a></li>";
	strBtn+="<li><a href=\"javascript:void(0)\" class=\"btnActive\"></a></li>";
	for (var i = 1; i <imgList.length; i++) {
		strPhoto+="<li style=\"display:none;z-index:"+(imgList.length-i)+";opacity:0;\"><a href=\"javascript:void(0)\"><img src=\""+imgList[i]+"\"></a></li>";
		strBtn+="<li><a href=\"javascript:\"></a></li>";
	}
	$("#photoWall").prepend(strPhoto);
	$("#photoWall>.photoBtn").append(strBtn);
}

//滚动照片
function playPhotoWall(index){
	var i = index;
	play = setInterval(function () {

		$("#photoWall>li").eq(i).css({"z-index":showindex-1}).animate({opacity:'0'},1000,function(){
			$(this).css("display","none");
		});
		$("#photoWall>.photoBtn>li>a").eq(i).removeClass("btnActive");
		if (i==showindex-3) {
			i=-1;
		}
		$("#photoWall>li").eq(i+1).css({"z-index":showindex,"display":"block"}).animate({opacity:'1'},1000);
		$("#photoWall>.photoBtn>li>a").eq(i+1).addClass("btnActive");
		i++;
	},3000);
}

//通过按钮切换照片
function playByBtn(){
	var index = 0;//记录当前按钮位置
	$(".photoBtn>li>a").mouseover(function() {
		clearInterval(play);//清除照片墙定时器
		index = $(this).parent().index();//当前按钮位置
		var imgAcive = $(".photoBtn>li>a.btnActive").parent().index();//当前显示图片位置
		$("#photoWall>li").eq(imgAcive).css({"z-index":showindex-1}).stop(true,false).animate({opacity:'0'},1000,function(){
			$(this).css("display","none");
		});
		$("#photoWall>.photoBtn>li>a").eq(imgAcive).removeClass("btnActive");
		$("#photoWall>li").eq(index).css({"z-index":showindex,"display":"block"}).stop(true,false).animate({opacity:'1'},1000);
		$("#photoWall>.photoBtn>li>a").eq(index).addClass("btnActive");
	}).mouseout(function() {
		playPhotoWall(index);//完成点击,从当前位置开始循环滚动
	})
}

//初始化滚动条
function initClubNav() {
	var url="/ClubSystem/school/getAlls";
	$.get(url, function (data) {
		if (data.serviceResult) {
			var listDate = data.resultParm.school;
			var str="";
			for (var i=0; i <listDate.length; i++) {
				str+="<li><a href=\"javascript:\">"+listDate[i].name+"</a></li>";
			}
			$("#orgList>ul").append(str);
			initClubNavBtn();//初始化点击事件
		}
	});
	// var list = ["广东海洋大学","岭南师范大学","广东海洋大学寸金学院","广东医学院","广州大学","广东工业大学","东莞理工学院","肇庆学院"];
	// var str="";
	// for (var i=0; i <list.length; i++) {
	// 	str+="<li><a href=\"javascript:\">"+list[i]+"</a></li>";
	// }
	// $("#orgList>ul").append(str);
	// initClubNavBtn();//初始化点击事件
}
//点击向后滚动组织栏
function nextBtn() {
	var moveLength =$("#orgList>ul>li:first").outerWidth();
	$("#orgList>ul").stop(true,false).animate({"left":-moveLength},'slow',function(){
		$(this).css("left","0");
		$("#orgList>ul>li:first").appendTo($("#orgList>ul"));
	});
}
//点击向前滚动组织栏
function prevBtn() {
	var moveLength =$("#orgList>ul>li:last").outerWidth();
	$("#orgList>ul").css("left",-moveLength);
	$("#orgList>ul>li:last").prependTo($("#orgList>ul"));
	$("#orgList>ul").stop(true,false).animate({"left":0},'slow');
}
//点击加载社团
function initClubNavBtn() {
	$(".clubNavBox a").click(function(){
		$(".clubNavBox a.checked").removeClass("checked");
		$(this).addClass("checked");
		if ($(this).html()=="全部") {
			//显示全部
			//获取数据
			var url="/ClubSystem/club/getAlls";
			var parm = "indexPageNum=1&size=6";
			$.get(url,parm,function (data) {
				if (data.serviceResult) {
					var listDate = data.resultParm.club;
					showClubList(listDate);
				}
			});
			// var listDate = {"clubs":[{"title":"网球社","proprieter":"张三","population":"50","position":"广东海洋大学","pic":"../images/001.jpg"},
			// 				{"title":"篮球社","proprieter":"李四","population":"20","position":"广州大学","pic":"../images/002.png"},
			// 				{"title":"足球社","proprieter":"王五","population":"30","position":"广东海洋大学","pic":"../images/003.jpg"},
			// 				{"title":"排球社","proprieter":"小七","population":"100","position":"广东海洋大学","pic":"../images/004.jpg"},
			// 				{"title":"乒乓球社","proprieter":"吴六","population":"33","position":"广东海洋大学","pic":"../images/001.jpg"},
			// 				{"title":"散打社","proprieter":"老九","population":"50","position":"广东海洋大学","pic":"../images/003.jpg"}]};
			// showClubList(listDate);
		}else{
			//显示指定
			var url="/ClubSystem/club/getBySchoolName";
			var parm = "schoolName="+$(this).html()+"&size=6&indexPageNum=1";
			$.get(url, parm, function (data) {
				if (data.serviceResult) {
					var listDate = data.resultParm.clublist;
					showClubList(listDate);
				}
			});
			// var listDate = {"clubs":[{"title":"网球社","proprieter":"张三","population":"50","position":"广东海洋大学","pic":"../images/001.jpg"}]};
			// showClubList(listDate);
		}
	});
	$("#all a").trigger("click");//显示全部
}
//显示社团列表
function showClubList(listDate) {
	var str = "";
	for (var i = 0; i <listDate.length; i++) {
		str+="<li class=\"clubBox\"><a href=\"club/clubDetail.html?clubId="+listDate[i].id+"\"><div class=\"imgBox\"><img src=\"/ClubSystem"+listDate[i].badge+"\"></div>";
		str+="<div class=\"info-area\"><div class='info-area_box'><h3>"+listDate[i].name+"</h3>";
		str+="<span class=\"proprieter\">"+listDate[i].leader+"</span>";
		str+="<span class=\"associator\">"+listDate[i].number+"</span>";
		str+="<span class=\"position\">"+listDate[i].schoolName+"</span></div></div></a></li>";
	}
	$("#clubList").html(str);
}

//显示活动列表
function showActiviity(){
	var url = "/ClubSystem/activity/getAlls";
	var parm ="indexPageNum=1&size=6";
	$.get(url, parm, function (data) {
		if (data.serviceResult) {
			var listData = data.resultParm.activity;
			var str = "";
			for (var i = 0; i <listData.length; i++) {
				str+="<li><span class=\"active-title\"><a href=\"club/clubDetail.html?clubId="+listData[i].clubId+"&activityId="+listData[i].id+"\">"+listData[i].title+"</a></span>";
				str+="<span class=\"active-club\"><a href=\"club/clubDetail.html?clubId="+listData[i].clubId+"\">"+listData[i].clubName+"</a></span>";
				str+="<span class=\"active-school\"><a href=\"club/clubDetail.html?clubId="+listData[i].clubId+"\">"+listData[i].schoolName+"</a></span>";
				var time = listData[i].createtime.split(" ")[0];
				str+="<span class=\"active-time\">"+time+"</span></li>";
			}
			$("#activeList ul").html(str);
		}
		// body...
	});
	// var listDate = {"activitys":[{"title":"网球比赛","club":"网球社","time":"2016-11-22"},
	// 						{"title":"篮球比赛","club":"篮球社","time":"2016-11-22"},
	// 						{"title":"足球社比赛","club":"足球社","time":"2016-11-22"},
	// 						{"title":"排球社比赛","club":"排球社","time":"2016-11-22"},
	// 						{"title":"乒乓球比赛","club":"乒乓球社","time":"2016-11-22"},
	// 						{"title":"散打社比赛","club":"散打社","time":"2016-11-22"}]};
	// var str = "";
	// for (var i = 0; i <listDate.activitys.length; i++) {
	// 	str+="<li><span class=\"active-title\"><a href=\""+""+"\">"+listDate.activitys[i].title+"</a></span>";
	// 	str+="<span class=\"active-club\"><a href=\""+""+"\">"+listDate.activitys[i].club+"</a></span>";
	// 	str+="<span class=\"active-time\">"+listDate.activitys[i].time+"</span></li>";
	// }
	// $("#activeList ul").html(str);
}


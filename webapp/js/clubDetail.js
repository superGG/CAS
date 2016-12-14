$(document).ready(function () {
	intiClubDtail();
})
$(window).resize(function(){
    if ($("#applyFrame").length>0) {
      setFrameWH("applyFrame") ;
    }
    if ($("#photoFrame").length>0) {
    	setFrameWH("photoFrame");
    }
});
function intiClubDtail(){
	userData = getCookieUserData();//提取用户信息;
	intiClubDetailBtn();
	parm = window.location.search.split("?")[1];
	var url = "/ClubSystem/club/getById";
	$.get(url,parm,function(data){
		if (data.serviceResult) {
			var str="";
			var clubData = data.resultParm.club;
			clubId=clubData.id;//全局变量给申请表用
			clubName=clubData.name;
			schoolName=clubData.schoolName;
			$(".club-logo img").attr("src","/ClubSystem/"+clubData.badge);
			$(".club-info h3").html(clubData.name);
			$(".club-info .club-style").html(clubData.typeName);
			$(".club-info .club-school").html(clubData.schoolName);
			$(".club-info .club-count").html(clubData.number);
			$(".club-info .club-leader").html(clubData.leader);
			$(".club-info .club-phone").html(clubData.phone);
			$(".club-info .club-email").html(clubData.email);
			$(".clubDetai-content .club-introduction p").html(clubData.introduce);
			isApplyOrJoin(clubId);//判断是否已加入社团或提交了申请
		}
	});
	//判断是否是进入活动
	if (parm.indexOf("activityId")!=-1) {
		var activityId=parm.split("activityId=")[1];
		//跳转到activiy-tab;
		$(".clubDetai-bar ul li.active").removeClass("active");
		$(".activity-tab").addClass("active");
		$(".clubDetai-bar ul .arrows").css("left","47.3%");
		$(".clubDetai-content .club-introduction").hide();
		var str = "<div class='club-activity'><ul></ul></div>";
		$(".clubDetai-content div").not(".club-introduction").remove();
		$(".clubDetai-content").append(str);	
		if (activityId!="") {
			activityId=activityId.split("&")[0];
			showActivity(activityId);
		}
	}
}
function intiClubDetailBtn(){
	$(".apply-btn").click(function(){
		showApplyTable();
	});
	$(".info-tab").click(function (){
		$(".clubDetai-bar ul li.active").removeClass("active");
		$(this).addClass("active");
		$(".clubDetai-bar ul .arrows").css("left","14.3%");
		$(".clubDetai-content div").not(".club-introduction").remove();
		$(".clubDetai-content .club-introduction").show();
	});
	$(".activity-tab").click(function (){
		$(".clubDetai-bar ul li.active").removeClass("active");
		$(this).addClass("active");
		$(".clubDetai-bar ul .arrows").css("left","47.3%");
		$(".clubDetai-content .club-introduction").hide();
		var str = "<div class='club-activity'><ul></ul><div class='loadMoreActivity' onclick='showClubActivity(2)'>加载更多</div></div>";
		$(".clubDetai-content div").not(".club-introduction").remove();
		$(".clubDetai-content").append(str);
		showClubActivity(1);
	});
	// $(".member-tab").click(function (){
	// 	$(".clubDetai-bar ul li.active").removeClass("active");
	// 	$(this).addClass("active");
	// 	$(".clubDetai-bar ul .arrows").css("left","61%");
	// 	$(".clubDetai-content .club-introduction").hide();
	// 	showClubMember();
	// });
	$(".album-tab").click(function (){
		$(".clubDetai-bar ul li.active").removeClass("active");
		$(this).addClass("active");
		$(".clubDetai-bar ul .arrows").css("left","80.3%");
		$(".clubDetai-content .club-introduction").hide();
		showClubAlbum();
	});
}

function showClubActivity(index) {
	var url="/ClubSystem/activity/getClubActivity";
	var mparm = parm+"&indexPageNum="+index+"&size=6";
	$.get(url, mparm, function (data) {
		if (data.serviceResult) {
			var total = data.resultParm.total;
			var listData = data.resultParm.activity;
			var str="";
			for (var i = 0; i <listData.length; i++) {
				str+="<li><span class=\"active-title\"><a href=\"clubDetail.html?clubId="+listData[i].clubId+"&activityId="+listData[i].id+"\">"+html_decode(listData[i].title)+"</a></span>";
				var time = listData[i].createtime.split(" ")[0];
				str+="<span class=\"active-time\">"+time+"</span></li>";
			}
			if (index==1) {
				$(".club-activity ul").html(str);
			}else{
				$(".club-activity ul").append(str);
			}
			if (listData.length<6) {
				$(".loadMoreActivity").attr("onclick","javascript:void(0)").css({"background-color":"#ccc","cursor":"default"}).html("已经没了！");
			}else{
				$(".loadMoreActivity").attr("onclick","showClubActivity("+(++index)+")").html("加载更多");
			}
		}else{
			$(".loadMoreActivity").attr("onclick","javascript:void(0)").css({"background-color":"#ccc","cursor":"default"}).html("没有数据");
		}
	})
}

function showActivity(activityId) {
	var url="/ClubSystem/activity/getDetails";
	var mparm = "id="+activityId;
	$.get(url, mparm, function (data) {
		if (data.serviceResult) {
			var activityData = data.resultParm.activity;
			var str='<div class="activity_detail">'+html_decode(activityData.content)+'</div>';
			$(".club-activity").html(str);	
		}
	})
}
// function showClubMember(clubId) {
// 	var str = "<div class='club-member'>成员</div>";
// 	$(".clubDetai-content div").not(".club-introduction").remove();
// 	$(".clubDetai-content").append(str);
// }
function showClubAlbum(clubId) {
	var str = "<div class='club-album'></div>";
	$(".clubDetai-content div").not(".club-introduction").remove();
	$(".clubDetai-content").append(str);
	getClubAlbumList(clubId);
}

function getClubAlbumList(clubId){
	var url = "/ClubSystem/album/getByClub";
	var mparm = parm.split("&")[0];
	mparm ="id="+mparm.split("=")[1];
	$.get(url, mparm, function (data) {
		if (data.serviceResult) {
			var listData = data.resultParm.albumList;
			showClubAlbumList(listData);
		}
	})
}
function showClubAlbumList(albumList){
	var str="<ul>";
	if (albumList.length==0) {
		str+="<h3 style='text-align:center;'>没有相册</h3>";
	}else{
		for (var i = 0; i < albumList.length; i++) {
			str+="<li><div class='club-album-box' data-albumid='"+albumList[i].id+"' data-clubid='"+albumList[i].clubId+"' data-albumname='"+albumList[i].name+"' data-albumpath='"+albumList[i].path+"' data-photocount='"+albumList[i].photoNumber+"'>";
			str+="<img src='/ClubSystem/"+albumList[i].path+"'>";
			str+="<span class='photo-count'>"+albumList[i].photoNumber+"</span><span>"+albumList[i].name+"</span></div></li>";
		}
	}
	str+="<ul>";
	$(".clubDetai-content .club-album").append(str);
	$(".club-album-box img").click(function(){
		var dataSet = document.querySelectorAll(".club-album-box")[$(this).parents("li").index()].dataset;
		var url = "/ClubSystem/photo/getAllAlbumPhoto";
		var mparm = "albumId="+dataSet.albumid;
		$.get(url, mparm, function (data) {
			if (data.serviceResult) {
				showPhotoList(data.resultParm.photoList,dataSet);
			}
		})
	});
}
function showPhotoList(photoList,dataSet){
	var pStr="<div class='photo_list'><div class='photo_list_hd'><div class='photo_list_hd_albumImg'>";
    pStr+="<img src='/ClubSystem/"+dataSet.albumpath+"'></div>";
    pStr+="<div class='photo_list_hd_albumName'>"+dataSet.albumname+"</div></div>";
    pStr+="<div class='photo_list_bd'><ul>";
    if (photoList.length==0){
    	pStr+= "<h3 style='text-align:center;'>没有照片</h3>";
    }else{
    	for (var i =0; i <photoList.length; i++) {
    		pStr+= "<li><img src='/ClubSystem/"+photoList[i].path+"'></li>";
    	}
    }
    pStr+="</ul></div></div>";
    $(".club-album").html(pStr);
    $(".photo_list_bd ul li img").click(function(){
    	$(".photo_list_bd ul li.photo_acive").removeClass("photo_acive");
    	$(this).parent().addClass("photo_acive");
    	showBigPhoto($(this).parent().index(),$(this).attr("src"));
    })
}
function showBigPhoto(index,path) {
	showMask();
	var pStr="<div id='photoFrame'><div class='photo_close'></div><div class='bigPhoto'>";
    pStr+="<div class='bigPhoto_prev'></div><div class='bigPhoto_next'></div><div class='bigPhoto_tip'></div></div>";
    $("body").append(pStr);
    $(".bigPhoto").append('<img src="'+path+'" '+getFixSize(path)+'>');
    setFrameWH("photoFrame");
    $("#photoFrame .photo_close").click(function(){
    	$("#photoFrame").remove();
		$("#mask").remove();
    });
   	$("#photoFrame .bigPhoto_prev").click(function(){
   		if (index==0) {
   			$(".bigPhoto_tip").stop(true,false).html("已经是第一张").show(300).delay(3000).hide(300);// 这个是渐渐消失 
   		}else{
   			preImgSrc = $(".photo_list_bd li").eq(--index).children("img").attr("src");
   			$(".bigPhoto img").attr("src",preImgSrc);
   			$(".bigPhoto img").attr("width",getFixSize(preImgSrc).split(" ")[0].split("=")[1].split("'")[1]);
   			$(".bigPhoto img").attr("height",getFixSize(preImgSrc).split(" ")[1].split("=")[1].split("'")[1]);
   		}
    });
    $("#photoFrame .bigPhoto_next").click(function(){
    	if (index==$(".photo_list_bd li").length-1) {
   			$(".bigPhoto_tip").stop(true,false).html("已经是最后一张").show(300).delay(1000).hide(300);// 这个是渐渐消失 
   		}else{
   			nextImgSrc = $(".photo_list_bd li").eq(++index).children("img").attr("src");
   			$(".bigPhoto img").attr("src",nextImgSrc);
   			$(".bigPhoto img").attr("width",getFixSize(nextImgSrc).split(" ")[0].split("=")[1].split("'")[1]);
   			$(".bigPhoto img").attr("height",getFixSize(nextImgSrc).split(" ")[1].split("=")[1].split("'")[1]);
   		
   		}
    });
}
function showApplyTable(){
	showMask();
	if ($(".applyFrame").length>0) {
		$(".applyFrame").remove();
	}
	
	if (userData==null) {
		showLoginFrame();
		return;
	}
	var formStr="";
	formStr+="<div id='applyFrame'>";
    formStr+="<form id='applyForm' method='POST'><table>";
    formStr+="<input type='hidden' name='ClubId' value='"+clubId+"'>";
    formStr+="<input type='hidden' name='detailId' value='"+userData.id+"'>";
    formStr+="<tr><td>社团</td><td><input  type='hidden' name='clubName' value='"+clubName+"'/>"+clubName+"</td>";
    formStr+="<td>学校</td><td><input type='hidden' name='schoolName' value='"+schoolName+"'/>"+schoolName+"</td>";
    formStr+="<td rowspan='5'><img src='/ClubSystem"+userData.headPath+"' style='width: 100px;border:1px solid #ccc' /></td></tr>";
    formStr+="<tr><td>申请人</td><td>"+userData.name+"</td>";
    formStr+="<td>民族</td><td><input  type='text' name='nation' value='' /></td></tr>";
    formStr+="<tr><td>真实姓名</td><td><input type='text' name='name' value='' /></td><td>性别</td><td><select name='sex'><option value='1'>男</option><option value='0'>女</option></select></td></tr>";
    formStr+="<tr><td>专业班级</td><td><input type='text' name='majorClass'  value='' /></td><td>年龄</td><td><input type='text' name='age' value='' /></td></tr>";     
    formStr+="<tr><td>邮箱</td><td><input type='text' name='email' value='' /></td><td>电话</td><td><input type='text' name='phone' value='' /></td></tr>";          
    formStr+="<tr><td>爱好</td><td colspan='5'><textarea name='hobby'></textarea></td></tr>";                
    formStr+="<tr><td>个人简介</td><td colspan='5'><textarea name='introduce'></textarea></td></tr>";    
    formStr+="<tr><td>申请理由</td><td colspan='5'><textarea name='reason'></textarea></td></tr>";        
    formStr+="<tr><td colspan='6'><div class='apply_post'>提交</div><div class='apply_cancel'>取消</div></td></tr></table></form></div>";
    $("body").append(formStr);
    setFrameWH("applyFrame");
    initApplyFrameBtn();
    
    
    
  //表单验证
    $().ready(function() {

    	
    	$( '#applyForm' ).validate({
    		onsubmit: false,
    		onkeyup: false, 
    		
    		onfocusout: function(element){
    	        $(element).valid();
    	    },
    	    errorPlacement: function(error, element) {  
    	    		 element.val('');
        	         element.attr("placeholder",error.html());
    	    },
    		rules:{
    			nation:{
    				required:true,
    				chinese:true
    			},
    			name:{
    				required:true,
    				chinese:true,
    				minlength:2,
    				maxlength: 8
    			},
    			majorClass:{
    				required:true,
    			},
    			email:{
    				required:true,
    				email:true
    			},
    			phone:{
    				required:true,
    				isPhone:true
    			},
    			age:{
    				required:true,
    				age:true,
    				
    			},
    			reason:{
    				required:true
    			}
    		},
    		messages:{
    			nation:{
    				required:"不能为空!",
    				chinese:"只能输入汉字!"
    			},
    			name:{
    				required:"不能为空!",
    				chinese:"请输入汉字",
    				minlength:"长度不能小于2",
    				maxlength:"长度不能大于8"
    			},
    			majorClass:{
    				required:"不能为空!"
    			},
    			email:{
    				required:"不能为空!",
    				email:"example@163.com"
    			},
    			reason:{
    				required:"不能为空!",
    			},

    		}
    	});
    });
}


function initApplyFrameBtn(){
	$(".apply_post").click(function(){
		var url="/ClubSystem/apply/createApply";
		var sendData = $("#applyForm").serialize();
		$.post(url,sendData,function(data){
			if (data.serviceResult) {
				$(".apply-btn").css("background-color","#ccc").html(data.resultInfo).unbind();
			}else{
				alert(data.resultInfo);
			}
		});
		$("#applyFrame").remove();
		$("#mask").remove();
	});

	$(".apply_cancel").click(function(){
		$("#applyFrame").remove();
		$("#mask").remove();
	})
}

//判断是否已加入社团或提交了申请
function isApplyOrJoin(clubId){
	if (userData==null) {
		return;
	}
	var url="/ClubSystem/apply/getStatue";
	var mparm = "detailId="+userData.id+"&clubId="+clubId;
	$.get(url, mparm, function (data){
		if (data.serviceResult) {
			var statue = data.resultParm.statue;
			switch(statue){
				case 0:$(".apply-btn").html("进入社团").unbind().click(function(){window.location.href="/ClubSystem/views/club/showclubinformation.html?clubId="+clubId;});break;
				case 2:$(".apply-btn").css("background-color","#ccc").html("申请书已提交，等待审核").unbind();break;
				default:break;
			}
		}else{
			console.log(data.resultInfo);
		}
	})
}
//计算图片大小
function getFixSize(path){
	var theFixWidth;
	var theFixHeight;
	var parentWidth = $(".bigPhoto").width();
	var parentHeight = $(".bigPhoto").height();
	var thisImg = new Image();
	thisImg.src = path;
	var thisWidth = thisImg.width;
	var thisHeight = thisImg.height;
	if(thisWidth<=thisHeight){
		if(thisHeight<parentHeight){
			theFixHeight = thisHeight;
			theFixWidth = thisWidth;
		}else{
			theFixHeight = parentHeight-40;
			theFixWidth = theFixHeight*thisWidth/thisHeight;
		}
		return "width='"+theFixWidth+"px' height='"+theFixHeight+"px'";
	}else{
		if(thisWidth<parentWidth){
			theFixWidth = thisWidth;
			theFixHeight = thisHeight;
		}else{
			theFixWidth = parentWidth-40;
			theFixHeight = theFixWidth*thisHeight/thisWidth;
		}
		return "width='"+theFixWidth+"px' height='"+theFixHeight+"px'";
	}
}

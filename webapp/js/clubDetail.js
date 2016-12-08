$(document).ready(function () {
	intiClubDtail();
	intiClubDetailBtn();
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
	parm = window.location.search.split("?")[1];
	var url = "/ClubSystem/club/getById";
	$.get(url,parm,function(data){
		console.log(data);
		if (data.serviceResult) {
			var str="";
			var clubData = data.resultParm.club;
			$(".club-logo img").attr("src","/ClubSystem/"+clubData.badge);
			$(".club-info h3").html(clubData.name);
			$(".club-info .club-style").html(clubData.typeName);
			$(".club-info .club-school").html(clubData.schoolName);
			$(".club-info .club-count").html(clubData.number);
			$(".club-info .club-leader").html(clubData.leader);
			$(".club-info .club-phone").html(clubData.phone);
			$(".club-info .club-email").html(clubData.email);
			$(".clubDetai-content .club-introduction p").html(clubData.introduce);
		}
	});
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
		showClubActivity();
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

function showClubActivity(clubId) {
	var str = "<div class='club-activity'>活动</div>";
	$(".clubDetai-content div").not(".club-introduction").remove();
	$(".clubDetai-content").append(str);

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

function getClubDetailData(){

}
function getClubAlbumList(clubId){
	var url = "";
	// $.get(url, data, success);
	showClubAlbumList();
}
function showClubAlbumList(albumList){
	var albumList = [{"albumId":"1","clubId":"2","albumName":"美好时光","albumPath":"../../images/001.jpg","photoCount":"19"},
					{"albumId":"2","clubId":"2","albumName":"校园","albumPath":"../../images/002.png","photoCount":"19"},
					{"albumId":"3","clubId":"2","albumName":"社会","albumPath":"../../images/003.jpg","photoCount":"19"},
					{"albumId":"4","clubId":"2","albumName":"美好时光","albumPath":"../../images/004.jpg","photoCount":"19"},
					{"albumId":"5","clubId":"2","albumName":"美好时光","albumPath":"../../images/001.jpg","photoCount":"19"}];
	var str="<ul>";
	for (var i = 0; i < albumList.length; i++) {
		str+="<li><div class='club-album-box' data-albumid='"+albumList[i].albumId+"' data-clubid='"+albumList[i].clubId+"' data-albumname='"+albumList[i].albumName+"' data-albumpath='"+albumList[i].albumPath+"' data-photocount='"+albumList[i].photoCount+"'>";
		str+="<img src='"+albumList[i].albumPath+"'>";
		str+="<span class='photo-count'>"+albumList[i].photoCount+"</span><span>"+albumList[i].albumName+"</span></div></li>";
	}
	str+="<ul>";
	$(".clubDetai-content .club-album").append(str);
	$(".club-album-box img").click(function(){
		console.log();
		var dataSet = document.querySelectorAll(".club-album-box")[$(this).parents("li").index()].dataset;
		console.log(dataSet);
		var url = "/ClubSystem/getPhotoList";
		var parm = "ClubId="+dataSet.clubid+"&UserId="+dataSet.userid+"&AlbumId="+dataSet.albumid;
		// $.get(url, parm, function (data) {
		// 	console.log(data);
		// 	if (data.serviceResult) {
		// 		showPhotoList(data.resultParm.photoList,dataSet);
		// 	}
		// })
		showPhotoList();
	});
}
function showPhotoList(photoList,dataSet){
	var pStr="<div class='photo_list'><div class='photo_list_hd'><div class='photo_list_hd_albumImg'>";
    pStr+="<img src='E:\\GitHub\\CAS\\webapp\\images\\001.jpg'></div>";
    pStr+="<div class='photo_list_hd_albumName'>123</div></div>";
    pStr+="<div class='photo_list_bd'><ul>";
    for (var i =0; i <3; i++) {
    	pStr+= "<li><img src='E:\\GitHub\\CAS\\webapp\\images\\00"+(i+1)+".jpg'></li>";
    }
    pStr+="</ul></div></div>";
    $(".club-album").html(pStr);
    $(".photo_list_bd ul li img").click(function(){
    	console.log($(this).parent().index());
    	console.log($(this).attr("src"));
    	$(".photo_list_bd ul li.photo_acive").removeClass("photo_acive");
    	$(this).parent().addClass("photo_acive");
    	showBigPhoto($(this).parent().index(),$(this).attr("src"));
    })
}
function showBigPhoto(index,path) {
	showMask();
	var pStr="<div id='photoFrame'><div class='photo_close'></div><div class='bigPhoto'>";
    pStr+="<img src='"+path+"'>";
    pStr+="<div class='bigPhoto_prev'></div><div class='bigPhoto_next'></div><div class='bigPhoto_tip'></div></div>";
    $("body").append(pStr);
    setFrameWH("photoFrame");
    $("#photoFrame .photo_close").click(function(){
    	$("#photoFrame").remove();
		$("#mask").remove();
    });
   	$("#photoFrame .bigPhoto_prev").click(function(){
   		if (index==0) {
   			$(".bigPhoto_tip").stop(true,false).html("已经是第一张").show(300).delay(3000).hide(300);// 这个是渐渐消失 
   		}else{
   			$(".bigPhoto img").attr("src",$(".photo_list_bd li").eq(--index).children("img").attr("src"));
   		}
    });
    $("#photoFrame .bigPhoto_next").click(function(){
    	if (index==$(".photo_list_bd li").length-1) {
   			$(".bigPhoto_tip").stop(true,false).html("已经是最后一张").show(300).delay(1000).hide(300);// 这个是渐渐消失 
   		}else{
   			$(".bigPhoto img").attr("src",$(".photo_list_bd li").eq(++index).children("img").attr("src"));
   		}
    });
}
function showApplyTable(){
	showMask();
	if ($(".applyFrame").length>0) {
		$(".applyFrame").remove();
	}
	var userData = getCookieUserData();
	console.log(userData==null);
	if (userData==null) {
		showLoginFrame();
		return;
	}
	var formStr="";
	formStr+="<div id='applyFrame'>";
    formStr+="<form id='applyForm' method='POST'><table>";
    formStr+="<input type='hidden' name='ClubId' value='"+parm.split("=")[1]+"'>";
    formStr+="<input type='hidden' name='UserId' value='"+userData.id+"'>";
    formStr+="<tr><td>申请人</td><td>"+userData.name+"</td>";
    formStr+="<td>民族</td><td><input type='text' name='nation' value='' /></td>";
    formStr+="<td rowspan='4'><img src='/ClubSystem"+userData.headPath+"' style='width: 100px;border:1px solid #ccc' /></td></tr>";
    formStr+="<tr><td>真实姓名</td><td><input type='text' name='Name' value='' /></td><td>性别</td><td><input type='text' name='Sex'  value='' /></td></tr>";
    formStr+="<tr><td>专业班级</td><td><input type='text' name='MajorClass'  value='' /></td><td>年龄</td><td><input type='text' name='Age' value='' /></td></tr>";     
    formStr+="<tr><td>邮箱</td><td><input type='text' name='Email' value='' /></td><td>电话</td><td><input type='text' name='Phone' value='' /></td></tr>";          
    formStr+="<tr><td>爱好</td><td colspan='5'><textarea name='Hobby'></textarea></td></tr>";                
    formStr+="<tr><td>个人简介</td><td colspan='5'><textarea name='Introuduce'></textarea></td></tr>";    
    formStr+="<tr><td>申请理由</td><td colspan='5'><textarea name='ApplyReason'></textarea></td></tr>";        
    formStr+="<tr><td colspan='6'><div class='apply_post'>提交</div><div class='apply_cancel'>取消</div></td></tr></table></form></div>";
    $("body").append(formStr);
    setFrameWH("applyFrame");
    initApplyFrameBtn();
}
function initApplyFrameBtn(){
	$(".apply_post").click(function(){
		var url="/ClubSystem/";
		var sendData = $("#applyForm").serialize();
		console.log(sendData);
		$.post(url,sendData,function(data){
			console.log(data);
			if (data.serviceResult) {

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
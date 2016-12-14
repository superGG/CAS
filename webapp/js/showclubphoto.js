$(document).ready(function() {
	getAlbum();
	getPhotoList(1);
});

function initPageBtn(clubId) {
	var mli = $(".top_navs li");
	for (var i = 0; i < mli.length; i++) {
		var a = mli[i].firstChild;
		a.href = a.href + "?clubId=" + clubId;
	}
}

function getAlbum() {
	var url = "/ClubSystem/album/findById";
	var parm = "id=" + getUrlParam("albumId");
	$.get(url, parm, function(data) {
		if (data.serviceResult) {
			var album = data.resultParm.album;
			$(".album_cover img").attr("src", "/ClubSystem" + album.path);
			$(".album_title b").html(album.name);
			$(".album_title span").eq(0).html("共" + album.photoNumber + "张");
			$(".album_title span").eq(1).html("创建时间" + album.createtime.split(" ")[0]);
			$(".album_title a").attr("href", "showclubalbums.html?clubId=" + album.clubId);
			initPageBtn(album.clubId);
		}
	})
}

function getPhotoList(index) {
	var url = "/ClubSystem/photo/getAlbumPhoto";
	var parm = "albumId=" + getUrlParam("albumId") + "&size=12&indexPageNum=" + index;
	$.get(url, parm, function(data) {
		if (data.serviceResult) {
			var photoList = data.resultParm.photoList;
			showImg(photoList);
			if (photoList.length==0) {
				$(".loadMorePhoto").css({"background-color":"#ccc","cursor":"default"}).attr("onclick","javascript:void(0);").html("没有照片");
				return;
			}
			if (photoList.length<12) {
				$(".loadMorePhoto").css({"background-color":"#ccc","cursor":"default"}).attr("onclick","javascript:void(0);").html("没有更多数据...");
				return;
			}else{
				$(".loadMorePhoto").attr("onclick","getPhotoList("+(++index)+")");
				return;
			}
		}
	})
}

function showPhotoMask() {
	$("body").css("overflow-y", "hidden");
	$("#myMask").css("height", $(document.body).height());
	$("#myMask").css("width", $(document.body).outerWidth(true));
	$("#myMask").show();
}

function hideMask() {
	$(".photo_mask_main img").remove();
	$("#myMask").hide();
	$("body").css("overflow-y", "visible");
}

function showImg(photoList) {
	for (var i = 0; i < photoList.length; i++) {
		$(".album_photo").append('<div class="photo_item" id="' + photoList[i].id + '"><div class="photo_box" id="' + photoList[i].id + '_photo"><img src="/ClubSystem' + photoList[i].path + '"width="150px" height="120px"></div><div class="photo_name"><span>' + photoList[i].content + '</span></div></div>');
	}
	intiWatch();
}

function intiWatch(){
	var photo;
	var selectIndex; //当前选中的照片下标
	var len; //数据长度

	len = $(".album_photo .photo_item").length;
	$(".photo_item").hover(function() {
		photo = $(this).attr("id");
		$("#" + photo + "_photo").append('<div class="photo_watch"><a href="#" id="watch_btn"><b>查看</b></a></div>');
		$("#watch_btn").click(function() {
			selectIndex = $(this).parent().parent().parent().index();
			showPhotoMask();
			var theImg = $(this).parent().parent().children("img");
			var pic_src = theImg.attr("src");
			$(".photo_mask_main").append('<img src=' + pic_src + ' '+getFixSize(theImg)+'>');
		});
	}, function() {
		$("#" + photo + "_photo .photo_watch").remove();
	});
	$("#pre_btn").click(function() {
		if (selectIndex == 0) {} else {
			$(".photo_mask_main img").remove();
			var preImg = $(".album_photo .photo_item").eq(selectIndex - 1).children(".photo_box").children("img");
			var pre_src = preImg.attr("src");
			$(".photo_mask_main").append('<img src=' + pre_src + ' '+getFixSize(preImg)+'>');
			selectIndex = selectIndex - 1;
		}
	});
	$("#next_btn").click(function() {
		if (selectIndex == len - 1) {} else {
			$(".photo_mask_main img").remove();
			var nextImg = $(".album_photo .photo_item").eq(selectIndex + 1).children(".photo_box").children("img");
			var next_src = nextImg.attr("src");
			$(".photo_mask_main").append('<img src=' + next_src + ' '+getFixSize(nextImg)+'>');
			selectIndex = selectIndex + 1;
		}
	});
}
function getFixSize(which){
	var theFixWidth;
	var theFixHeight;
	var parentWidth = $(".photo_mask_main").width();
	var parentHeight = $(".photo_mask_main").height();
	var thisImg = new Image();
	thisImg.src = $(which).attr("src");
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
var index = 0;
var albumId;
$(document).ready(function() {
	index = 1;
	setPositionHref();
	initAlbumInformation();

	morePhoto(index);
	ShowOrHide();
	detailShowOrHide();
});
//初始画相册信息
function initAlbumInformation() {
	var mAlbumId = getUrlParam("albumId");
	var url = "/ClubSystem/album/findById";
	var parm = "id=" + mAlbumId;
	$.get(url, parm, function(data) {
		if (data.serviceResult) {
			var album = data.resultParm.album;
			albumId = album.id;
			$("#cover").attr("src", "/ClubSystem" + album.path);
			$("#album_name").text(album.name);
			$("#number").text(album.photoNumber);
			$("#a_number").text(album.photoNumber);
			$("#createtime").text(album.createtime.split(" ")[0]);
			initPhoto(index);
		} else {
			alert(data.resultInfo);
		}
	});
}
//删除相册
function albumDelete() {
	var con;
	con = confirm("确认删除?"); //在页面上弹出对话框  
	if (con) {
		var url = "/ClubSystem/album/delete";
		var parm = "id=" + albumId;
		console.log(parm);
		$.get(url, parm, function(data) {
			if (data.serviceResult) {
				window.location.href = "/ClubSystem/views/club/albummanage.html";
			} else {
				alert(data.resultInfo);
			}
		});
	} else {
		return false;
	}
}
//初始化相册的相片
function initPhoto(index) {
	var url = "/ClubSystem/photo/getAlbumPhoto";
	var parm = "albumId=" + albumId + "&size=12&indexPageNum=" + index;
	$.get(url, parm, function(data) {
		if (data.serviceResult) {
			var photoList = data.resultParm.photoList;
			showPhoto(photoList);
			if ($(".album_photo .photo_item").length >= data.resultParm.total) {
				$("#more").remove();
				$(".lwd_main_iframe .noMore:first").remove();
				$(".lwd_main_iframe").append('<div class="noMore">没有更多图片...</div>');
			}
		} else {
			alert(data.resultInfo);
		}
	});
}

function showPhoto(photoList) {
	for (var i = 0; i < photoList.length; i++) {
		$(".album_photo").append('<div id="' + photoList[i].id + '" class="photo_item" onmouseenter="showBox(this)" onmouseleave="hideBox(this)"><div class="photo_box" id="' + photoList[i].id + '"><img src="/ClubSystem' + photoList[i].path + '" width="150px" height="120px"><div class="hidden_box" id="hidden_box" ><a href=javascript:void(0); onclick="photoDelete(this)" title="删除照片"><i class="icon-m icon-trash-m"></i></a>  <a href=javascript:void(0); onclick="setCover(' + photoList[i].id + ')" title="设为封面"><i class="icon-m icon-cover-m"></i></a></div>	</div><div class="photo_name"><span onclick="showInput(this)">' + photoList[i].content + '</span></div></div>');
	}
}

function morePhoto(index) {
	$("#more").click(function() {
		index = index + 1;
		initPhoto(index);
	});
}

function photoDelete(which) {
	var con;
	con = confirm("确定要删除？");
	if (con) {
		var url = "/ClubSystem/photo/delete";
		var parm = "id=" + $(which).parent().parent().parent().attr("id");
		$.post(url, parm, function(data) {
			if (data.serviceResult) {
				$(which).parent().parent().parent().remove();
				$("#number").text(parseInt($("#number").text()) - 1);
			} else {
				alert(data.resultInfo);
			}
		});
	} else {
		return false;
	}

}

function photoUpload() {
	var photoFormData = new FormData($("#uploadForm_photo")[0]);
	// photoFormData.append("clubId",);
	photoFormData.append("albumId", albumId);
	$.ajax({
		url: '/ClubSystem/photo/upload',
		type: 'POST',
		data: photoFormData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			// $(".album_photo").children().remove();
			// initPhoto(1);
			var photoList = data.resultParm.photoList;
			for (var i = 0; i < photoList.length; i++) {
				$(".album_photo").prepend('<div id="' + photoList[i].id + '" class="photo_item" onmouseenter="showBox(this)" onmouseleave="hideBox(this)"><div class="photo_box" id="' + photoList[i].id + '"><img src="/ClubSystem' + photoList[i].path + '" width="150px" height="120px"><div class="hidden_box" id="hidden_box" ><a href=javascript:void(0); onclick="photoDelete(this)" title="删除照片"><i class="icon-m icon-trash-m"></i></a>  <a href="javascript:void(0);" onclick="setCover(' + photoList[i].id + ')" title="设为封面"><i class="icon-m icon-cover-m"></i></a></div>	</div><div class="photo_name"><span onclick="showInput(this)">' + photoList[i].content + '</span></div></div>');
			}
			$("#number").text(parseInt($("#number").text()) + photoList.length);
		},
		error: function(data) {
			alert(data.resultInfo);
		}
	});
}
//更多show 或者hide；
function ShowOrHide() {
	$(".album_cover").mouseover(function() {
		$(".album_update_cover").show();
	});
	$(".album_cover").mouseout(function() {
		$(".album_update_cover").hide();
	});

}
//详情down或者up
function detailShowOrHide() {
	$("#down").click(function() {
		$("#down").hide();
		$("#up").show();
		$("#sb_more").show();
	}); //下拉查看详细描述
	$("#up").click(function() {
		$("#up").hide();
		$("#down").show();
		$("#sb_more").hide();
	}); //上去
	$(".sb_update").click(function() {
		$("#sb_box").show();
		$("#sb_more").hide();
		$('#loadingDiv').css('display', 'block');
	}); //点击编辑相册详情
	$("#sb_back").click(function() {
		$("#sb_box").hide();
		$('#loadingDiv').css('display', 'none');
	}); //点击取消，相册详情隐藏
}

function showBox(which) {
	$(which).children().children("#hidden_box").show();
}

function hideBox(which) {
	$(which).children().children("#hidden_box").hide();
}

function updateCover() {
	var coverFormData = new FormData($("#uploadForm_cover")[0]);
	coverFormData.append("id", albumId);
	$.ajax({
		url: '/ClubSystem/album/update',
		type: 'POST',
		data: coverFormData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			initAlbumInformation();
			alert(data.resultInfo);
		},
		error: function(data) {
			alert(data.resultInfo);
		}
	});
}

function showInput(which) {
	var oldName = $(which).text();
	$(which).after('<input type="text" style="width:147.5px;height:25px;" id="new_name" maxlength="8" value="' + oldName + '">');
	$(which).next().focus().val(oldName);
	$(which).remove();
	$("#new_name").keydown(function(e) {
		if (e.keyCode == 13) {
			var newName = $(this).val();
			$(this).children("input").remove();
			$(this).append('<span>' + newName + '</span>');
			var url = "/ClubSystem/photo/update";
			var parm = "id=" + $(this).parents(".photo_item").attr("id") + "&content=" + newName;
			$.post(url, parm, function(data) {
				if (data.serviceResult) {
					$("#new_name").before('<span onclick="showInput(this)">' + data.resultParm.photo.content + '</span>');
					$("#new_name").remove();
				} else {
					alert(data.resultInfo);
				}
			});
		}
	});
	$("#new_name").blur(function() {
			$(this).before('<span onclick="showInput(this)">' + oldName + '</span>');
			$(this).remove();
		})
		// $(which).children().blur(function(){
		// 	$(which).children().remove();
		// 	$(which).append('<span>'+oldName+'</span>');
		// });
}

//更新相册名字
function updataAlbum() {
	// var name = $("#sb_box input[name='updateAlbum']").val();
	var url = "/ClubSystem/album/update";
	var parm = $("#sb_box form").serialize() + "&id=" + albumId;
	$.post(url, parm, function(data) {
		if (data.serviceResult) {
			var album = data.resultParm.album;
			$("#album_name").text(album.name);
			$("#sb_back").click();
		}
	});

}
//设置为封面
function setCover(id) {
	var mPath = $(".photo_box#" + id + " img").attr("src");
	mPath = mPath.split("ClubSystem")[1];
	var url = "/ClubSystem/album/update";
	var parm = "id=" + albumId + "&path=" + mPath;
	$.post(url, parm, function(data) {
		if (data.serviceResult) {
			var album = data.resultParm.album;
			$(".album_cover #cover").attr("src", "/ClubSystem" + album.path);
		}
	});
}
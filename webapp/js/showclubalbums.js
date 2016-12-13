$(document).ready(function() {
	initPageBtn(getUrlParam("clubId"));
	getAlbumList();
});
function initPageBtn(clubId){
	var mli = $(".top_navs li");
	for (var i = 0; i <mli.length; i++) {
		var a = mli[i].firstChild;
		a.href=a.href+"?clubId="+clubId;
	}
}
function getAlbumList(){
	var url="/ClubSystem/album/getByClub";
	var parm = "id="+getUrlParam("clubId");
	$.get(url, parm, function (data) {
		if (data.serviceResult) {
			var albumList = data.resultParm.albumList;
			initAlbumList(albumList);
		}
	})

}
function initAlbumList(albumList) {
	showAlbumList(albumList);
	var timer;
	var album;
	$(".album_action").css("visibility", "visible")
	$(".album_action").hide();
	$(".album").hover(function() {
		clearTimeout(timer);
		album = $(this).attr("id");
		timer = setTimeout(function() {
			$("#" + album + "_action").slideDown("fast");
			$("#" + album + "_name").animate({
				height: '60px'
			}, "fast");
		}, 100);
	}, function() {
		clearTimeout(timer);
		$("#" + album + "_action").slideUp("fast");
		$("#" + album + "_name").animate({
			height: '30px'
		}, "fast");
	});
}

function showAlbumList(albumList) {
	for (var i = 0; i < albumList.length; i++) {
		$(".lwd_main_iframe").append('<div class="album" id="' + albumList[i].id + '"><div class="album_pic"><img src="/ClubSystem' + albumList[i].path + '" width="150px" height="100px"></div><div class="album_name" id="' + albumList[i].id + '_name">	<span style="font-size:12px;">' + albumList[i].name + '</span></div><div id="' + albumList[i].id + '_action" class="album_action"><a href="showclubphoto.html?albumId='+albumList[i].id+'"><button >进入相册</button></a></div></div>');
	}
}
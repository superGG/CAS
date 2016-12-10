//$(document).ready(function(){
//		showAlbumList(albumList);
//		var timer;
//		var album;
//		$(".album_action").css("visibility","visible")
//		$(".album_action").hide();
//		$(".album").hover(function(){
//			clearTimeout(timer);
//			album = $(this).attr("id");
//			timer=setTimeout(function(){
//				$("#"+album+"_action").slideDown("fast");
//				$("#"+album+"_name").animate({height:'60px'},"fast");
//			},100);
//		},function(){
//			clearTimeout(timer);
//			$("#"+album+"_action").slideUp("fast");
//			$("#"+album+"_name").animate({height:'30px'},"fast");
//		});
//});
$(document).ready(function(){
	initAlbumList();
	albumAdd();
});
function showAlbumList(albumList){
	for(var i=0; i<albumList.album.length; i++){
		$(".lwd_main_iframe").append('<div class="album" id="'+albumList.album[i].id+'"><div class="album_pic"><img src="'+albumList.album[i].cover+'" width="150px" height="100px"></div><div class="album_name" id="'+albumList.album[i].id+'_name">	<span style="font-size:12px;">'+albumList.album[i].title+'</span><span style="font-size:12px;position:absolute;right:5px;">'+albumList.album[i].createtime+'</span></div><div id="'+albumList.album[i].id+'_action" class="album_action"><a href="albuminformation.html"><button>进入相册</button></a> <button>删除相册</button></div></div>');
	}
}
function albumAdd(){
	if($("#album_name").val()!=null){
		var url = "/ClubSystem/album/save";
		var parm = "";
		$.post(url,parm,function(data){
			
		});
	}else{
		alert("请填入相册名称!");
	}
}


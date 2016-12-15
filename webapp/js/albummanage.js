var timer;
$(document).ready(function(){
	setPositionHref();
	getMyCreateClub();
});
function initAlbumList(){
	var url = "/ClubSystem/album/getByClub";
	var parm = "id="+clubId;
	$.get(url,parm,function(data){
		if(data.serviceResult){
			var albumList = data.resultParm.albumList;
			showAlbumList(albumList);
		}
	});
}
function showAlbumList(albumList){
	for(var i=0; i<albumList.length; i++){
		$(".lwd_main_iframe").append('<div class="album" id="'+albumList[i].id+'" onmouseenter="mouseOver(this)" onmouseleave="mouseOut(this)"><div class="album_pic"><img src="../..'+albumList[i].path+'" width="150px" height="100px"></div><div class="album_name" id="'+albumList[i].id+'_name">	<span style="font-size:12px;">'+albumList[i].name+'</span><span style="font-size:12px;position:absolute;right:5px;">'+albumList[i].createtime.split(" ")[0]+'</span></div><div id="'+albumList[i].id+'_action" class="album_action"><a href="albuminformation.html?albumId='+albumList[i].id+'"><button>进入相册</button></a> <button onclick="albumDelete(this)">删除相册</button></div></div>');
	}
}
function albumAdd(){
	var albumName=$("#album_name").val();
	if(albumName!=""){
		var url = "/ClubSystem/album/save";
		var parm = "name="+html_encode(albumName)+"&clubId="+clubId;
		$.post(url,parm,function(data){
			if(data.serviceResult){
				alert(data.resultInfo);
				var albumData = data.resultParm.album;
				$("#album_name").val("");
				$(".lwd_main_iframe").append('<div class="album" id="'+albumData.id+'" onmouseenter="mouseOver(this)" onmouseleave="mouseOut(this)"><div class="album_pic"><img src="../..'+albumData.path+'" width="150px" height="100px"></div><div class="album_name" id="'+albumData.id+'_name">	<span style="font-size:12px;">'+html_decode(albumData.name)+'</span><span style="font-size:12px;position:absolute;right:5px;">'+albumData.createtime.split(" ")[0]+'</span></div><div id="'+albumData.id+'_action" class="album_action"><a href="albuminformation.html?albumId='+albumData.id+'"><button>进入相册</button></a> <button onclick="albumDelete(this)">删除相册</button></div></div>');
			}else{
				alert(data.resultInfo);
			}
		});
	}else{
		alert("请填入相册名称!");
	}
}
function mouseOver(which){
	clearTimeout(timer);
	timer=setTimeout(function(){
	    //这里触发hover事件
		$(which).children(".album_action").slideDown("fast");
	 },150);
}
function mouseOut(which){
	clearTimeout(timer);
	$(which).children(".album_action").slideUp("fast");
}
function albumDelete(which){
	var con;
	con=confirm("确认删除?"); //在页面上弹出对话框  
	if(con){
		var url = "/ClubSystem/album/delete";
		var parm = "id="+$(which).parent().parent().attr("id");
		console.log(parm);
		$.get(url,parm,function(data){
			if(data.serviceResult){
				$(which).parent().parent().remove();
			}else{
				alert(data.resultInfo);
			}
		});
	}else{
		return false;
	}
}

function getMyCreateClub() {
	var userData = getCookieUserData();
	clubId='';
	var url="/ClubSystem/club/getMyClub";
	var mparm = "detailId="+userData.id;

	$.get(url, mparm, function (data) {
		console.log(data);
		if (data.serviceResult) {
			clubId = data.resultParm.club.id;
			initAlbumList();
		}else{
			alert("请先创建社团");
			window.location.href="tocreateclub.html";
		}
	})
}
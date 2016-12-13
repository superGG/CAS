var index = 0;
var albumId;
$(document).ready(function(){
	index = 1;
	initAlbumInformation();
	initPhoto(index);
	morePhoto(index);
	ShowOrHide();
	detailShowOrHide();
});
//初始画相册信息
function initAlbumInformation(){
	var url = "";
	var parm = "";
	$.get(url,parm,function(data){
		if(data.serviceResult){
			var album = data.resultParm.alubm;
			albumId = album.id;
			$("#cover").text("../.."+album.path);
			$("#album_name").text(album.name);
			$("#number").text(album.number);
			$("#a_number").text(album.number);
			$("#createtime").text(album.createtime.split(" ")[0]);
		}else{
//			alert(data.resultInfo);
		}
	});
}
//删除相册
function albumDelete(){
	var con;
	con=confirm("确认删除?"); //在页面上弹出对话框  
	if(con){
		var url = "/ClubSystem/album/delete";
		var parm = "id="+albumId;
		console.log(parm);
		$.get(url,parm,function(data){
			if(data.serviceResult){
				window.location.href="/ClubSystem/views/club/membermanage.html";
			}else{
				alert(data.resultInfo);
			}
		});
	}else{
		return false;
	}
}
//初始化相册的相片
function initPhoto(index){
	var url = "/ClubSystem/photo/getAlbumPhoto";
	var parm = "albumId=1&size=12&indexPageNum="+index;
	$.get(url,parm,function(data){
		if(data.serviceResult){
			var photoList = data.resultParm.photoList;
			showPhoto(photoList);
			if( $(".album_photo .photo_item").length >= data.resultParm.total){
				$("#more").remove();
				$(".lwd_main_iframe .noMore:first").remove();
				$(".lwd_main_iframe").append('<div class="noMore">没有更多数据...</div>');
			}
		}else{
			alert(data.resultInfo);
		}
	});
}
function showPhoto(photoList){
	for(var i = 0;i<photoList.length;i++){
		$(".album_photo").append('<div id="'+photoList[i].id+'" class="photo_item" onmouseenter="showBox(this)" onmouseleave="hideBox(this)"><div class="photo_box" id="'+photoList[i].id+'"><img src="../..'+photoList[i].path.split("\\")[7]+'" width="150px" height="120px"><div class="hidden_box" id="hidden_box" ><a href="#" onclick="photoDelete(this)" title="删除照片"><i class="icon-m icon-trash-m"></i></a>  <a href="#" title="设为封面"><i class="icon-m icon-cover-m"></i></a></div>	</div><div class="photo_name" onclick="showInput(this)"><span>'+photoList[i].content+'</span></div></div>');
	}
}
function morePhoto(index){
	$("#more").click(function(){
		index = index+1;
		initPhoto(index);
	});
}
function photoDelete(which){
	var con;
	con = confirm("确定要删除？");
	if(con){
		var url = "/ClubSystem/photo/delete";
		var parm = "id="+$(which).parent().parent().parent().attr("id");
		$.post(url,parm,function(data){
			if(data.serviceResult){
				$(which).parent().parent().parent().remove();
			}else{
				alert(data.resultInfo);
			}
		});
	}else{
		return false;
	}
	
}
function photoUpload(){
	var photoFormData = new FormData($( "#uploadForm_photo" )[0]);
	photoFormData.append("clubId",2);
	photoFormData.append("albumId",1);
	$.ajax({  
	   url: '/ClubSystem/photo/upload',  
	   type: 'POST',  
	   data: photoFormData,  
	   async: false,  
	   cache: false,  
	   contentType: false,  
	   processData: false,  
	   success: function (data) { 
		   $(".album_photo").children().remove();
		   initPhoto(1);
	   },  
	   error: function (data) {  
	      alert(data.resultInfo);  
	   }  
	}); 
}
//更多show 或者hide；
function ShowOrHide(){
	$(".album_cover").mouseover(function(){
		$(".album_update_cover").show();
	});
	$(".album_cover").mouseout(function(){
		$(".album_update_cover").hide();
	});
	
}
//详情down或者up
function detailShowOrHide(){
	$("#down").click(function(){
		$("#down").hide();
		$("#up").show();
		$("#sb_more").show();
	});//下拉查看详细描述
	$("#up").click(function(){
		$("#up").hide();
		$("#down").show();
		$("#sb_more").hide();
	});//上去
	$(".sb_update").click(function(){
		$("#sb_box").show();
		$("#sb_more").hide();
		$('#loadingDiv').css('display','block'); 
	});//点击编辑相册详情
	$("#sb_back").click(function(){
		$("#sb_box").hide();
		$('#loadingDiv').css('display','none'); 
	});//点击取消，相册详情隐藏
}
function showBox(which){
	$(which).children().children("#hidden_box").show();
}
function hideBox(which){
	$(which).children().children("#hidden_box").hide();
}
function updateCover(){
	var coverFormData = new FormData($( "#uploadForm_cover" )[0]); 
	coverFormData.append("id",1);
	$.ajax({  
         url: '/ClubSystem/album/update' ,  
         type: 'POST',  
         data: coverFormData,  
         async: false,  
         cache: false,  
         contentType: false,  
         processData: false,  
         success: function (data) { 
        	 initAlbumInformation();
        	 alert(data.resultInfo);
	     },  
         error: function (data) {  
             alert(data.resultInfo);  
         }  
   }); 
}
function showInput(which){
	var oldName = $(which).children().text();
	$(which).children().remove();
	$(which).append('<input type="text" style="width:147.5px;height:25px;" id="new_name" maxlength="8" value="'+oldName+'">');
	$(which).children().val("").focus().val(oldName);
	$(which).children().keydown(function(e){
		if(e.keyCode==13){
			var newName = $(which).children().val();
			$(which).children("input").remove();
			$(which).append('<span>'+newName+'</span>');
			
			var url = "/ClubSystem/photo/update";
			var parm = "id="+$(which).parent().attr("id")+"&content="+newName;
			$.post(url,parm,function(data){
				if(data.serviceResult){
					
				}else{
					alert(data.resultInfo);
				}
			});
		}
	});
	$(which).children().blur(function(){
		$(which).children().remove();
		$(which).append('<span>'+oldName+'</span>');
	});
}

	

$(document).ready(function () {
	getMessageList();
})

function getMessageList(index) {
	index=1;
	var url = "/ClubSystem/message/getOneMessgae";
	var mparm = "indexPageNum=1&size=4";
	$.get(url,mparm,function (data) {
		if (data.serviceResult) {
			var listData = data.resultParm.message;
			var mStr = "";
			for (var i = 0; i < listData.length; i++) {
				mStr+="<div class='comment_item'><div class='img_radius'><img src='../.."+listData[i].headPath+"' width='100%' height='100%'></div>";
				mStr+="<div class='item_main'><div class='item_row_one'><b>"+listData[i].userName+"</b><span>"+listData[i].createtime+"</span></div>";
				mStr+="<div class='item_row_tow'><p>"+listData[i].content+"</p></div>";
				mStr+="<div class='item_row_three'><a href='#'><i class='icon-m icon-response-m'></i><label>评论"+listData[i].sonSize+"条</label></a>";
				mStr+="<a href='#'><i class='icon-m icon-praise-m'></i><label>赞"+listData[i].good+"</label></a></div>";
				mStr+="<div class='item_row_four'><div class='editor'>";
				mStr+="<div class='editorContent' contenteditable='true'></div>";
				mStr+="<div class='toolBar'><span class='expression' title='表情' onclick='showExpression(this)'></span></div>";
				mStr+="<button class='comment_post' onclick='comment_post(this)'>提交评论</button>";
				mStr+="<button class='comment_cancel' onclick='comment_cancel(this)'>取消</button></div>";
				for (var  j= 0; j < listData[i].sonList.length; j++) {
					mStr+="<div class='response_item'>";
					mStr+="<div class='response_item_img'><img src='../.."+listData[i].sonList[j].headPath+"' width='100%' height='100%'></div>";
					mStr+="<div class='response_item_main'>";
					mStr+="<p><b>"+listData[i].sonList[j].userName+"</b>"+listData[i].sonList[j].content+"</p>";
					mStr+="<p class='create_date'>"+listData[i].sonList[j].createtime+"<a href='#'><i class='icon-m icon-praise-m-green'></i>";
					mStr+="<label>"+listData[i].sonList[j].good+"</label></a></p></div></div>";
				}
				mStr+="</div></div></div>";
			}
			$(".main_left_main").append(mStr);
			intiEditor();//初始化评论输入框
		}
	});
}
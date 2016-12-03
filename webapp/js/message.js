$(document).ready(function () {
	// body...
})

function getMessageList() {
	var url = "/ClubSystem/message/getMessageList";
	$.get(url,function (data) {
		console.log(data.serviceResult+";"+data);
		if (data.serviceResult) {
			var list = data.resultParm.messageList;
			var mStr = "";
			for (var i = 0; i < list.length; i++) {
				mStr+="<div class='comment_item'><div class='img_radius'><img src='../../images/"+list[i].headPath+"' width='100%' height='100%'></div>";
				mStr+="<div class='item_main'><div class='item_row_one'><b>"+list[i].name+"</b><span>"+list[i].time+"</span></div>";
				mStr+="<div class='item_row_tow'><p>"+list[i].content+"</p></div>";
				mStr+="<div class='item_row_three'><a href='#'><i class='icon-m icon-response-m'></i><label>评论"+list[i].count+"条</label></a>";
				mStr+="<a href='#'><i class='icon-m icon-response-m'></i><label>评论"+list[i].like+"条</label></a></div>";
				mStr+="<div class='item_row_four'><div class='editor'>";
				mStr+="<div class='editorContent' contenteditable='true'></div>";
				mStr+="<div class='toolBar'><span class='expression' title='表情' onclick='showExpression(this)'></span></div>";
				mStr+="<button class='comment_post' onclick='comment_post(this)'>提交评论</button>";
				mStr+="<button class='comment_cancel' onclick='comment_cancel(this)'>取消</button></div>";
				for (var  j= 0; j < list[i].respone.length; j++) {
					mStr+="<div class='response_item'>";
					mStr+="<div class='response_item_img'><img src='"+list[i].respone[j].headPath+"' width='100%' height='100%'></div>";
					mStr+="<div class='response_item_main'>";
					mStr+="<p><b>"+list[i].respone[j].name+"</b>"+list[i].respone[j].content+"</p>";
					mStr+="<p class='create_date'>"list[i].respone[j].time"<a href='#'><i class='icon-m icon-praise-m-green'></i>";
					mStr+="<label>"+list[i].respone[j].like+"</label></a></p></div></div>";
				}
				mStr+="</div></div></div>";
			}
			$(".main_left_main").append(mStr);
		}
	});
}
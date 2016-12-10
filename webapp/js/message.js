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
				mStr+="<div class='item_row_tow'><p>"+html_decode(listData[i].content)+"</p></div>";
				mStr+="<div class='item_row_three'><a href='#'><i class='icon-m icon-response-m'></i><label>"+listData[i].sonSize+"</label></a>";
				mStr+="<a href='#'><i class='icon-m icon-praise-m'></i><label>赞"+listData[i].good+"</label></a></div>";
				mStr+="<div class='item_row_four'><div class='editor'>";
				mStr+="<div class='editorContent' contenteditable='true'></div>";
				mStr+="<div class='toolBar'><span class='expression' title='表情' onclick='showExpression(this)'></span></div>";
				mStr+="<button class='comment_post' onclick='comment_post(this,"+listData[i].id+")'>提交评论</button>";
				mStr+="<button class='comment_cancel' onclick='comment_cancel(this)'>取消</button></div>";
				for (var  j= 0; j < listData[i].sonList.length; j++) {
					mStr+="<div class='response_item'>";
					mStr+="<div class='response_item_img'><img src='../.."+listData[i].sonList[j].headPath+"' width='100%' height='100%'></div>";
					mStr+="<div class='response_item_main'>";
					mStr+="<p><b>"+listData[i].sonList[j].userName+"</b>"+html_decode(listData[i].sonList[j].content)+"</p>";
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

//此方法给editor的提交内容按钮用
function post_data(commentData,fatherId,tihsClick){
	var userData = getCookieUserData();
	if (userData==null) {
		showLoginFrame();
		return;
	}
	var url="/ClubSystem/message/save";
	//判断父留言还是子留言
	if (fatherId==null) {
		var mparm ="detailId="+userData.id+"&content="+commentData.trim();
	}else{
		var mparm = "fatherId="+fatherId+"&detailId="+userData.id+"&content="+commentData.trim();
	}
	$.post(url,mparm,function (data) {
		if (data.serviceResult) {
			var mdata = data.resultParm.message;
			var mStr="";
			if(fatherId==null){
				mStr+="<div class='comment_item'><div class='img_radius'><img src='../.."+mdata.headPath+"' width='100%' height='100%'></div>";
				mStr+="<div class='item_main'><div class='item_row_one'><b>"+mdata.userName+"</b><span>"+mdata.createtime+"</span></div>";
				mStr+="<div class='item_row_tow'><p>"+html_decode(mdata.content)+"</p></div>";
				mStr+="<div class='item_row_three'><a href='#'><i class='icon-m icon-response-m'></i><label>"+mdata.sonSize+"</label></a>";
				mStr+="<a href='#'><i class='icon-m icon-praise-m'></i><label>赞"+mdata.good+"</label></a></div>";
				mStr+="<div class='item_row_four'><div class='editor'>";
				mStr+="<div class='editorContent' contenteditable='true'></div>";
				mStr+="<div class='toolBar'><span class='expression' title='表情' onclick='showExpression(this)'></span></div>";
				mStr+="<button class='comment_post' onclick='comment_post(this,"+mdata.id+")'>提交评论</button>";
				mStr+="<button class='comment_cancel' onclick='comment_cancel(this)'>取消</button></div>";
				$(".main_left_main").prepend(mStr);
				intiEditor();
			}else{
				mStr="<div class='response_item'>";
				mStr+="<div class='response_item_img'><img src='../.."+mdata.headPath+"' width='100%' height='100%'></div>";
				mStr+="<div class='response_item_main'>";
				mStr+="<p><b>"+mdata.userName+"</b>"+html_decode(mdata.content)+"</p>";
				mStr+="<p class='create_date'>"+mdata.createtime+"<a href='#'><i class='icon-m icon-praise-m-green'></i>";
				mStr+="<label>"+mdata.good+"</label></a></p></div></div>";
				$(tihsClick).parent().after(mStr);
				var sonSize = $(tihsClick).parents(".item_row_four").siblings(".item_row_three").find(".icon-response-m").next().html();
				$(tihsClick).parents(".item_row_four").siblings(".item_row_three").find(".icon-response-m").next().html(parseInt(sonSize)+1);
			}
		}
	})
}

// function html_encode(str) {
//     var s = "";
//     if (str.length == 0)
//         return "";
//     s = str.replace(/&/g, "&gt;");
//     s = s.replace(/</g, "&lt;");
//     s = s.replace(/>/g, "&gt;");
//     s = s.replace(/ /g, "&nbsp;");
//     s = s.replace(/\'/g, "&#39;");
//     s = s.replace(/\"/g, "&quot;");
//     s = s.replace(/\n/g, "<br>");
//     return s;
// }
//替换转义
function html_decode(str) {
    var s = "";
    if (str.length == 0){
        return "";
    }
    s = str.replace(/&lt;/g, "<");
    s = s.replace(/&gt;/g, ">");
    s = s.replace(/&nbsp;/g, " ");
    s = s.replace(/&#39;/g, "\'");
    s = s.replace(/&quot;/g, "\"");
    s = s.replace(/<br>/g, "\n");
    s = s.replace(/&gt;/g, "&");
    return s;
}
$(document).ready(function() {
	getMessageList(1);
});

function getMessageList(index) {
	var url = "/ClubSystem/message/getOneMessgae";
	var mparm = "indexPageNum=" + index + "&size=4";
	$.get(url, mparm, function(data) {
		if (data.serviceResult) {
			var listData = data.resultParm.message;
			showMessageList(listData);
			intiEditor(); //初始化评论输入框
		}
	});
}

function showMessageList(listData) {
	var mStr = "";
	for (var i = 0; i < listData.length; i++) {
		mStr += "<div class='comment_item'><div class='img_radius'><img src='../.." + listData[i].headPath + "' width='100%' height='100%'></div>";
		mStr += "<div class='item_main'><div class='item_row_one'><b>" + listData[i].userName + "</b><span>" + listData[i].createtime + "</span></div>";
		mStr += "<div class='item_row_tow'><p>" + html_decode(listData[i].content) + "</p></div>";
		mStr += "<div class='item_row_three'><a href='javascipt:void(0);'><i class='icon-m icon-response-m' onclick='showEditor(this)'></i><label>" + listData[i].sonSize + "</label></a>";
		mStr += "<a href='javascipt:void(0);'><i class='icon-m icon-praise-m' onclick='giveLike(this," + listData[i].id + ")'></i><label>" + listData[i].good + "</label></a></div>";
		mStr += "<div class='item_row_four'><div class='editor' style='display:none;'>";
		mStr += "<div class='editorContent' contenteditable='true'></div>";
		mStr += "<div class='toolBar'><span class='expression' title='表情' onclick='showExpression(this)'></span></div>";
		mStr += "<button class='comment_post' onclick='comment_post(this," + listData[i].id + ")'>提交评论</button>";
		mStr += "<button class='comment_cancel' onclick='comment_cancel(this)'>取消</button></div>";
		for (var j = 0; j < listData[i].sonList.length; j++) {
			if (j < 3) {
				mStr += "<div class='response_item'>";
			} else {
				mStr += "<div class='response_item' style='display:none;'>";
			}
			mStr += "<div class='response_item_img'><img src='../.." + listData[i].sonList[j].headPath + "' width='100%' height='100%'></div>";
			mStr += "<div class='response_item_main'>";
			mStr += "<p><b>" + listData[i].sonList[j].userName + "</b>" + html_decode(listData[i].sonList[j].content) + "</p>";
			mStr += "<p class='create_date'>" + listData[i].sonList[j].createtime + "<a href='javascipt:void(0);'><i class='icon-m icon-praise-m-green' onclick='giveLike(this," + listData[i].sonList[j].id + ")'></i>";
			mStr += "<label>" + listData[i].sonList[j].good + "</label></a></p></div></div>";
		}
		if(listData[i].sonSize>3){
			mStr+="<div class='loadMoreSonMessage' onclick='showMoresMessage(this)'>更多回复</div>";
		}
		mStr+= "</div></div></div>";
	}
	$(".main_left_main .loadMoreMessage").before(mStr);
	if(listData.length==0){
		$(".loadMoreMessage").html("没有数据").attr("onclick", "javascipt:void(0);").css("cursor","default");
		return;
	}
	if (listData.length<4) {
		$(".loadMoreMessage").html("已经没有了！").attr("onclick", "javascipt:void(0);").css("cursor","default");
	}
}
//此方法给editor的提交内容按钮用
function post_data(commentData, fatherId, tihsClick) {
	var userData = getCookieUserData();
	if (userData == null) {
		showLoginFrame();
		return;
	}
	var url = "/ClubSystem/message/save";
	//判断父留言还是子留言
	if (fatherId == null) {
		var mparm = "detailId=" + userData.id + "&content=" + html_encode(commentData.trim());
	} else {
		var mparm = "fatherId=" + fatherId + "&detailId=" + userData.id + "&content=" + html_encode(commentData.trim());
	}
	$.post(url, mparm, function(data) {
		if (data.serviceResult) {
			var mdata = data.resultParm.message;
			var mStr = "";
			if (fatherId == null) {
				mStr += "<div class='comment_item'><div class='img_radius'><img src='../.." + mdata.headPath + "' width='100%' height='100%'></div>";
				mStr += "<div class='item_main'><div class='item_row_one'><b>" + mdata.userName + "</b><span>" + mdata.createtime + "</span></div>";
				mStr += "<div class='item_row_tow'><p>" + html_decode(mdata.content) + "</p></div>";
				mStr += "<div class='item_row_three'><a href='javascipt:void(0);'><i class='icon-m icon-response-m' onclick='showEditor(this)'></i><label>" + mdata.sonSize + "</label></a>";
				mStr += "<a href='javascipt:void(0);'><i class='icon-m icon-praise-m' onclick='giveLike(this," + mdata.id + ")'></i><label>" + mdata.good + "</label></a></div>";
				mStr += "<div class='item_row_four'><div class='editor' style='display:none;'>";
				mStr += "<div class='editorContent' contenteditable='true'></div>";
				mStr += "<div class='toolBar'><span class='expression' title='表情' onclick='showExpression(this)'></span></div>";
				mStr += "<button class='comment_post' onclick='comment_post(this," + mdata.id + ")'>提交评论</button>";
				mStr += "<button class='comment_cancel' onclick='comment_cancel(this)'>取消</button></div>";
				$(".main_left_main").prepend(mStr);
				intiEditor();
			} else {
				mStr = "<div class='response_item'>";
				mStr += "<div class='response_item_img'><img src='../.." + mdata.headPath + "' width='100%' height='100%'></div>";
				mStr += "<div class='response_item_main'>";
				mStr += "<p><b>" + mdata.userName + "</b>" + html_decode(mdata.content) + "</p>";
				mStr += "<p class='create_date'>" + mdata.createtime + "<a href='javascipt:void(0);'><i class='icon-m icon-praise-m-green' onclick='giveLike(this," + mdata.id + ")'></i>";
				mStr += "<label>" + mdata.good + "</label></a></p></div></div>";
				$(tihsClick).parent().after(mStr);
				var sonSize = $(tihsClick).parents(".item_row_four").siblings(".item_row_three").find(".icon-response-m").next().html();
				$(tihsClick).parents(".item_row_four").siblings(".item_row_three").find(".icon-response-m").next().html(parseInt(sonSize) + 1);
			}
		}
	});
}

function loadMoreMessage(index) {
	var url = "/ClubSystem/message/getOneMessgae";
	var mparm = "indexPageNum=" + index + "&size=4";
	$.get(url, mparm, function(data) {
		if (data.serviceResult) {
			var listData = data.resultParm.message;
			showMessageList(listData);
			if (listData.length<4) {
				$(".loadMoreMessage").html("已经没有了！").attr("onclick", "javascipt:void(0);").css("cursor","default");
			}else{
				$(".loadMoreMessage").attr("onclick","loadMoreMessage("+(++index)+")");
			}
		}
	});
}
//显示更多子评论
function showMoresMessage(thisClick){
	var num = $(thisClick).siblings(".response_item:hidden").length;
	for (var i = 0; i <num; i++) {
		if (i==3) {
			break;
		}
		$(thisClick).siblings(".response_item:hidden").eq(0).css("display","block");
	}
	if (num<3) {
		$(thisClick).html("已经没有了！").attr("onclick", "javascipt:void(0);").css("cursor","default");
	}
}
//显示输入框
function showEditor(thisClick) {
	$(thisClick).parents(".item_row_three").siblings(".item_row_four").find(".editor").show();
}
//点赞
function giveLike(thisClick,id){
	var num = $(thisClick).siblings("label").html();
	var url="/ClubSystem/message/update";
	var mparm = "id="+id+"&good="+(parseInt(num)+1);
	$.post(url, mparm, function(data){
		if (data.serviceResult) {
			var mData = data.resultParm.message;
			$(thisClick).siblings("label").html(mData.good);
		}
	});
}


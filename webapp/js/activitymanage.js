$(document).ready(function(){
	showUserData();
	getMyCreateClub();
	editor='';
})

function showClubActivity(index) {	var url="/ClubSystem/activity/getClubActivity";
	var mparm = "clubId="+clubId+"&indexPageNum="+index+"&size=10";
	$.get(url, mparm, function (data) {
		if (data.serviceResult) {
			var total = data.resultParm.total;
			var listData = data.resultParm.activity;
			var str="";
			for (var i = 0; i <listData.length; i++) {
				str+='<li class="activity_list_item"><div class="item_title">';
				str+='<a href="javascript:void(0)" onclick="showDetail('+listData[i].id+')">'+html_decode(listData[i].title)+'</a></div>'
				str+='<div class="item_time"><a href="javascript:void(0)" onclick="showDetail('+listData[i].id+')">'+listData[i].createtime+'</a></div>'
				str+='<div class="item_btn"><a href="javascript:void(0)" onclick="changeActivity('+listData[i].id+')">修改</a><a href="javascript:void(0)" onclick="deleteActivity('+listData[i].id+')">删除</a></div>';
			}
			if (index==1) {
				$(".activity_list ul").html(str);
			}else{
				$(".activity_list ul").append(str);
			}
			if (listData.length<10) {
				$(".loadMoreActivity").attr("onclick","javascript:void(0)").css({"background-color":"#ccc","cursor":"default"}).html("已经没了！");
			}else{
				$(".loadMoreActivity").attr("onclick","showClubActivity("+(++index)+")").html("加载更多");
			}
		}else{
			$(".loadMoreActivity").attr("onclick","javascript:void(0)").css({"background-color":"#ccc","cursor":"default"}).html("已经没了！");
		}
	})
}
function createActivity(thisClick){
	$(".activity_list").hide();
	$(thisClick).attr("onclick","backList(this)").html("返回列表");
	showCreateFrame();
}
function backList(thisClick){
	if (editor) {
		editor.destroy();
		editor = null;
	}
	$(".createFrame").remove();
	$(".showDetail").remove();
	$(".upDataFrame").remove();
	$(".activity_list").show();
	$(thisClick).attr("onclick","createActivity(this)").html("创建活动");
}
function changeActivity(id){
	$(".activity_list").hide();
	$(".top_tab a").attr("onclick","backList(this)").html("返回列表");
	var url="/ClubSystem/activity/getDetails";
	var mparm = "id="+id;
	$.get(url, mparm, function(data){
		if (data.serviceResult) {
			var aData = data.resultParm.activity
			var str='<div class="upDataFrame"><div class="activity_title"><span>活动标题</span><input type="text" name="activity_title"/></div><div class="activity_content"><div class="content_lebel">活动内容</div><div id="editor"></div></div><div id="saveActivity"onclick="updataActivity('+id+')">保存</div></div>';
			$(".cmm_content").append(str);
			if (editor){
				return;
			}
			editor = CKEDITOR.appendTo( 'editor',null,'');
			$(".upDataFrame .activity_title input").val(html_decode(aData.title));
			editor.setData(html_decode(aData.content));
		}
	})
}
function updataActivity(id) {
	if (userData==null) {
		showLoginFrame();
		return;
	}
	var title = $(".upDataFrame .activity_title input").val();
	var content = editor.getData();
	var url="/ClubSystem/activity/update";
	var mparm = "id="+id+"&title="+html_encode(title)+"&content="+html_encode(content);
	$.post(url, mparm, function (data) {
		if (data.serviceResult) {
			window.location.reload();
		}
	})
}

function deleteActivity(id){
	var url="/ClubSystem/activity/deleteById";
	var mparm = "id="+id;
	$.post(url, mparm, function(data) {
		if (data.serviceResult) {
			alert(data.resultInfo);
			window.location.reload();
		}else{
			alert(data.resultInfo);
		}
	})
}

function showDetail(id){
	var url="/ClubSystem/activity/getDetails";
	var mparm = "id="+id;
	$.get(url, mparm, function(data){
		if (data.serviceResult) {
			var aData = data.resultParm.activity;
			var str='<div class="showDetail">'+html_decode(aData.content)+'</div>';
			$(".activity_list").hide();
			$(".top_tab a").attr("onclick","backList(this)").html("返回列表");
			$(".cmm_content").append(str);
		}
	})
}

function showCreateFrame(){
	var str='<div class="createFrame"><div class="activity_title"><span>活动标题</span><input type="text" name="activity_title"/></div><div class="activity_content"><div class="content_lebel">活动内容</div><div id="editor"></div></div><div id="saveActivity"onclick="saveActivity()">发布</div></div>';
	$(".cmm_content").append(str);
	if (editor){
		return;
	}
	editor = CKEDITOR.appendTo( 'editor',null,'');
}
function saveActivity(){
	if (userData==null) {
		showLoginFrame();
		return;
	}
	var title = $(".createFrame .activity_title input").val();
	var content = editor.getData();
	var url="/ClubSystem/activity/save";
	var mparm = "clubId="+clubId+"&title="+html_encode(title)+"&content="+html_encode(content);
	$.post(url, mparm, function (data) {
		if (data.serviceResult) {
			window.location.reload();
		}
	})
}
function getMyCreateClub() {
	clubId='';
	var url="/ClubSystem/club/getMyClub";
	var mparm = "detailId="+userData.id;
	$.get(url, mparm, function (data) {
		if (data.serviceResult) {
			clubId = data.resultParm.club.id;
			showClubActivity(1);
		}
	})
}

function showUserData() {
	userData = getCookieUserData();
	if (userData==null) {
		showLoginFrame();
		return;
	}
	$(".header_photo img").attr("src","/ClubSystem"+userData.headPath);
	$(".header_photo p").html(userData.name);

}

//替换转义
function html_encode(str) {
    var s = "";
    if (str.length == 0)
        return "";
    s=str.replace(/%/g,"[p];");
    s=s.replace(/&/g,"[a];");
    s=s.replace(/\+/g,"[ad];");
    return s;
}
//替换转义
function html_decode(str) {
	var s = "";
	if (str.length == 0) {
		return "";
	}
	s = str.replace(/&lt;/g, "<");
	s = s.replace(/&gt;/g, ">");
	s=s.replace(/\[p\];/g,"%");
    s=s.replace(/\[a\];/g,"&");
    s=s.replace(/\[ad\];/g,"+");
	return s;
}
//条用此接口才能生效
function intiEditor(){
	intiEditorBtn();
}

function showExpression(index){
	window.event.stopPropagation();
	if($(".expression-panel").length!=0){
		$(".expression-panel").remove();
	};
	var arrEp=getExpression();
	var epAtr="<div class='expression-panel'><ul>";
	for (var j = 0; j<arrEp.length; j++) {
		epAtr+="<li><img src='"+getPath()+"/images/expression/"+arrEp[j]+"'></li>";
	}
	epAtr+="</ul></div>";
	$(index).parent().after(epAtr);
	initExpressionEvent(index);

}
//获取项目根路径
function getPath(){
	 //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	  var curWwwPath = window.document.location.href;
	  //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	  var pathName = window.document.location.pathname;
	  var pos = curWwwPath.indexOf(pathName);
	  //获取主机地址，如： http://localhost:8083
	  var localhostPaht = curWwwPath.substring(0, pos);
	  //获取带"/"的项目名，如：/uimcardprj
     var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
     return projectName;
}
function getExpression(){
	var str="";
	for (var i = 1; i <=100; i++) {
		if (i==1) {
			str+=i+".gif";
		}else{
			str+=";"+i+".gif";
		}
	}
	return str.split(";");
}
function initExpressionEvent(index){
	$(index).parent().siblings(".expression-panel").find("ul li").click(function (){
		var eindex = $(this).index();
		var epStr="<img src='"+getPath()+"/images/expression/"+(eindex+1)+".gif'>";
		$(index).parent().siblings(".editorContent").append(epStr);

	});
	$(document).click(function(){
		if ($(".expression-panel").length==1) {
			$(".expression-panel").remove();
		}
	});
}
function intiEditorBtn(){
	$(".editorContent").focus(function(){
		if ($(".editorContent.isOpen").length!=0) {
			$(".editorContent.isOpen").siblings(".toolBar").hide();
			$(".editorContent.isOpen").siblings(".comment_post").hide();
			$(".editorContent.isOpen").siblings(".comment_cancel").hide();
			$(".editorContent.isOpen").removeClass("isOpen");
		};
		$(this).addClass("isOpen");
		$(this).siblings(".toolBar").show();
		$(this).siblings(".comment_post").show();
		$(this).siblings(".comment_cancel").show();
	});
}
function comment_post(index,fatherId) {
	var comment = $(index).siblings(".editorContent").html().toString();
	post_data(comment,fatherId,index);
	$(index).siblings(".editorContent").html("");
	$(index).siblings(".toolBar").hide();
	$(index).siblings(".comment_cancel").hide();
	$(index).hide();
}

function comment_cancel(index){
	$(index).siblings(".editorContent").html("");
	$(index).siblings(".toolBar").hide();
	$(index).siblings(".comment_post").hide();
	$(index).hide();
}
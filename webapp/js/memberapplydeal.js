var applyId;
$(document).ready(function(){
	initApplyList();
});
function showApplyList(applyList){
	for(var i=0; i<applyList.length;i++){
		var sex = "";
		if(applyList[i].sex){
			sex = "男";
		}else{
			sex = "女";
		}
		$(".cmm_head_nav").append('<div class="sq_item" id="'+applyList[i].id+'" onmouseenter="detailShow(this)" onmouseleave="detailHide(this)"><div class="sq_item_box"><img src="../../images/ccbg.jpg" width="70px" height="70px" style="margin-right:10px;"><textarea cols="88" rows="3" style="position:absolute;margin-top:0;margin-left:80px;" disabled="disabled">'+applyList[i].reason+'</textarea><span style="padding-right:50px;font-size:12px;">申请日期:'+applyList[i].createtime.split(" ")[0]+'</span><span style="padding-right:50px;font-size:12px;">审核状态：未审核</span><button aciton="#" class="sq_item_btn" onclick="applyAgree(this)" style="margin-left:200px;">同意</button> <button action="#" onclick="applyRefuce(this)" class="sq_item_btn">拒绝</button><div class="sq_details" id="'+applyList[i].id+'_details"><img src="../../images/ccbg.jpg" width="116px" height="116px"style="position:absolute;margin-top:10px;margin-left:295px;border:1px solid #ccc"><div style="margin:10px 0 6px 0 ;"><span>姓名</span><input type="text" disabled="disabled" value="'+applyList[i].name+'"></div><div><span>性别</span><input type="text" disabled="disabled" value="'+sex+'"></div><div><span>年龄</span><input type="text" disabled="disabled" value="'+applyList[i].age+'"></div><div><span>民族</span><input type="text" disabled="disabled" value="'+applyList[i].nation+'"></div><div><span>专业</span><input type="text" disabled="disabled" value="'+applyList[i].majorClass+'"></div><div><span>电话</span><input type="text" disabled="disabled" value="'+applyList[i].phone+'"></div><div><span>邮箱</span><input type="text" disabled="disabled" value="'+applyList[i].email+'"></div><div style="margin-top:2px;"><span style="position:absolute;top:130px;left:205px;">简介</span><textarea cols="20" rows="4" disabled="disabled" style="border:1px solid #ccc;position:absolute;top:135px;left:250px;">'+applyList[i].introduce+'</textarea></div><div style="margin-top:2px;"><span style="position:absolute;top:210px;left:205px;">爱好</span><textarea cols="20" rows="4" disabled="disabled" style="border:1px solid #ccc;position:absolute;top:207px;left:250px;">'+applyList[i].hobby+'</textarea></div></div></div></div>');
	}
	
}
function initApplyList(){
	userData = getCookieUserData();
	if(userData==null){
		showLoginFrame();
		return;
	}
	var url = "/ClubSystem/apply/displayAllClubApply";
	var parm = "detailId="+userData.id+"&statue=2";
	$.get(url,parm,function(data){
		if(data.serviceResult){
			var applyList = data.resultParm.apply;
			showApplyList(applyList);
		}else{
			alert(data.resultInfo);
			window.location.href ="/ClubSystem/views/club/tocreateclub.html";
		}
	});
}
function detailShow(which){
	$(which).children().children(".sq_details").show();
}
function detailHide(which){
	$(which).children().children(".sq_details").hide();
}
function applyAgree(which){
	var applyId = $(which).parent().parent().attr("id");
	var url = "/ClubSystem/apply/isAgree";
	var parm = "applyId="+applyId+"&statue=0";
	$.post(url,parm,function(data){
		if(data.serviceResult){
			$(".cmm_head_nav #"+applyId).remove();
			alert(data.resultInfo);
		}else{
			alert(data.resultInfo);
		}
	});
}
function applyRefuce(which){
	var applyId = $(which).parent().parent().attr("id");
	var url = "/ClubSystem/apply/isAgree";
	var parm = "applyId="+applyId+"&statue=1";
	console.log(parm);
	$.post(url,parm,function(data){
		if(data.serviceResult){
			$(".cmm_head_nav #"+applyId).remove();
			alert(data.resultInfo);
		}else{
			alert(data.resultInfo);
		}
	});
}
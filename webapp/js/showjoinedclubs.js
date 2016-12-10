$(document).ready(function(){
	initClubList();
});
function showClubList(clubList){
	for(var i = 0; i<clubList.length; i++){
		$(".lwd_main_iframe").append('<div class="club_box" id="'+clubList[i].id+'"><div class="club_box_pic"><img src="../..'+clubList[i].badge+'" width="140px" height="140px"><br><button><a href="showclubinformation.html">进入社团</a></button> <button action="#">退出社团</button></div><div class="club_box_text"><strong class="clubtitle">'+clubList[i].name+'</strong><span class="ccdate">创社时间：'+clubList[i].createtime.split(" ")[0]+'</span><div class="clubdet"><div class="clubdet_item"><p style="color:#999;">社长</p><p style="color:#666;">'+clubList[i].leader+'</p></div><div class="clubdet_item"><p style="color:#999;">社团类型</p><p style="color:#666;">'+clubList[i].typeName+'</p></div><div class="clubdet_item" style="border-right:0;"><p style="color:#999;">规模</p><p style="color:#666;">'+clubList[i].number+'人</p></div></div><textarea cols="70" rows="4" class="clubint" disabled="disabled">&nbsp;&nbsp;'+clubList[i].introduce+'</textarea></div></div>');
	}
}
function initClubList(){
	var url = "/ClubSystem/club/getMyClubList";
	var parm = "detailId=3";
	$.get(url,parm,function(data){
		if(data.serviceResult){
			var clubList = data.resultParm.club;
			showClubList(clubList);
		}else{
			$(".lwd_main_iframe").append('<div class="noMore">您还没有加入社团，赶紧去申请吧！</div>');
		}
	});
}
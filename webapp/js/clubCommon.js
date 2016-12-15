$(document).ready(function(){
	initUserLeft();
});
function initUserLeft(){
	 var userData = getCookieUserData();
	 $("#head").attr("src","../.."+userData.headPath);
	 id = userData.id;
	 $("#userName").text(userData.name);
}
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null){
    	return unescape(r[2]);
    }else{
    	return null; //返回参数值
    }
}
function setPositionHref(){
	var url = "/ClubSystem/club/getMyClub";
	var parm = "detailId="+getCookieUserData().id;
	$.get(url,parm,function(data){
		if(data.serviceResult){
			$("#positionhref").attr("href","positionmanage.html?clubId="+data.resultParm.club.id);
		}else{
		}
	});
}
function setMyJoinedClubHref(){
	$("#clubdetailhref").attr("href","showclubinformation.html?clubId="+getUrlParam("clubId"));
	$("#clubalbumhref").attr("href","showclubalbums.html?clubId="+getUrlParam("clubId"));
	$("#clubactivityhref").attr("href","showclubactivity.html?clubId="+getUrlParam("clubId"));
	$("#myinformationhref").attr("href","showmyinformation.html?clubId="+getUrlParam("clubId"));
}
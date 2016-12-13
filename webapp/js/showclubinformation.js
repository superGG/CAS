$(document).ready(function(){
	setMyJoinedClubHref();
	initClubInformation();
});
function initClubInformation(){
	var url = "/ClubSystem/club/getMyJoinClub";
	var parm = "clubId="+getUrlParam("clubId");
	$.get(url,parm,function(data){
		if(data.serviceResult){
			var club = data.resultParm.club;
			$("#badge").attr("src","../.."+club.badge);
			$("#name").text(club.name);
			$("#createtime").text("创建时间："+club.createtime.split(" ")[0]);
			$("#leader").text(club.leader);
			$("#type").text(club.typeName);
			$("#number").text(club.number+"人");
			$("#introduce").text(club.introduce);
		}
	});
}
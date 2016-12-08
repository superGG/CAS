var clubId;
$(document).ready(function(){
	initClubInformation();
	updateClub();
});
function initClubInformation(){
	var url = "/ClubSystem/club/getMyClub";
	var parm = "detailId=1";
	var typeUrl = "/ClubSystem/clubtype/getAlls";
	$.get(url,parm,function(data){
		if(data.serviceResult){
			var club = data.resultParm.club;
			clubId = club.id;
			$("#badge").attr("src","../.."+club.badge);
			$("#school").val(club.schoolName);
			$("#clubname").val(club.name);
			
		//	$("#type").val(club.typeName);
			$.get(typeUrl,function(data){
				if(data.serviceResult){
					var typeList = data.resultParm.clubType;
					showClubType(typeList);
					$("#type option:contains('"+club.typeName+"')").attr("selected", true);
				}
			});
			$("#createtime").val(club.createtime.split(" ")[0]);
			$("#leader").val(club.leader);
			
			$("#number").val(club.number);
			$("#phone").val(club.phone);
			$("#email").val(club.email);
			$("#profile").val(club.introduce);
		}else{
			alert(data.resultInfo);
		}
	});
}
function showClubType(typeList){
	for(var i = 0;i<typeList.length;i++){
		$("#type").append('<option value ="'+(i+1)+'">'+typeList[i].name+'</option>');
	}
}
function updateClub(){
	$("#update").click(function(){
		
		var index = document.getElementById('type').selectedIndex
		var id = clubId;
		var name = document.getElementById('clubname').value;
		var typeName = document.getElementById('type').options[index].text;
		var phone = document.getElementById('phone').value;
		var email = document.getElementById('email').value;
		var introduce = document.getElementById('profile').value;
		
		var updateUrl = "/ClubSystem/club/updateMyClub";
		var updateParm = "id="+id+"&name="+name+"&typeName="+typeName+"&phone="+phone+"&email="+email+"&introduce="+introduce;
		$.post(updateUrl,updateParm,function(data){
			if(data.serviceResult){
				var club = data.resultParm.club;
				$("#badge").attr("src","../.."+club.badge);
				$("#school").val(club.schoolName);
				$("#clubname").val(club.name);
				
				var typeUrl = "/ClubSystem/clubtype/getAlls";
				$.get(typeUrl,function(data){
					if(data.serviceResult){
						var typeList = data.resultParm.clubType;
						showClubType(typeList);
						$("#type option:contains('"+club.typeName+"')").attr("selected", true);
					}
				});
				$("#createtime").val(club.createtime.split(" ")[0]);
				$("#leader").val(club.leader);
				$("#number").val(club.number);
				$("#phone").val(club.phone);
				$("#email").val(club.email);
				$("#profile").val(club.introduce);
				alert(data.resultInfo);
			}else{
				alert(data.resultInfo);
			}
		});
	});
}
$(document).ready(function(){
	
});
function showTable(){
	initTable();
	$("#applytable").show();
}
function closeTable(){
	$("#applytable").hide();
}
function initTable(){
	initSchoolList();
	initTypeList();
}
function initSchoolList(){
	var url = "/ClubSystem/school/getAlls";
	$.get(url,function(data){
		if(data.serviceResult){
			console.log(data);
			var schoolList = data.resultParm.school;
			showSchoolList(schoolList);;
		}else{
			alert(data.resultInfo);
		}
	});
}
function initTypeList(){
	var url = "/ClubSystem/clubtype/getAlls";
	$.get(url,function(data){
		if(data.serviceResult){
			console.log(data);
			var typeList = data.resultParm.clubType;
			showTypeList(typeList);
		}else{
			alert(data.resultInfo);
		}
	});
}
function showSchoolList(schoolList){
	for(var i = 0;i<schoolList.length;i++){
		$("#schoolName").append('<option value="'+schoolList[i].name+'">'+schoolList[i].name+'</option>');
	}
}
function showTypeList(typeList){
	for(var i = 0;i<typeList.length;i++){
		$("#typeName").append('<option value="'+typeList[i].name+'">'+typeList[i].name+'</option>');
	}
}
function createClub(){
	var userDate = getCookieUserData();
	var detaliId = userDate.id;
	var schoolIndex = document.getElementById('schoolName').selectedIndex;
	var schoolName = document.getElementById('schoolName').options[schoolIndex].text;
	var typeIndex = document.getElementById('typeName').selectedIndex;
	var typeName = document.getElementById('typeName').options[typeIndex].text;
	var name = $("#name").val();
	var phone = $("#phone").val();
	var introduce = $("#reason").val();
	var url = "/ClubSystem/club/create";
	var parm = "detailId="+detaliId+"&schoolName="+schoolName+"&clubType="+typeName+"&name="+name+"&phone="+phone+"&introduce="+introduce;
	$.post(url,parm,function(data){
		if(data.serviceResult){
			alert(data.resultInfo);
			window.location.href ="/ClubSystem/views/club/clubinformationmanage.html";
		}else{
			alert(data.resultInfo);
		}
	});
}
$(document).ready(function(){
	 getActivityList("1");
})

function getActivityList(key){
	switch(key){
		case "1":console.log("全部");break;
		case "2":console.log("最新");break;
		default: console.log(key);break;
	}
}
function selectChange(){
	getActivityList($('.activity-select option:selected').val());
}

function searchActivity() {
	var key = $("input[name='key']").val();
	getActivityList(key);
}
function loadMoreActivity() {
	
}
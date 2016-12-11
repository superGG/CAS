$(document).ready(function(){
	initClubPosition();
	positionAdd();
});
function showClubPosition(positionList){
	for(var i = 0;i<positionList.length;i++){
		$("#position_table").append('<tr id="'+positionList[i].id+'"><td >'+(i+1)+'</td><td class="position">'+positionList[i].name+'</td><td><button class="bige_btn" onclick="editPosition(this)">修改</button> <button class="bige_btn" onclick="positionDelete(this)">删除</button></td></tr>');
	}
}
function initClubPosition(){
	var url = "/ClubSystem/position/getByClubId";
	var parm = "id=2";
	$.get(url,parm,function(data){
		if(data.serviceResult){
			console.log(data);
			var positionList = data.resultParm.position;
			showClubPosition(positionList);
		}else{
			alert(data.resultInfo);
		}
	});
}
var statu = 0;                       
function editPosition(which){
	if(statu == 0){
		var oldName = $(which).parent().parent().children(".position").text();
		$(which).text("保存");  
		$(which).parent().parent().children(".position").text("");
		$(which).parent().parent().children(".position").append('<input type="text" style="margin:0;padding:3px 0;text-align:center;width:100px;" id="item_input" value="'+oldName+'">');
		$(which).parent().parent().children(".position").children("input").focus();
		statu = 1;                
	}
	else{
		if($(which).parent().siblings(".position").find("input").length<=0){
			alert("请先保存！");
			return;
		}
		var newName = $(which).parent().parent().children(".position").children().val();
		var url = "/ClubSystem/position/update";
		var parm = "id="+$(which).parent().parent().attr("id")+"&name="+newName;
		$.post(url,parm,function(data){
			if(data.serviceResult){
				$(which).text("修改");
				$(which).parent().parent().children(".position").children().remove();
				$(which).parent().parent().children(".position").text(newName);
				alert(data.resultInfo);
			}else{
				alert(data.resultInfo);
			}
		});
		statu = 0
	}               
	return false;     
} ;

function positionAdd(){
	$("#position_add").click(function(){
		if($("#position_name").val()!=""){
			var positionName = $("#position_name").val();
			var url = "/ClubSystem/position/save";
			var parm = "clubId=2&name="+positionName;
			$.post(url,parm,function(data){
				if(data.serviceResult){
					$("#position_table tr").remove();
					initClubPosition();
					alert(data.resultInfo);
				}else{
					alert(data.resultInfo);
				}
			});
		}else{
			alert("职位名称不能为空！");
		}
	});
}

function positionDelete(which){
	if($("#position_table input").length>0){
		alert("请先保存！");
		return;
	}
	var url =  "/ClubSystem/position/delete";
	var parm = "positionId="+$(which).parent().parent().attr("id");
	$.post(url,parm,function(data){
		if(data.serviceResult){
			alert(data.resultInfo);
		}else{
			alert(data.resultInfo);
		}
	});
	$(which).parent().parent().remove();
}
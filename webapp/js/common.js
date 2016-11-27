$(document).ready(function(){
	
})
/*登录页面内容*/
function showLoginFrame() {
	 // 获取页面高度
     var sHeight = document.documentElement.scrollHeight;
     var sWidth = document.documentElement.scrollWidth;
     // 可视区域的高度和宽度
     var wHeight = document.documentElement.clientHeight;
     var wWidth = document.documentElement.clientWidth;
     var mMask = document.createElement("div");
     mMask.id = "mask";
     mMask.style.height = sHeight+"px";
     mMask.style.width = sWidth+"px";
     document.body.appendChild(mMask);

     var addCom = document.createElement("div");
      addCom.id = "mForm";
      var str = "<div id='close'>X</div><form id='loginForm' class='' method='POST'><fieldset><legend class=''>登录</legend>";
      str+="<div class='input-style'>用户名:<input name='username' class='' type='text' ></div> ";
      str+="<div class='input-style'>密&nabp;码:<input name='password' class='' type='password' ></div> ";
       str+="<div class='input-style'>验证码:<img src='' id='checkCode'> </div> ";
      str+="</fieldset></form>";
      addCom.innerHTML=str;
      document.body.appendChild(addCom);
      // 获得元素高度、宽度
      var dHeight = addCom.offsetHeight;
      var dWidth = addCom.offsetWidth;
      addCom.style.left = (wWidth-dWidth)/2+"px";
      addCom.style.top = (wHeight-dHeight)/2+"px"; 
      var mClose=document.getElementById("close");
      mClose.onclick=function(){
        document.body.removeChild(mMask);
        document.body.removeChild(addCom);
      }
      getCheckCode();
}

function getCheckCode() {
	var url ="http://192.168.1.101:8080/ClubSystem/users/getImgVerifyCode";
	// $.getJSON(url,function (data) {
 //        	var data = eval('('+ data +')');
	// 	 alert(data.resultInfo);
	// 	$("checkCode").attr("src","http://192.168.1.101:8080/ClubSystem/"+data.resultPram.path);
 //        });
	$.ajax({
        url: "http://192.168.1.101:8080/ClubSystem/users/getImgVerifyCode?jsoncallback=?",
        type: 'GET',
        dataType: 'JSONP',//here
        success: function (data) {
        	var data = eval('('+ data +')');
		 alert(data.resultInfo);
		$("checkCode").attr("src","http://192.168.1.101:8080/ClubSystem/"+data.resultPram.path);
        }
    });
}

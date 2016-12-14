//自定义校验方法
$().ready(function() {
	
	jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {
		var length = value.length;
		for(var i = 0; i < value.length; i++){
			if(value.charCodeAt(i) > 127){
				length++;
			}
		}
		return this.optional(element) || ( length >= param[0] && length <= param[1] );   
	}, $.validator.format("请确保输入的值在大于2个中文字符"));
	
	
 	jQuery.validator.addMethod("age", function(value, element) {   
		var age = /^(?:[1-9][0-9]?|1[01][0-9]|120)$/;
		return this.optional(element) || (age.test(value));
	}, "格式不对，不能超过120"); 
 	
	jQuery.validator.addMethod("chinese", function (value, element) {
	    var chinese = /^[\u4E00-\u9FFF]+$/;
	    return this.optional(element) || (chinese.test(value));
	}, "格式不对");
	
	jQuery.validator.addMethod("isPhone", function(value,element) {   
	    var length = value.length;   
	    var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;   
	     var tel = /^\d{3,4}-?\d{7,9}$/;   
	    return this.optional(element) || (tel.test(value) || mobile.test(value));   
	 
	}, "请正确填写您的联系电话"); 
});
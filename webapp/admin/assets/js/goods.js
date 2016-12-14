$(function(){
	//获得所有商家信息
  $.get(baseUrl + "/goods/all/", function(result){

         console.log($($("tbody")[0]));

         console.log(result);
 
         result.resultParm.goodsList.forEach((goods)=>{
             console.log(goods);
             $($("tbody")[0]).append($('<tr><td class="center"><label><input type="checkbox"class="ace"><span class="lbl"></span></label></td><td><a href="#">'+goods.name+'</a></td><td>￥'+goods.price+'</td><td class="hidden-480">'+goods.introduce+'</td><td>'+new Date(goods.lastModifiedDate).Format("yyyy-MM-dd")+'</td><td class="hidden-480"><span class="label label-sm label-success">Registered</span></td><td><div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button class="btn btn-xs btn-success"><i class="icon-ok bigger-120"></i></button><button class="btn btn-xs btn-info"><i class="icon-edit bigger-120"></i></button><button class="btn btn-xs btn-danger"><i class="icon-trash bigger-120"></i></button><button class="btn btn-xs btn-warning"><i class="icon-flag bigger-120"></i></button></div><div class="visible-xs visible-sm hidden-md hidden-lg"><div class="inline position-relative"><button class="btn btn-minier btn-primary dropdown-toggle"data-toggle="dropdown"><i class="icon-cog icon-only bigger-110"></i></button><ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close"><li><a href="#"class="tooltip-info"data-rel="tooltip"title="View"><span class="blue"><i class="icon-zoom-in bigger-120"></i></span></a></li><li><a href="#"class="tooltip-success"data-rel="tooltip"title="Edit"><span class="green"><i class="icon-edit bigger-120"></i></span></a></li><li><a href="#"class="tooltip-error"data-rel="tooltip"title="Delete"><span class="red"><i class="icon-trash bigger-120"></i></span></a></li></ul></div></div></td></tr>'));
        });
    });
});
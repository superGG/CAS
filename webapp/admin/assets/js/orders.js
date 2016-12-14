$(function(){
	//获得所有商家信息
  $.get(baseUrl + "/orders/all/", function(result){

         console.log($($("tbody")[0]));

         console.log(result);

         result.resultParm.ordersList.forEach((orders)=>{
             console.log(orders);
             $($("tbody")[0]).append($('<tr><td class="center"><label><input type="checkbox"class="ace"><span class="lbl"></span></label></td><td><a href="#">'+orders.id+'</a></td><td>'+orders.number+'</td><td>￥'+orders.totalPrice+'</td><td class="hidden-480">'+payTypeToText(orders.payType)+'</td><td>'+new Date(orders.lastModifiedDate).Format("yyyy-MM-dd")+'</td><td class="hidden-480">'+statusToText(orders.status)+'</td><td><div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button class="btn btn-xs btn-success"><i class="icon-ok bigger-120"></i></button><button class="btn btn-xs btn-info"><i class="icon-edit bigger-120"></i></button><button class="btn btn-xs btn-danger"><i class="icon-trash bigger-120"></i></button><button class="btn btn-xs btn-warning"><i class="icon-flag bigger-120"></i></button></div><div class="visible-xs visible-sm hidden-md hidden-lg"><div class="inline position-relative"><button class="btn btn-minier btn-primary dropdown-toggle"data-toggle="dropdown"><i class="icon-cog icon-only bigger-110"></i></button><ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close"><li><a href="#"class="tooltip-info"data-rel="tooltip"title="View"><span class="blue"><i class="icon-zoom-in bigger-120"></i></span></a></li><li><a href="#"class="tooltip-success"data-rel="tooltip"title="Edit"><span class="green"><i class="icon-edit bigger-120"></i></span></a></li><li><a href="#"class="tooltip-error"data-rel="tooltip"title="Delete"><span class="red"><i class="icon-trash bigger-120"></i></span></a></li></ul></div></div></td></tr>'));
         });
    });
});

function payTypeToText(paytype){
    if(paytype == 1){
        return '<span class="label label-sm label-success">在线支付</span>';
    }else if(paytype == 2){
        return '<span class="label label-sm label-warning">货到付款</span>';
    }else return '<span class="label label-sm label-inverse arrowed-in">未知状态'+paytype+'</span>';
}

function statusToText(status){
    if(status == 1){
        //--label-warning   --label-inverse arrowed-in
        return '<span class="label label-sm label-success">未支付</span>';
    }if(status ==2){
        return '<span class="label label-sm label-success">已支付</span>';
    }if(status ==3){
        return '<span class="label label-sm label-success">已接单</span>';
    }if(status ==4){
        return '<span class="label label-sm label-inverse arrowed-in">正在配送</span>';
    }if(status ==5){
        return '<span class="label label-sm label-success">待评价</span>';
    }if(status ==6){
        return '<span class="label label-sm label-success">已完成</span>';
    }if(status ==7){
        return '<span class="label label-sm label-warning">订单取消</span>';
    }if(status ==8){
        return '<span class="label label-sm label-warning arrowed-in">申请退单失败</span>';

    }else return '<span class="label label-sm label-warning">非法状态'+status+'</span>';
}
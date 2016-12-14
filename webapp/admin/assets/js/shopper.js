$(function(){
    getShoppers();
});

function getShoppers() {
    var getShopperByIdUrl;
    var click;
    //获得所有商家信息
    $.get(baseUrl + "/shopper/all", function(result){
        //清空记录
        $("tbody").empty();
        result.resultParm.shopperList.forEach((shopper)=>{
            console.log(shopper);
            if(shopper.status == 1) {
                $($("tbody")[0]).append($('<tr><td class="center"><label><input type="checkbox"class="ace"/><span class="lbl"></span></label></td><td><a href="#">'+shopper.name+'</a></td><td>'+shopper.address+'</td><td class="hidden-480">'+shopper.contact+'</td><td>'+new Date(shopper.lastModifiedDate).Format("yyyy-MM-dd")+'</td><td class="hidden-480"><span class="label label-sm label-warning">待审核</span></td><td><div class="visible-md visible-lg hidden-sm hidden-xs action-buttons"><a class="btn btn-xs btn-success" onclick="showShopperDetail(' + shopper.userId + ')">审核详情</a><a class="btn btn-xs btn-danger" onclick="deleteShopperById('+ shopper.id +')">删除</a></div></td></tr>'));
            }
            if(shopper.status == 2) {
                $($("tbody")[0]).append($('<tr><td class="center"><label><input type="checkbox"class="ace"/><span class="lbl"></span></label></td><td><a href="#">'+shopper.name+'</a></td><td>'+shopper.address+'</td><td class="hidden-480">'+shopper.contact+'</td><td>'+new Date(shopper.lastModifiedDate).Format("yyyy-MM-dd")+'</td><td class="hidden-480"><span class="label label-sm label-success">审核通过</span></td><td><div class="visible-md visible-lg hidden-sm hidden-xs action-buttons"><a class="btn btn-xs btn-success" onclick="showShopperDetail(' + shopper.userId + ')">审核详情</a><a class="btn btn-xs btn-danger" onclick="deleteShopperById('+ shopper.id +')">删除</a></div></td></tr>'));
            }
            if(shopper.status == 3) {
                $($("tbody")[0]).append($('<tr><td class="center"><label><input type="checkbox"class="ace"/><span class="lbl"></span></label></td><td><a href="#">'+shopper.name+'</a></td><td>'+shopper.address+'</td><td class="hidden-480">'+shopper.contact+'</td><td>'+new Date(shopper.lastModifiedDate).Format("yyyy-MM-dd")+'</td><td class="hidden-480"><span class="label label-sm label-inverse arrowed-in">审核驳回</span></td><td><div class="visible-md visible-lg hidden-sm hidden-xs action-buttons"><a class="btn btn-xs btn-success" onclick="showShopperDetail(' + shopper.userId + ')">审核详情</a><a class="btn btn-xs btn-danger" onclick="deleteShopperById('+ shopper.id +')">删除</a></div></td></tr>'));
            }
        });
    });
}

/**
 * 查看申请详情
 */
function showShopperDetail(userId) {

    $.get(baseUrl + "/shopper/getShopper/" + userId,(result)=>{
        if(result.serviceResult) {
            $("#introduction").val(result.resultParm.shopper.introduction);
            $("#name").val(result.resultParm.shopper.name);
            $("#contact").val(result.resultParm.shopper.contact);
            $("#address").val(result.resultParm.shopper.address);

            if(result.resultParm.shopper.status == 1) {
                $("#status").val("待审核");
                $("#deal").attr("style","display:block");
            } else if(result.resultParm.shopper.status == 2) {
                $("#status").val("审核通过");
                $("#deal").attr("style","display:none");
            } else if(result.resultParm.shopper.status == 3) {
                $("#status").val("审核驳回");
                $("#deal").attr("style","display:none");
            }

            $("#fontImg").attr("src",baseUrl + result.resultParm.shopper.identityImg);
            $("#backImg").attr("src",baseUrl + result.resultParm.shopper.identityBackImg);

            //解除绑定事件
            $("#reject").unbind();
            $("#pass").unbind();

            //审核驳回
            $("#reject").on("click",()=>{
                $("#reject").attr("disabled","disabled");
                $.post(baseUrl + "/shopper/fail/" + result.resultParm.shopper.id,(result)=>{
                    if(result.serviceResult) {
                        alert("操作成功");
                        getShoppers();
                    } else {
                        alert(result.resultInfo);
                    }
                    $('#modal').modal('hide');
                    $("#reject").removeAttr("disabled");
                });
            });

            //审核通过
            $("#pass").on("click",()=>{
                $("#pass").attr("disabled","disabled");
                $.post(baseUrl + "/shopper/success/" + result.resultParm.shopper.id,(result)=>{
                    if(result.serviceResult) {
                        alert("操作成功");
                        getShoppers();
                    } else {
                        alert(result.resultInfo);
                    }
                    $('#modal').modal('hide');
                    $("#pass").removeAttr("disabled");
                });
            });

            $("#modal").modal('show');
        } else {
            alert(result.resultInfo);
        }
    });
}

/**
 * 删除商家申请信息
 * @param id
 */
function deleteShopperById(id) {
    $.get(baseUrl + "/shopper/deleteShopperById/" + id,(result) =>{
        if(result.serviceResult) {
            getShoppers();
        } else {
            alert(result.resultInfo);
        }
    });
}
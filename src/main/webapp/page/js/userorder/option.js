
/**
 * Created by xiangkui on 2015/2/20.
 */
$(document).ready(function(){
    var $table = $('#orderItems_tb');
    var orderId = $("#identifier").val();
    //材料选择-弹出操作框
    $("#products_select").select2({
//        theme: "classic",
        placeholder: "请选择产品项",
        maximumSelectionLength: 1
    });
    $("#users_select").select2({
        placeholder: "请选择用户项",
        maximumSelectionLength: 1
    });
    //选中某个产品
    $("#products_select").on('change',function(){
        var productId = $("#products_select").val();
        productId = productId&&productId[0];
        if(productId){
            $("#item_ok_btn").attr('disabled',true);
            post(urls.product.get,{productId:productId},function(result){
                var product = result.data;
                var danjia = product.danjia;
                $("#danjia").val(danjia);//设定产品单价
                $("#item_ok_btn").attr('disabled',false);
            });
        }else{
            $("#danjia").val("");//设定产品单价
        }
    });
    //确认添加一个产品项
    $("#item_ok_btn").on('click',function(){
        var data = getFormJson("#item_form");
        var productId = data.productId;
        //根据产品id置换出产品信息
        post(urls.product.get,{productId:productId},function(result){
            var product = result.data;
            var product_name = product.name;
            var product_danjia = data.danjia;//使用临时的定价价格
            var product_count = data.count;
            //直接添加到产品项列中
            var result = useTemplateId("order_item_template", {
                    item: $.extend(data,{
                        product_name:product_name,
                        product_danjia:product_danjia,
                        product_count:product_count,
                        jine:product_danjia*product_count
                    })
            });
            $table.find("tbody").append(result);
            $("#products_select").select2("val", "");
            $("#item_form")[0].reset();
            $("#myModal").modal('hide');
        });
    });
    $("#item_close_btn").on('click',function(){
        $("#products_select").select2("val", "");
        $("#item_form")[0].reset();
        $("#myModal").modal('hide');
    });
    // Delete a record
    $table.delegate('a.editor_remove','click', function (e) {
        $(this).parents("tr").remove();
    });
    //保存 材料
    $("#save_btn").on('click',function(){
        var data = getFormJson("#info_form");
        delete data.state;
        delete data.orderTime;
        post(urls.userorder.save,data,function(result){
            $.messager.popup(result._msg);
            var user = result.data;
            if(user){
                var id = user.id;
                $("#identifier").val(id);
            }
//            window.location = urls.user.index;
        });
        return false;
    });
});
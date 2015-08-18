/**
 * Created by xiangkui on 2015/3/4.
 */

$(document).ready(function(){
    var $table = $("#products_tb");
    var $select = $("#products_select");
    var $wizard = $('#myWizard');
    $select.select2({
        placeholder: "Select a state",
        tags: true,
        maximumSelectionLength: 1
    });
    $wizard.wizard();

    //第1步页面---------------------------------------------------------
    //第1步重新计算tfoot
    var ReComputeStep1 = function(){
        var count = 0;//总个数
        $("#products_tb tbody tr").each(function(index,item){
            var $td = $("td:eq(2)",this);
            var data = $td.find('.data-container').html();
            count += parseInt(data);
        });
        $("#products_tb tfoot").find('.count-container').html(count);
    };
    //确认 添加产品项
    $("#confirmItem").on('click',function(){
        var item = getFormJson("#product_item_form");
        var productId = item.productId;
        post(urls.product.get,{productId:productId},function(result){
            var product = result.data;
            var context = {
                productId : product.id,
                name:product.name,
                count: item.count
            };
            var html = useTemplateId("entry_template",context);
            $table.find("tbody").prepend(html);//添加到行收
            ReComputeStep1();
        });
    });
    //第一步-删除
    $table.delegate(".item_remove",'click',function(){
        var $tr = $(this).closest('tr');
        var id = $tr.data("id");
        $tr.remove();
        ReComputeStep1();
    });
    //进1->2页面（出单预览页）
    $('#myWizard').on('actionclicked.fu.wizard', function (evt, data) {
        var step = data.step;
        var direction = data.direction;
        if(!(
            (1== step)&&
            (direction=='next')
            )){//非第二部页面
            return ;
        }
        var items = [];
        $("#products_tb tbody").find("tr").each(function(index,item){
            var $this = $(this);
            var productId = $this.data('productid');
            var name =$this.data('name');
            var count = $this.data('count');
            var item = {
                productId:productId,
                count:count
            };
            items.push(item);
        });
        $("#chudan_container").html('');//清空所有表格，重新计算
        var model  = $("#chudan_model").val();
        var myUrl = '';
        switch(model){
            case '0':
                myUrl = urls.chudan.jianhuodan_result;
                break;
            case '1'://分供货商打单
                myUrl = urls.chudan.jinhuodan_manufacturer;
                break;
            default :
                break;
        };
        post(myUrl,JSON.stringify(items),function(result){
            var zaojias = result.dataList;
            var danjia_showmodel = $('#danjia_showmodel').prop("checked");
            var count_showmodel = $('#count_showmodel').prop("checked");
            var heji_showmodel = $('#heji_showmodel').prop("checked");
            $.each(zaojias,function(index,item){
                var manufacturerName = item.manufacturerName;
                var manufacturerId  =item.manufacturerId;
                if(!item.manufacturerId){
                    manufacturerName = '其他';
                }
                var table_item = useTemplateId("material_table_template",{
                    incrementId:index+1,
                    manufacturerId:manufacturerId,
                    manufacturerName:manufacturerName,
                    danjia_showmodel:danjia_showmodel,
                    count_showmodel:count_showmodel,
                    heji_showmodel:heji_showmodel
                });
                var $table_item = $(table_item);
                var materialItems = item.items;
                $(materialItems).each(function(index,item){
                    var context = {
                        incrementId:index,
                        materialId : item.materialId,
                        materialName:item.materialName,
                        materialdDanjia:item.materialDanjia,
                        count : item.count,
                        zaojia : item.zaojia
                    };
                    var result = useTemplateId("material_tr_template",context);
                    $table_item.find("tbody").append(result);
                });
                $("#chudan_container").append($table_item);
            });
            ReComputeStep2();
            //后续补充功能
            $("#chudan_container").find(".material_table_item").draggable();
            $("#chudan_container").find(".material_table_item").droppable({
                drop: function() {
                    alert( "dropped" );
                }
            });
        },null,{
            contentType:"application/json; charset=utf-8"
        });
    });

    //第2步页面---------------------------------------------------------

    $('#danjia_showmodel').on('change',function(){
        var danjia_showmodel = $('#danjia_showmodel').prop("checked");
        if(danjia_showmodel){
            $(".chudan_tb").find("thead").find("input.danjia_showmodel").prop("checked",true);
        }else{
            $(".chudan_tb").find("thead").find("input.danjia_showmodel").prop("checked",false);
        }
    });
    $('#count_showmodel').on('change',function(){
        var count_showmodel = $('#count_showmodel').prop("checked");
        if(count_showmodel){
            $(".chudan_tb").find("thead").find("input.count_showmodel").prop("checked",true);
        }else{
            $(".chudan_tb").find("thead").find("input.count_showmodel").prop("checked",false);
        }
    });
    $('#heji_showmodel').on('change',function(){
        var danjia_showmodel = $('#heji_showmodel').prop("checked");
        if(danjia_showmodel){
            $(".chudan_tb").find("thead").find("input.heji_showmodel").prop("checked",true);
        }else{
            $(".chudan_tb").find("thead").find("input.heji_showmodel").prop("checked",false);
        }
    });
    //第1步重新计算tfoot
    var ReComputeStep2 = function(){
        $.each($("#chudan_container").find('table.chudan_tb'),function(index,item){
            var $table = $(item);
            var count = 0;//总个数
            var zaojia = 0;
            //特殊计算
            $table.find("tbody tr").each(function(index,item){
                var $td = $("td:eq(3)",this);
                var data = $td.find('.data-container').html();
                count += parseInt(data);
            });
            $table.find("tbody tr").each(function(index,item){
                var $td = $("td:eq(4)",this);
                var data = $td.data('data');
                zaojia += parseFloat(data);
            });
            $table.find("tfoot").find('.count-container').html(count);
            $table.find("tfoot").find('.zongjia-container').html(zaojia.toFixed(2));
        });


    };
    // step2->step3
    $('#myWizard').on('actionclicked.fu.wizard', function (evt, data) {
        var step = data.step;
        var direction = data.direction;
        if(!(
            (2== step)&&
            (direction=='next')
            )){//非第二部页面
            return ;
        }
        var model  = $("#chudan_model").val();
        var myUrl = '';
        switch(model){
            case '0':
                myUrl = urls.chudan.jianhuodan_result;
                break;
            case '1'://分供货商打单
                myUrl = urls.chudan.jinhuodan_manufacturer;
                break;
            default :
                break;
        };
        $("#print_container").html('');
        $.each($("#chudan_container").find('table.chudan_tb'),function(index,item){
            var $chudan_table = $(item);//第二页的出单表
            var manufacturerName = $chudan_table.data("manufacturername");
            var print_item = useTemplateId("print_item_template",{
                manufacturerName:manufacturerName
            });
            var $print_item = $(print_item);
            var $print_table = $print_item.find("table.print_tb");
            $chudan_table.find("tbody").find('tr').each(function(index,item){
                var $tr = $(this);
                var materialId  = $tr.find('td:eq(0)'),
                    name = $tr.find('td:eq(1)').data('data'),
                    materialdDanjia = $tr.find('td:eq(2)').data('data'),
                    count = $tr.find('td:eq(3)').data('data'),
                    zaojia = $tr.find('td:eq(4)').data('data');
                var identifiedIndex = index+1;
                var context = {
                    index:identifiedIndex,
                    materialId:materialId,
                    materialName:name,
                    materialdDanjia:materialdDanjia,
                    count:count,
                    zaojia:zaojia
                };
                var result = useTemplateId("print_tr_template",context);
                $print_table.find("tbody").append(result);
            });
            $("#print_container").append($print_item);
            //判断是否隐藏
            //单价
            var $if_show_danjia = $chudan_table.find("thead tr").find('th:eq(2)').find('input.if_show');
            if($if_show_danjia){
                var checked = $if_show_danjia.prop('checked');
                if(checked){//隐藏
                    $print_table.find("thead tr").find('th.materialDanjia').hide();
                    $print_table.find("tbody tr").find('td.materialDanjia').hide();
                    $print_table.find("tfoot tr").find('th.materialDanjia').hide();
                }else{
                    $print_table.find("thead tr").find('th.materialDanjia').show();
                    $print_table.find("tbody tr").find('td.materialDanjia').show();
                    $print_table.find("tfoot tr").find('th.materialDanjia').show();
                }
            }
            //数量
            var $if_show_shuliang = $chudan_table.find("thead tr").find('th:eq(3)').find('input.if_show');
            if($if_show_shuliang){
                var checked = $if_show_shuliang.prop('checked');
                if(checked){//隐藏
                    $print_table.find("thead tr").find('th.materialCount').hide();
                    $print_table.find("tbody tr").find('td.materialCount').hide();
                    $print_table.find("tfoot tr").find('th.materialCount').hide();
                }else{
                    $print_table.find("thead tr").find('th.materialCount').show();
                    $print_table.find("tbody tr").find('td.materialCount').show();
                    $print_table.find("tfoot tr").find('th.materialCount').show();
                }
            }
            //合计金额
            var $if_show_zonjia =  $chudan_table.find("thead tr").find('th:eq(4)').find('input.if_show');
            if($if_show_zonjia){
                var checked = $if_show_zonjia.prop('checked');
                if(checked){//隐藏
                    $print_table.find("thead tr").find('th.materialZaojia').hide();
                    $print_table.find("tbody tr").find('td.materialZaojia').hide();
                    $print_table.find("tfoot tr").find('th.materialZaojia').hide();
                }else{
                    $print_table.find("thead tr").find('th.materialZaojia').show();
                    $print_table.find("tbody tr").find('td.materialZaojia').show();
                    $print_table.find("tfoot tr").find('th.materialZaojia').show();
                }

            }
        });
        ReComputeStep3();
    });
    //第二步 -删除
    $("#chudan_container").delegate("table.chudan_tb button.item_remove",'click',function(){
        var $tr = $(this).closest('tr');
        var id = $tr.data("id");
        $tr.remove();
        ReComputeStep2();
    });


    //第3步页面---------------------------------------------------------
    var ReComputeStep3 = function(){
        $.each($("#print_container").find('table.print_tb'),function(index,item){
            var count = 0;//总个数
            var zaojia = 0;
            var $print_tb = $(this);
            //特殊计算
            $print_tb.find("tbody tr").each(function(index,item){
                var $td = $("td:eq(4)",this);
                var data = $td.html();
                count += parseInt(data);
            });
            $print_tb.find("tbody tr").each(function(index,item){
                var $td = $("td:eq(6)",this);
                var data = $td.html();
                zaojia += parseFloat(data);
            });
            $print_tb.find("tfoot").find('.count-container').html(count);
            $print_tb.find("tfoot").find('.zongjia-container').html(zaojia.toFixed(2));
        });
    };
    //用户确认出单
    $wizard.on('finished.fu.wizard', function (evt, data) {

        $.each($("#print_container").find(".print_context"),function(index,item){

            //暂时认为是全部打印的
            $(item).jqprint({
                debug:false,
                importCSS: true,
                printContainer: false,
                operaSupport: true
            });
        });
    });
});
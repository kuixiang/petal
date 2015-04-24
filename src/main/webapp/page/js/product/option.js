/**
 * Created by xiangkui on 2015/2/20.
 */
$(document).ready(function(){
    var $table = $('#materialItems_tb');
    var productId = $("#identifier").val();
    //产品表单
    var table = $table.dataTable( {
        "ajax":{
            url: urls.product.listMaterial +"?productId="+productId,
            contentType: "application/json; charset=utf-8"
        },
        serverSide: false,
//        "bDestroy" : true,
//        "processing": true,
//        "order": [[ 1, "desc" ]],
        "paging":   false,
        "ordering": false,
        "info":     false,
        "columns": [
            { "data": "id" ,"className":"center","width":"58px",
                "render": function ( data, type, full, meta ) {
                    var id = data;
                    return '<label><input type="checkbox" class="ace checkbox" data-id="'+id+'"><span class="lbl"></span></label>';
                }},
            { "data": "id","title":"序号" },
            { "data": "materialName","title":"材料名称","visible": false},
            { "data": "materialId","title":"材料名称",
                "render": function( data, type, row, meta ) {
                    var materialId = data;
                    var materialName = row.materialName;
                    var result = '<a href="'+urls.material.edit+'?id='+materialId+'" target="_blank">'+materialName+'</a>';
                    return result;
                }
            },
            { "data": "materialYanse","title":"材料颜色"},
            { "data": "materialDanjia","title":"材料单价"},
            { "data": "count","title":"数量",
                "render": function( data, type, row, meta ) {
                    if ( type === 'display' ) {
                        var result = '<span class="label label-sm label-success data-container"'+
                            ' data-id='+row.id+
                            ' data-materialId='+row.materialId+
                            ' data-count='+row.count+
                            ' data-beizhu='+row.beizhu+
                            ">"+
                            data+
                            '</span>';
                        return result;
                    }
                    return data;
                }
            },
            { "data": null,"title":"造价" ,
                "render": function( data, type, row, meta ) {
                    var danjia = parseFloat(row.materialDanjia);
                    var count = parseInt(row.count);
                    var zaojia = parseFloat(count*danjia).toFixed(2);
                    return zaojia;
                }
            },
            { "data": "id","title":"操作" ,"width":"160px",className: "center","sClass": "center",
                "render": function ( data, type, row, meta ) {
                    var id = data;
                    return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">' +
//                        '<a class="blue" title="查看" href='+urls.material.edit+"?id="+id+'>' +
//                        '<i class="icon-zoom-in bigger-130"></i>' +
//                        '</a>' +
//                        '<a class="green edit editor_edit" title="编辑" data-id="'+id+'" href="javascript:void(0);" >' +
//                        '<i class="icon-pencil bigger-130"></i>' +
//                        '</a>' +
                        '<a class="red delete editor_remove" title="删除" data-id="'+id+'" href="javascript:void(0);">' +
                        '<i class="icon-trash bigger-130"></i>' +
                        '</a>' +
                        '</div>';
                }
            }
        ],
//        "dom": '<T<"clear"><"row"lf><t><"row"ip>>',
        "dom": '<T<"clear"><"row"l><t><"row"ip>>',
        "tableTools": {
            "sSwfPath": "/lib/plugins/datatables/js/copy_csv_xls.swf",
            "aButtons" : [{
                    sExtends: "collection",
                    sButtonText: "保存",
                    sButtonClass: "save-collection ",
                    aButtons: [ 'csv', 'xls', 'pdf','copy']
                }
            ],

            sRowSelect: "os",
            sRowSelector: 'td:first-child'
        },
        searchable:true
    } );
    //材料选择-弹出操作框
    $("#materials_select").select2({
//        theme: "classic",
        placeholder: "请选择材料项",
        maximumSelectionLength: 1
    });
    $("#item_ok_btn").on('click',function(){
        var data = getFormJson("#materialItem_form");
        post(urls.product.addMaterial,JSON.stringify(data),function(result){
            $("#item_close_btn").trigger('click');
            table.fnDraw();
            window.location.reload();
        },null,{
            contentType:"application/json; charset=utf-8"
        });
    });
    $("#item_close_btn").on('click',function(){
        $("#materialItem_form").modal('hide');
    });

    //材料项操作
    $table.on( 'click', 'tbody td:not(:first-child)', function (e) {
        var $self = $(this);
        var index = $(this).context.cellIndex;
        if(5!=index) return;
        var $element = $("<input type='text'/>");
        var $origin_element = $($(this).html());
        $(this).html($element);
        $element.trigger('focus');
        $element.keydown(function(e){
            if(e.keyCode==13){
                $(this).trigger('blur');
            }
        });
        $element.on('blur',function(){
            var value = $element.val();
            var $dataNode = $origin_element;
            //更新材料项
            var id = $dataNode.data('id');
            var productId = $("#identifier").val();
            var materialId = $dataNode.data('materialid');
            var count = value;//更新项
            var beizhu = $dataNode.data('beizhu');
            post(urls.product.updateMaterial,JSON.stringify({
                id:id,
                productId:productId,
                materialId:materialId,
                count:count,
                beizhu:beizhu
            }),function(result){
                $origin_element.find('.data-container').html(value);
                $self.html($origin_element);
                table.fnDraw();
                window.location.reload();
            },null,{
                contentType:"application/json; charset=utf-8"
            });
        });
    } );
    // Delete a record
    $table.on('click', 'a.editor_remove', function (e) {
        e.preventDefault();
        var relationId = $(this).data("id");

        var title = "请确认";
        var message  =  "确定要删除此这个材料项么?";
        var rtMessge = "操作成功!";
        $.messager.confirm(title, message, function() {
            post(urls.product.deleteMaterial,{
                id:relationId
            },function(result){
                $.messager.popup(rtMessge);
                table.fnDraw();
                window.location.reload();
            });
        });
    } );
    //保存 材料
    $("#save_btn").on('click',function(){
        var data = getFormJson("#product_form");
        post(urls.product.save,JSON.stringify(data),function(result){
            var product = result.data;
            if(product){
                $("#identifier").val(product.id);
            }
            $.messager.popup("成功");
        },null,{
            contentType:"application/json; charset=utf-8"
        });
        return false;
    });
});
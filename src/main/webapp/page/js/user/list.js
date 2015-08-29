/**
 * Created by xiangkui on 2015/2/1.
 */
$(document).ready(function(){
    var $table = $("#list_tb");
    var table = $table.dataTable( {
        "ajax":{
            url: urls.user.list,
            contentType: "application/json; charset=utf-8"
        },
        serverSide: true,
//        "processing": true,
//        "order": [[ 1, "desc" ]],
        "ordering": false,
        "columns": [
            { "data": "id" ,"className":"center","width":"58px",
                "render": function ( data, type, full, meta ) {
                    var id = data;
                    return '<label><input type="checkbox" class="ace checkbox" data-id="'+id+'"><span class="lbl"></span></label>';
                }},
            { "data": "id","title":"序号" },
            { "data": "name","title":"名称"},
            { "data": "phone","title":"电话"},
            { "data": "email","title":"邮箱"},
            { "data": "address","title":"联系地址"},
            { "data": "beizhu","title":"备注"},
            { "data": "id","title":"操作" ,"width":"184px",
                "render": function ( data, type, full, meta ) {
                    var id = data;
                    return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">' +
//                        '<a class="blue" title="查看" href='+urls.material.edit+"?id="+id+'>' +
//                        '<i class="icon-zoom-in bigger-130"></i>' +
//                        '</a>' +
                        '<a class="green edit" title="编辑" data-id="'+id+'" href="javascript:void(0);" >' +
                        '<i class="icon-pencil bigger-130"></i>' +
                        '</a>' +
                        '<a class="red delete" title="删除" data-id="'+id+'" href="javascript:void(0);">' +
                        '<i class="icon-trash bigger-130"></i>' +
                        '</a>' +
                        '</div>';
                }
            }
        ],
        "pagingType": "simple_numbers",
        "lengthMenu": [[10, 25, 50, 99999999999999], [10, 25, 50, "All"]],
        "language": {
            "lengthMenu": "每页展示 _MENU_ 条记录",
            "info": "第 _PAGE_/_PAGES_ 页",
            "search":"搜索",
            "infoEmpty": "没有结果",
            "zeroRecords" : "没有检索到数据",
            "infoFiltered" : "(从 _MAX_ 条数据中检索)",
            "paginate": {
                "previous": "<<",
                "next": ">>"
            }
        },
        "dom": '<T<"clear"><"row"lf><t><"row"ip>>',
        "tableTools": {
            "sSwfPath": "/lib/plugins/datatables/js/copy_csv_xls.swf",
            "aButtons" : [ {
                "sExtends" : "xls",
                "sButtonText" : "导出excel"
            } ]
        },
        searchable:true
    } );
    $("#add_btn").on('click',function(){
        window.location = urls.user.add;
    });

    $("#delete_btn").on('click',function(){
       var ids = [];
        $table.find("tbody").find("input:checkbox").each(function(index){
           var checked = $(this).prop("checked");
           if(checked){
               var id = $(this).data("id");
               ids.push(id);
           }
       });
        if(ids.length==0){//没有选中
            $.messager.popup("没有选中，请确认");
            return true;
        }
        _delete(ids);

    });
    $table.find("tbody").delegate(".edit", 'click', function () {
        var id = $(this).data("id");
        window.location = urls.user.edit+"?id="+id;
    } );
    $table.find("tbody").delegate(".delete", 'click', function () {
        var id = $(this).data("id");
        var ids = [];
        ids.push(id);
        _delete(ids);
    } );
    var _delete = function(ids){
        var title = "请确认";
        var message  =  "确认要删除吗？";
        var result = "操作成功!";
        $.messager.confirm(title, message, function() {
            post(urls.user.delete,{ids:ids},function(result){
                $.messager.popup(result._msg);
                table.fnDraw();
            });
        });
    }
});
/**
 * Created by xiangkui on 2015/2/20.
 */
$(document).ready(function(){
    //材料列表
    var table = $('#material_tb').dataTable( {
        "ajax":{
            url: urls.material.list,
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
            { "data": "guige","title":"规格" },
            { "data": "yanse","title":"颜色" },
            { "data": "danwei","title":"单位" ,
                "render":function(data){
                    switch (data){
                        case "GE":
                            return "个";
                            break;
                        case "JIAN":
                            return "件";
                            break;
                        case "TAO":
                            return "套";
                            break;
                        case "ZU":
                            return "组";
                            break;
                        default :
                            break;
                    }
                }},
            { "data": "jinjia" ,"title":"进价"},
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
        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
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
            "aButtons" : [
                {"sExtends" : "xls","sButtonText" : "导出excel"}
            ],
            sRowSelect: "os"
        },
        searchable:true
    } );

    $("#manufacturers_select").select2({
        language: "zh-ch",
        theme: "classic",
        placeholder: "请选择",
//        tags: true,
        tokenSeparators: [',', ' ']
    });
    //保存 材料
    $("#save_btn").on('click',function(){
        var data = getFormJson("#material_form");
        post(urls.material.save,data,function(result){
            var material = result.data;
            if(material){
                var id = material.id;
                $("#identifier").val(id);
            }
            $.messager.popup("成功");
        });
        return false;
    });
});
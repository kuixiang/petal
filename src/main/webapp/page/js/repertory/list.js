
/**
 * Created by xiangkui on 2015/2/1.
 */
$(document).ready(function(){
    var $table = $("#list_tb");
    var options = {
        url:       urls.repertory.list,
        type:      'post',
        dataType:  'json',
        contentType: "application/json; charset=utf-8",
        beforeSubmit:  showRequest,
        success:       showResponse,
        target:        '#output2'
    };
    function showRequest(formData, jqForm, options) {
        return true;
    }
    function showResponse(responseText, statusText, xhr, $form)  {
        var list = responseText.data;
    }
    $('#filtersForm').submit(function() {
        $(this).ajaxSubmit(options);
        return false;
    });

    $("#add_btn").on('click',function(){
        window.location = urls.userorder.add;
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
        window.location = urls.userorder.edit+"?id="+id;
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
            post(urls.userorder.delete,{ids:ids},function(result){
                $.messager.popup(result._msg);
                table.fnDraw();
            });
        });
    }
});
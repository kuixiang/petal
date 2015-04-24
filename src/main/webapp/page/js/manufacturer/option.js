/**
 * Created by xiangkui on 2015/2/20.
 */
$(document).ready(function(){
    $("#materials_select").select2({
        language: "zh-ch",
        theme: "classic",
        placeholder: "请选择",
//        tags: true,
        tokenSeparators: [',', ' ']
    });
    //保存 材料
    $("#save_btn").on('click',function(){
        var data = getFormJson("#manufacture_form");
        post(urls.manufacturer.save,data,function(result){
            $.messager.popup("保存成功");
            var manufacturer = result.data;
            if(manufacturer){
                var id = manufacturer.id;
                $("#identifier").val(id);
            }
//            window.location = urls.manufacturer.index;
        });
        return false;
    });
});
/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

/**
 * Created by xiangkui on 2015/2/20.
 */
$(document).ready(function(){
    //保存 材料
    $("#save_btn").on('click',function(){
        var data = getFormJson("#info_form");
        post(urls.user.save,data,function(result){
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
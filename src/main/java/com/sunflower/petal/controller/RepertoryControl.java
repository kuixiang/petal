/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunflower.petal.entity.DataTableRequest;
import com.sunflower.petal.entity.DataTableResponse;
import com.sunflower.petal.entity.RepertoryItem;
import com.sunflower.petal.service.RepertoryService;
import com.sunflower.petal.utils.AjaxUtil;
import com.sunflower.petal.utils.CommonUtil;

import net.sf.json.JSONObject;

/**
 * Created by xiangkui on 14-2-15.
 * 维护库存信息（材料，产品等）
 */
@Controller
@RequestMapping("/repertory")
public class RepertoryControl {
    @Autowired
    private RepertoryService repertoryService;
    //查询
    @RequestMapping(value= "/list.ajax")
    public @ResponseBody JSONObject list(DataTableRequest request,Model model){
        DataTableResponse response = repertoryService.getDataTableList(request);
        return AjaxUtil.dataTableJson(response);
    }
    //编辑/增加
    @RequestMapping(value = {"/edit.html","/add.html"})
    public String editOrAdd(Long id,Model model){
       if(CommonUtil.IsNotNull(id)){//edit
            RepertoryItem repertoryItem = repertoryService.get(id);
            model.addAttribute("item",repertoryItem);
        } else {//add
            ;
        }
        return "/material/option";
    }
    //删除
    @RequestMapping("/delete.ajax")
    public @ResponseBody JSONObject delete(@RequestParam("ids[]") Long[] ids){
        repertoryService.delete(ids);
        return AjaxUtil.success("操作成功");
    }
    //保存
    @RequestMapping("/save.ajax")
    public @ResponseBody JSONObject save(RepertoryItem repertoryItem){
        repertoryService.save(repertoryItem);
        return AjaxUtil.success("成功",repertoryItem);
    }



    /*
     *  手动更新了仓库库存信息,用于批量添加了一批材料后，需要同步库存量信息
     *  新增加了材料项，如果没有库存信息，则认为库存量为0
     *  如果删除了材料项，则库存记录项清理
     * */
    @RequestMapping("/sync.ajax")
    public @ResponseBody JSONObject sync(){
        repertoryService.sync();
        return AjaxUtil.success("同步中...");
    }
}
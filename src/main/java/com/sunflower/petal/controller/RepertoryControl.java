/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunflower.petal.entity.DataTableRequest;
import com.sunflower.petal.entity.DataTableResponse;
import com.sunflower.petal.entity.RepertoryDeta;
import com.sunflower.petal.entity.RepertoryItem;
import com.sunflower.petal.entity.RepertoryType;
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
    /** 出库、入库情况*************************************************************************************/
    @RequestMapping("/index_dan.html")
    public String index_dan(){
        return "/repertory/list_dan";
    }
    /** 展示入库、出库单 **/
    @RequestMapping(value = "/list_dan.ajax")
    public @ResponseBody JSONObject list_dan(DataTableRequest request,Model model) {
        DataTableResponse response = repertoryService.getDataTableListDan(request);
        return AjaxUtil.dataTableJson(response);
    }
    @RequestMapping("/repertory/chudan/{type}/chudan.html")
    public String chudan(@PathVariable("type") String type,Model model) {
        model.addAttribute("type",type);
        if ("input".equals(type)){//入库单

        } else if ("output".equals(type)) { //出库单

        }
        return "/repertory/chudan";
    }
    @RequestMapping(value = "/repertory/save_dan.ajax")
    public @ResponseBody JSONObject save_dan(@RequestBody RepertoryDeta repertoryDeta) {
        repertoryService.add_dan(repertoryDeta);
        return AjaxUtil.success("成功",repertoryDeta);
    }
    /** 库存情况*******************************************************************************************/
    //首页
    @RequestMapping("/index.html")
    public String index(){
        return "/repertory/list";
    }
    //查询
    @RequestMapping(value= "/list.ajax")
    public @ResponseBody JSONObject list(
            @RequestParam(required=true,value = "type")RepertoryType type
            ,@RequestParam(required=true,value = "start") Integer start
            ,@RequestParam(required=true,value = "length") Integer length
            ,@RequestParam(required=false,value = "goodsId")Long goodsId //like
            ,@RequestParam(required=false,value = "name",defaultValue = "")String name //like
            // >=~'count'
            // <='count~'
            // count 数值覆盖 (minCount 和 maxCount)
            ,@RequestParam(required=false,value = "count") String count
            ,@RequestParam(required=false,value = "minCount",defaultValue = ""+Integer.MIN_VALUE) Integer minCount
            ,@RequestParam(required=false,value = "maxCount",defaultValue = ""+Integer.MAX_VALUE) Integer maxCount
            ,@RequestParam(required=false,value = "others")List<String> others // common sql
            ,Model model){
        //如果有count数值，则计算count
        final String COUNT_SPLIT = "~";

        List<String> filters = new ArrayList<String>();
        if(CommonUtil.IsNotNull(goodsId)){
            filters.add("goodsId like %"+goodsId+"%");
        }
        if(StringUtils.isNotEmpty(name)){
            filters.add("name like %"+name+"%");
        }
        if(CommonUtil.IsNotNull(count)){
            String[] splits = count.split(COUNT_SPLIT);
            minCount = Integer.valueOf(splits[0]);
            maxCount = Integer.valueOf(splits[1]);
        }
        filters.add("count >= "+minCount);
        filters.add("count <= "+maxCount);
        if(CommonUtil.IsNotNull(others)){
            filters.addAll(others);
        }
        List<RepertoryItem> dataTableList = repertoryService.getDataTableList(type, start, length, filters);
        return AjaxUtil.dataTableJson(dataTableList);
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
     *  手动更新了仓库库存信息,用于批量添加了一批材料或产品信息后，需要同步库存量信息
     *  新增加了材料项，如果没有库存信息，则认为库存量为0
     *  如果删除了材料项，则库存记录项清理
     * */
    @RequestMapping("/sync.ajax")
    public @ResponseBody JSONObject sync(){
        repertoryService.sync();
        return AjaxUtil.success("同步中...");
    }


}
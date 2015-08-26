/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunflower.petal.entity.DataTableRequest;
import com.sunflower.petal.entity.DataTableResponse;
import com.sunflower.petal.entity.UserOrder;
import com.sunflower.petal.service.UserOrderService;
import com.sunflower.petal.utils.AjaxUtil;
import com.sunflower.petal.utils.CommonUtil;

import net.sf.json.JSONObject;

/**
 * Created by xiangkui on 2015/8/25.
 *  * 1： 用户订单
 */
@RequestMapping("/userOder")
public class UserOrderController {
    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private UserOrderService userOrderService;
    //查询
    @RequestMapping(value= "/list.ajax")
    public @ResponseBody
    JSONObject list(DataTableRequest request,Model model){
        DataTableResponse response = userOrderService.getDataTableList(request);
        return AjaxUtil.dataTableJson(response);
    }
    //编辑/增加
    @RequestMapping(value = {"/edit.html","/add.html"})
    public String editOrAdd(Long id,Model model){
        if(CommonUtil.IsNotNull(id)){
            UserOrder userOrder = userOrderService.get(id);
            model.addAttribute("userOrder",userOrder);
        }else{

        }
        return "/userOder/option";
    }
    //保存
    @RequestMapping("/save.ajax")
    public @ResponseBody JSONObject save(@RequestBody UserOrder userOrder){
        userOrderService.save(userOrder);
        return AjaxUtil.success("成功",userOrder);
    }
    //废弃用户订单
    @RequestMapping("/discard.ajax")
    public @ResponseBody JSONObject discard(@RequestParam("ids[]") Long[] ids){
        userOrderService.discard(ids);
        return AjaxUtil.success("操作成功");
    }
}

/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunflower.petal.entity.DataTableRequest;
import com.sunflower.petal.entity.DataTableResponse;
import com.sunflower.petal.entity.Product;
import com.sunflower.petal.entity.User;
import com.sunflower.petal.entity.UserOrder;
import com.sunflower.petal.entity.UserOrderState;
import com.sunflower.petal.service.ProductService;
import com.sunflower.petal.service.UserOrderService;
import com.sunflower.petal.service.UserService;
import com.sunflower.petal.utils.AjaxUtil;
import com.sunflower.petal.utils.CommonUtil;

import net.sf.json.JSONObject;

/**
 * Created by xiangkui on 2015/8/25.
 *  * 1： 用户订单
 */
@Controller
@RequestMapping("/userorder")
public class UserOrderController {
    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService usersService;

    /**
     * 用户列表 首页
     *
     * @return
     */
    @RequestMapping({"index.html"})
    public String index(){
        return "/userorder/list";
    }

    //查询
    @RequestMapping(value= "/list.ajax")
    public @ResponseBody JSONObject list(DataTableRequest request,Model model){
        DataTableResponse response = userOrderService.getDataTableList(request);
        return AjaxUtil.dataTableJson(response);
    }
    //编辑/增加
    @RequestMapping(value = {"/edit.html","/add.html"})
    public String editOrAdd(Long id,Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products",products);
        List<User> users = usersService.getAll();
        model.addAttribute("users",users);
        model.addAttribute("userOrderStates", UserOrderState.values());
        if(CommonUtil.IsNotNull(id)){//编辑模式
            UserOrder userOrder = userOrderService.get(id);
            model.addAttribute("userOrder",userOrder);
            Long userId = userOrder.getUserId();
            for (User user : users) {
                if(user.getId().equals(userId))
                    user.setSelected(true);
            }
        }else{

        }
        return "/userorder/option";
    }
    //保存
    @RequestMapping("/save.ajax")
    public @ResponseBody JSONObject save(@RequestBody UserOrder userOrder){
        if(CommonUtil.IsNull(userOrder.getOrderTime())){
            userOrder.setOrderTime(new Date());
        }
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

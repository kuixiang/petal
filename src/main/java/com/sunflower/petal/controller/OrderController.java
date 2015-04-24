package com.sunflower.petal.controller;

import com.sunflower.petal.entity.*;
import com.sunflower.petal.entity.order.MaterialOrder;
import com.sunflower.petal.entity.order.MaterialOrderItem;
import com.sunflower.petal.service.OrderService;
import com.sunflower.petal.utils.AjaxUtil;
import com.sunflower.petal.utils.CommonUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xiangkui on 2015/4/16.
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {

    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    //查询
    @RequestMapping(value= "/list.ajax")
    public @ResponseBody JSONObject list(DataTableRequest request,Model model){
        DataTableResponse response = orderService.getDataTableList(request);
        return AjaxUtil.dataTableJson(response);
    }
    //编辑/增加
    @RequestMapping(value = {"/edit.html","/add.html"})
    public String editOrAdd(Long id,Model model){
//        List<MaterialOrder> orders = orderService.getAll();
//        model.addAttribute("orders",orders);
        return "/order/option";
    }
    //删除
    @RequestMapping("/delete.ajax")
    public @ResponseBody JSONObject delete(@RequestParam("ids[]") Long[] ids){
        orderService.delete(ids);
        return AjaxUtil.success("操作成功");
    }
    //保存
    @RequestMapping("/save.ajax")
    public @ResponseBody JSONObject save(@RequestBody MaterialOrder order){
        orderService.save(order);
        Long id = order.getId();
        return AjaxUtil.success("成功",order);
    }
    @RequestMapping("/MaterialOrderOrder.ajax")
    public @ResponseBody
    JSONObject MaterialOrderOrder(@RequestBody List<MaterialOrderItem> items,User user){
        try{
            orderService.materialChudan(user,items);
            return AjaxUtil.success("出单成功");
        } catch (Exception e) {
            logger.error("出单失败");
            return AjaxUtil.failure("出单失败",e);
        }
    }
}

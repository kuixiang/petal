/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunflower.petal.entity.DataTableRequest;
import com.sunflower.petal.entity.DataTableResponse;
import com.sunflower.petal.entity.User;
import com.sunflower.petal.service.UserService;
import com.sunflower.petal.utils.AjaxUtil;
import com.sunflower.petal.utils.CommonUtil;

import net.sf.json.JSONObject;

/**
 * Created by xiangkui on 2015/8/27.
 * 用户管理相关
 * 管理员用户维护系统用户基本信息
 * 用户包括 普通用户 供货商 系统管理员等信息
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private Logger logger  = LoggerFactory.getLogger(UserController.class);

    //查询
    @RequestMapping(value= "/list.ajax")
    public @ResponseBody
    JSONObject list(DataTableRequest request,Model model){
        DataTableResponse response = userService.getDataTableList(request);
        return AjaxUtil.dataTableJson(response);
    }
    //编辑/增加
    @RequestMapping(value = {"/edit.html","/add.html"})
    public String editOrAdd(Long id,Model model){
        if(CommonUtil.IsNotNull(id)){//edit
            User user=userService.get(id);
            model.addAttribute("user",user);
        } else {//add
            ;
        }
        return "/user/option";
    }
    //禁用
    @RequestMapping("/disable.ajax")
    public @ResponseBody JSONObject disable(@RequestParam("ids[]") Long[] ids){
        userService.disable(ids);
        return AjaxUtil.success("操作成功");
    }

    /**
     * 此接口受限使用
     * @param ids
     * @return
     */
    @RequestMapping("/delete.ajax")
    public @ResponseBody JSONObject delete(@RequestParam("ids[]") Long[] ids){
        userService.delete(ids);
        return AjaxUtil.success("操作成功");
    }

    //保存
    @RequestMapping("/save.ajax")
    public @ResponseBody JSONObject save(User user){
        try{
            Long uid = user.getId();
            if(CommonUtil.IsNotNull(uid)){
                userService.update(user);
            }else{
                boolean isOk = userService.add(user);
                if(!isOk)
                    throw new RuntimeException("添加用户失败");
            }
            return AjaxUtil.success("成功",user);
        }catch (Exception e){
            logger.error("保存用户失败",e);
            return AjaxUtil.failure("保存失败",e);
        }
    }
}

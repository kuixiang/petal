package com.sunflower.petal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiangkui on 2014/6/22.
 * 设计控制器
 */
@Controller
@RequestMapping("/design")
public class DesignerCtrl {

    @RequestMapping(value = "/form-wizard.html")
    public String calendar(Model model,HttpServletRequest request) {
        return "/design/form-wizard";
    }


}

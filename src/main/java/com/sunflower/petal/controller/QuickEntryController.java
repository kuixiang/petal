/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sunflower.petal.entity.Material;
import com.sunflower.petal.entity.Product;
import com.sunflower.petal.service.FileUpLoadService;
import com.sunflower.petal.service.ManufacturerService;
import com.sunflower.petal.service.MaterialService;
import com.sunflower.petal.service.ProductMaterialService;
import com.sunflower.petal.service.ProductService;
import com.sunflower.petal.utils.CommonUtil;

/**
 * Created by xiangkui on 2015/8/18.
 * 整合产品，材料，供货商等信息快速录入功能
 */
@Controller
@RequestMapping("/quickEntry")
public class QuickEntryController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMaterialService productMaterialService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private FileUpLoadService fileUpLoadService;

    @RequestMapping(value = "/newProduct.html")
    public String newProductPage(Long id,Model model){
        List<Material> materials = materialService.getAll();
        model.addAttribute("materials",materials);
        if(CommonUtil.IsNotNull(id)){//edit
            Product product=productService.getProduct(id);
            model.addAttribute("product",product);
        } else {//add
            ;
        }
        return "/quickentry/option";
    }

}

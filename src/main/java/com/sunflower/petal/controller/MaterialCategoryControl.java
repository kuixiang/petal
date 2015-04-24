package com.sunflower.petal.controller;

import com.sunflower.petal.entity.MaterialCategory;
import com.sunflower.petal.service.MaterialCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by xiangkui on 14-2-19.
 */
@Controller
@RequestMapping(value = "/materialCategory")
public class MaterialCategoryControl {
    @Autowired
    private MaterialCategoryService service;

    @RequestMapping(value = "/list")
    public String listCategoru(Model model){
        List<MaterialCategory> list= service.getAllMaterialCategorys();
        model.addAttribute("categorys",list);
        return "material/categoryPanel";
    }

    @RequestMapping(value = "/delete")
    public int deleteCategory(@RequestParam("id") Long id){
        return service.deleteMaterialCategory(id);
    }

    @RequestMapping(value = "/add")
    public void addCategory(@RequestParam("category") MaterialCategory category){
        service.saveMaterialCategory(category);
    }


}

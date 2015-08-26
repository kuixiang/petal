package com.sunflower.petal.controller;

import com.sunflower.petal.entity.MaterialCategory;
import com.sunflower.petal.service.MaterialCategoryService;
import com.sunflower.petal.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by xiangkui on 14-2-19.
 */
@Controller
@RequestMapping("")
public class IndexControl {
    @Autowired
    private MaterialService materialService;
    @Autowired
    private MaterialCategoryService categoryService;

    //index
    @RequestMapping(value={"/index","/"},method = RequestMethod.GET)
    public String index(){
        return "index";
    }
    //基础信息
    @RequestMapping(value = "/basicInfo/{menuItem}",method = RequestMethod.GET)
    public String basicInfoItems(@PathVariable("menuItem") String menuItem,Model mode){
        String redirect="";
        if(menuItem.equals("material")){
            redirect="material/list";
        }else if(menuItem.equals("product")){
            redirect="product/list";
        }else if(menuItem.equals("user")){
            redirect="user/list";
        }else if(menuItem.equals("manufacturer")) {
            redirect = "manufacturer/list";
        }else if(menuItem.equals("quickentry")){
            redirect = "quickEntry/newProduct";
        }else{//unkwon page
            redirect="";
        }
        return redirect;
    }
    //预备生产
    @RequestMapping(value = "/production/{menuItem}")
    public String productItems(@PathVariable("menuItem") String menuItem){
        String redirect="";
        if(menuItem.equals("assembly")){
            redirect="product/assembly";
        }else{//unkwon pages

        }
        return redirect;
    }

    //预算
    @RequestMapping(value = "/budget/{menuItem}")
    public String budgetItems(@PathVariable("menuItem") String menuItem){
        String redirect="";
        if(menuItem.equals("assembly")){
        }else{//unkwon pages

        }
        return redirect;
    }

}

package com.sunflower.petal.controller;

import com.sunflower.petal.entity.*;
import com.sunflower.petal.entity.ext.AssemblyItem;
import com.sunflower.petal.service.MaterialService;
import com.sunflower.petal.service.ProductMaterialService;
import com.sunflower.petal.service.ProductService;
import com.sunflower.petal.utils.AjaxUtil;
import com.sunflower.petal.utils.CommonUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xiangkui on 14-2-15.
 */
@Controller
@RequestMapping("/product")
public class ProductControl {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMaterialService productMaterialService;

    @Autowired
    private MaterialService materialService;

    @RequestMapping(value= "/get.ajax")
    public @ResponseBody JSONObject get(Long productId,Model model){
        Product product = productService.getProduct(productId);
        return AjaxUtil.success("成功",product);
    }
    //查询
    @RequestMapping(value= "/list.ajax")
    public @ResponseBody JSONObject list(DataTableRequest request,Model model){
        DataTableResponse response = productService.getDataTableList(request);
        return AjaxUtil.dataTableJson(response);
    }
    @RequestMapping(value= "/listMaterialByProductId.ajax")
    public @ResponseBody JSONObject listMaterialByProductId(Long productId,DataTableRequest request,Model model){
        List<AssemblyItem> assemblyItems = productMaterialService.getAssemblyItemsByProductId(productId);
        return AjaxUtil.dataTableJson(assemblyItems);
    }
    @RequestMapping(value= "/addMaterialItemByProductId.ajax")
    public @ResponseBody JSONObject addMaterialItemByProductId(@RequestBody ProductMaterialRL productMaterialRL){
        productMaterialService.addRL(productMaterialRL);
        return AjaxUtil.success("成功");
    }
    @RequestMapping(value= "/deleteMaterialItemByProductId.ajax")
    public @ResponseBody JSONObject deleteMaterialItemByProductId( @RequestParam Long id){
        productMaterialService.deleteRLById(id);
        return AjaxUtil.success("成功");
    }

    @RequestMapping(value= "/updateMaterialItemByProductId.ajax")
    public @ResponseBody JSONObject updateMaterialItemByProductId(@RequestBody ProductMaterialRL productMaterialRL){
        productMaterialService.updateRL(productMaterialRL);
        return AjaxUtil.success("成功");
    }

    //编辑/增加
    @RequestMapping(value = {"/edit.html","/add.html"})
    public String editOrAdd(Long id,Model model){
        List<Material> materials = materialService.getAll();
        model.addAttribute("materials",materials);
        if(CommonUtil.IsNotNull(id)){//edit
            Product product=productService.getProduct(id);
            model.addAttribute("product",product);
        } else {//add
            ;
        }
        return "/product/option";
    }
    //删除
    @RequestMapping("/delete.ajax")
    public @ResponseBody JSONObject delete(@RequestParam("ids[]") Long[] ids){
        productService.delete(ids);
        return AjaxUtil.success("操作成功");
    }
    //保存
    @RequestMapping("/save.ajax")
    public @ResponseBody JSONObject save(@RequestBody Product product){
        productService.saveProduct(product);
        Long id = product.getId();
        return AjaxUtil.success("成功",product);
    }
}
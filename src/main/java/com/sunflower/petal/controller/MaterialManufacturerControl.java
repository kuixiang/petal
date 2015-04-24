package com.sunflower.petal.controller;

import com.sunflower.petal.entity.*;
import com.sunflower.petal.service.MaterialManufacturerService;
import com.sunflower.petal.service.ProductService;
import com.sunflower.petal.utils.AjaxUtil;
import com.sunflower.petal.utils.CommonUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xiangkui on 14-2-15.
 */
@Controller
@RequestMapping("/material_manufacturer")
public class MaterialManufacturerControl {
//    @Autowired
//    private MaterialManufacturerService service;
//    //查询
//    @RequestMapping(value= "/listByMaterialId.ajax")
//    public @ResponseBody JSONObject listByMaterialId(Long materialId,Model model){
//        List<MaterialManufacturerRL> rlByMaterialId = service.getRLByMaterialId(materialId);
//
//        return null;
//    }
//    //编辑/增加
//    @RequestMapping(value = {"/edit.html","/add.html"})
//    public String editOrAdd(Long id,Model model){
//        if(CommonUtil.IsNotNull(id)){//edit
//            Product product=productService.getProduct(id);
//            model.addAttribute("product",product);
//        } else {//add
//            ;
//        }
//        return "/product/option";
//    }
//    //删除
//    @RequestMapping("/delete.ajax")
//    public @ResponseBody JSONObject delete(@RequestParam("ids[]") Long[] ids){
//        productService.delete(ids);
//        return AjaxUtil.success("操作成功");
//    }
//    //保存
//    @RequestMapping("/save.ajax")
//    public @ResponseBody JSONObject save(Product product){
//        productService.saveProduct(product);
//        Long id = product.getId();
//        return AjaxUtil.success("成功");
//    }
}
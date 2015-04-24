package com.sunflower.petal.controller;

import com.sunflower.petal.entity.Product;
import com.sunflower.petal.entity.compute.ProductItem;
import com.sunflower.petal.entity.list.ZaojiaContext;
import com.sunflower.petal.service.ComputeService;
import com.sunflower.petal.service.ProductService;
import com.sunflower.petal.utils.AjaxUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangkui on 2015/3/3.
 *
 * 出单
 */
@Controller
@RequestMapping("/chudan")
public class ChudanController {
    @Autowired
    private ComputeService computeService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value= "/jinhuodan.html")
    public String jinhuodan(Model model){
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products",allProducts);
        return "chudan/jinhuodan";
    }
    @RequestMapping(value= "/jianhuodan_result.ajax")
    public @ResponseBody JSONObject jianhuodan_result(@RequestBody List<ProductItem> productItems,Model model){
        ZaojiaContext context = computeService.computeM(productItems);
        List<ZaojiaContext> zaojiaContexts = new ArrayList<ZaojiaContext>();
        zaojiaContexts.add(context);
        return AjaxUtil.list2Json(zaojiaContexts);
    }
    /*
    * 分供货商打单
    * */
    @RequestMapping(value= "/jianhuodan_manufacturer.ajax")
    public @ResponseBody JSONObject jianhuodan_manufacturer(@RequestBody List<ProductItem> productItems,Model model){
        ZaojiaContext context = computeService.computeM(productItems);
        List<ZaojiaContext> zaojiaContexts = computeService.getZaojiContextByManufactures(context);
        return AjaxUtil.list2Json(zaojiaContexts);
    }
}

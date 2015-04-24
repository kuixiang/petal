package com.sunflower.petal.controller;

import com.sunflower.petal.entity.Material;
import com.sunflower.petal.service.MaterialCategoryService;
import com.sunflower.petal.service.MaterialService;
import com.sunflower.petal.utils.AjaxUtil;
import com.sunflower.petal.utils.CommonUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by xiangkui on 14-2-15.
 */
@Controller
@RequestMapping("/demo")
public class DemoControl {
    @Autowired
    private MaterialService materialService;
    @Autowired
    private MaterialCategoryService categoryService;
    //查询
    @RequestMapping(value= "/list")
    public String getAllList(Model model){
        List<Material> result=materialService.getAllMaterials();
        model.addAttribute("materials",result);
        return "material/list";
    }
    //编辑/增加
    @RequestMapping(value = {"/edit","/add"})
    public String editOrAdd(Long id,Model model){
        if(CommonUtil.IsNotNull(id)){
            Material material=materialService.getMaterial(id);
            model.addAttribute("material",material);
        }
        return "material/option.vm";
    }
    //删除
    @RequestMapping("/delete")
    public JSONObject delete(Long id){
//        materialService.delete(id);
        return AjaxUtil.success("操作成功");
    }
    //保存
    @RequestMapping("/save")
    public String save(Material material){
        materialService.saveMaterial(material);
        Long id = material.getId();
        return "/material/edit?id="+id;
    }
}
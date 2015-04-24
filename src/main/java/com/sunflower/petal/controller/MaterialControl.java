package com.sunflower.petal.controller;

import com.sunflower.petal.entity.*;
import com.sunflower.petal.service.ManufacturerService;
import com.sunflower.petal.service.MaterialCategoryService;
import com.sunflower.petal.service.MaterialManufacturerService;
import com.sunflower.petal.service.MaterialService;
import com.sunflower.petal.utils.AjaxUtil;
import com.sunflower.petal.utils.CommonUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xiangkui on 14-2-15.
 */
@Controller
@RequestMapping("/material")
public class MaterialControl {
    @Autowired
    private MaterialService materialService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private MaterialManufacturerService materialManufacturerService;
    //查询
    @RequestMapping(value= "/list.ajax")

    public @ResponseBody JSONObject list(DataTableRequest request,Model model){
        DataTableResponse response = materialService.getDataTableList(request);
        return AjaxUtil.dataTableJson(response);
    }
    //编辑/增加
    @RequestMapping(value = {"/edit.html","/add.html"})
    public String editOrAdd(Long id,Model model){
        List<Manufacturer> allManufactures = manufacturerService.getAll();
        model.addAttribute("manufactures",allManufactures);
        if(CommonUtil.IsNotNull(id)){//edit
            Material material=materialService.getMaterial(id);
            model.addAttribute("material",material);
            Long materialId = id;

            List<MaterialManufacturerRL> rlByMaterialId = materialManufacturerService.getRLByMaterialId(materialId);
            List<Long> manufacturerIds = new ArrayList<Long>();
            for (MaterialManufacturerRL relation : rlByMaterialId) {
                Long manufacturerId = relation.getManufacturerId();
                manufacturerIds.add(manufacturerId);
            }
            for (Manufacturer manufacturer : allManufactures) {
                Long manufacturerId = manufacturer.getId();
                if(manufacturerIds.contains(manufacturerId)){
                    manufacturer.setSelected(true);
                }
            }

        } else {//add
            ;
        }

        return "/material/option";
    }
    //删除
    @RequestMapping("/delete.ajax")
    public @ResponseBody JSONObject delete(@RequestParam("ids[]") Long[] ids){
        materialService.delete(ids);
        return AjaxUtil.success("操作成功");
    }
    //保存
    @RequestMapping("/save.ajax")
    public @ResponseBody JSONObject save(Material material){
        materialService.saveMaterial(material);
        Long materialId = material.getId();
        Long[] manufacturerIds = material.getManufacturerIds();
        //更新材料-供货商关系
        materialManufacturerService.buildRLByMaterialId(materialId,manufacturerIds,"");
        return AjaxUtil.success("成功",material);
//        return "/material/edit?id="+id;
    }
}
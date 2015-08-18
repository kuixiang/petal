package com.sunflower.petal.controller;

import com.sunflower.petal.entity.*;
import com.sunflower.petal.service.ManufacturerService;
import com.sunflower.petal.service.MaterialManufacturerService;
import com.sunflower.petal.service.MaterialService;
import com.sunflower.petal.utils.AjaxUtil;
import com.sunflower.petal.utils.CommonUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangkui on 14-2-15.
 */
@Controller
@RequestMapping("/manufacturer")
public class ManufacturerControl {
    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private MaterialManufacturerService materialManufacturerService;
    //查询
    @RequestMapping(value= "/list.ajax")
    public @ResponseBody JSONObject list(DataTableRequest request,Model model){
        DataTableResponse response = manufacturerService.getDataTableList(request);
        return AjaxUtil.dataTableJson(response);
    }
    //编辑/增加
    @RequestMapping(value = {"/edit.html","/add.html"})
    public String editOrAdd(Long id,Model model){
        List<Material> allMaterials = materialService.getAll();
        model.addAttribute("materials",allMaterials);
        if(CommonUtil.IsNotNull(id)){//edit
            Manufacturer manufacturer=manufacturerService.get(id);
            model.addAttribute("manufacturer",manufacturer);
            Long manufacturerId = id;

            List<MaterialManufacturerRL> rlByManufacturerId = materialManufacturerService.getRLByManufacturerId(manufacturerId);
            List<Long> materialIds = new ArrayList<Long>();
            for (MaterialManufacturerRL relation : rlByManufacturerId) {
                Long materialId = relation.getMaterialId();
                materialIds.add(materialId);
            }
            for (Material material : allMaterials) {
                Long materialId = material.getId();
                if(materialIds.contains(materialId)){
                    material.setSelected(true);
                }
            }
        } else {//add
            ;
        }
        return "/manufacturer/option";
    }
    //删除
    @RequestMapping("/delete.ajax")
    public @ResponseBody JSONObject delete(@RequestParam("ids[]") Long[] ids){
        manufacturerService.delete(ids);
        return AjaxUtil.success("操作成功");
    }
    //保存
    @RequestMapping("/save.ajax")
    public @ResponseBody JSONObject save(Manufacturer manufacturer){
        manufacturerService.save(manufacturer);
        Long manufacturerId = manufacturer.getId();
        Long[] materialIds = manufacturer.getMaterialIds();
        //更新材料-供货商关系
        materialManufacturerService.buildRLByManufacturerId(manufacturerId,materialIds,"");

        return AjaxUtil.success("成功",manufacturer);
    }
}
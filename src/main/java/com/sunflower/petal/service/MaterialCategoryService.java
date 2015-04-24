package com.sunflower.petal.service;

import com.sunflower.petal.dao.MaterialCategoryDao;
import com.sunflower.petal.entity.Material;
import com.sunflower.petal.entity.MaterialCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiangkui on 14-2-13.
 */
@Service
public class MaterialCategoryService {
    @Autowired
    private MaterialCategoryDao dao;

    public void saveMaterialCategory(MaterialCategory material){
        dao.add(material);
    }

    public List<MaterialCategory> getAllMaterialCategorys(){
        return dao.listAll();
    }
    public MaterialCategory getMaterialCategory(Long id){
        return dao.queryById(id);
    }
    public int deleteMaterialCategory(Long id){
        return dao.deleteById(id);
    }

    //查询接口
    public MaterialCategory getMaterialByName(String name){
       return dao.findByName(name);
    }

}

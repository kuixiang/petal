package com.sunflower.petal.service;

import com.sunflower.petal.dao.MaterialDao;
import com.sunflower.petal.dao.MaterialManufacturerDao;
import com.sunflower.petal.entity.DataTableRequest;
import com.sunflower.petal.entity.DataTableResponse;
import com.sunflower.petal.entity.Material;
import com.sunflower.petal.entity.MaterialManufacturerRL;
import com.sunflower.petal.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiangkui on 14-2-13.
 */
@Service("materialManufacturerService")
public class MaterialManufacturerService {
    @Autowired
    private MaterialManufacturerDao materialManufacturerDao;


    public void buildRLByMaterialId(Long materialId,Long[] manufacturerIds,String beizhu){
        materialManufacturerDao.deleteBLByMaterialId(materialId);
        if(null!=manufacturerIds && manufacturerIds.length>0)
            materialManufacturerDao.addRLByMaterialId(materialId,manufacturerIds,beizhu);
    }
    public void buildRLByManufacturerId(Long manufacturerId,Long[] materialIds,String beizhu){
        materialManufacturerDao.deleteBLByManufacturerId(manufacturerId);
        if(null!=materialIds && materialIds.length>0)
            materialManufacturerDao.addRLByManufacturerId(manufacturerId,materialIds,beizhu);
    }

    public List<MaterialManufacturerRL> getRLByMaterialId(Long materialId){
        return materialManufacturerDao.queryByMaterialId(materialId);
    }
    public List<MaterialManufacturerRL> getRLByManufacturerId(Long manufacturerId){
        return materialManufacturerDao.queryByManufacturerId(manufacturerId);
    }
}

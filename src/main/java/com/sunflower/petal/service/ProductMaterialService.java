package com.sunflower.petal.service;

import com.sunflower.petal.dao.ProductMaterialDao;
import com.sunflower.petal.entity.Material;
import com.sunflower.petal.entity.Product;
import com.sunflower.petal.entity.ProductMaterialRL;
import com.sunflower.petal.entity.ext.AssemblyItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xiangkui on 14-2-13.
 */
@Service("productMaterialService")
public class ProductMaterialService {
    @Autowired
    private ProductMaterialDao productMaterialDao;

    @Autowired
    private MaterialService materialService;
    @Autowired
    private ProductService productService;

    public void buildRLByMaterialId(Long materialId,Long[] productIds,String beizhu){
        productMaterialDao.deleteBLByMaterialId(materialId);
        if(null!=productIds && productIds.length>0)
            productMaterialDao.addRLByMaterialId(materialId,productIds,beizhu);
    }
    public void buildRLByProductId(Long productId,Long[] materialIds,String beizhu){
        productMaterialDao.deleteBLByProductId(productId);
        if(null!=materialIds && materialIds.length>0)
            productMaterialDao.addRLByProductId(productId, materialIds, beizhu);
    }

    public List<ProductMaterialRL> getRLByMaterialId(Long materialId){
        return productMaterialDao.queryByMaterialId(materialId);
    }
    public List<ProductMaterialRL> getRLByProductId(Long productId){
        return productMaterialDao.queryByProductId(productId);
    }

    //置换出所需要的材料
    public List<Material> getMaterialsByProductId(Long productId){
        List<ProductMaterialRL> rlByProductId = this.getRLByProductId(productId);
        List<Long> materialIds = new ArrayList<Long>();
        for (ProductMaterialRL relation : rlByProductId) {
            Long materialId = relation.getMaterialId();
            materialIds.add(materialId);
        }
        //置换出需要的材料列表
        List<Material> materials = materialService.getMaterialsByIds(materialIds);
        return materials;
    }

    public List<AssemblyItem> getAssemblyItemsByMaterialId(Long materialId){
        return Collections.EMPTY_LIST;
    }

    public List<AssemblyItem> getAssemblyItemsByProductId(Long productId){
        return productMaterialDao.queryAssemblyItemByProductId(productId);
    }

    public void addRL(ProductMaterialRL relation) {
        productMaterialDao.add(relation);
    }

    public void deleteRL(ProductMaterialRL relation) {
        Long id = relation.getId();
        productMaterialDao.deleteById(id);
    }
    public void deleteRLById(Long  id) {
        productMaterialDao.deleteById(id);
    }

    public void updateRL(ProductMaterialRL productMaterialRL) {
        productMaterialDao.update(productMaterialRL);
    }
}

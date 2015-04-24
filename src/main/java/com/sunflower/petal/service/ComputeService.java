package com.sunflower.petal.service;

import com.sunflower.petal.entity.Manufacturer;
import com.sunflower.petal.entity.Material;
import com.sunflower.petal.entity.MaterialManufacturerRL;
import com.sunflower.petal.entity.ProductMaterialRL;
import com.sunflower.petal.entity.compute.MaterialItem;
import com.sunflower.petal.entity.compute.ProductItem;
import com.sunflower.petal.entity.list.ZaojiaContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by xiangkui on 2015/3/3.
 * 计算服务：
 * M模型计算：
 *  输入：A型号灯饰x个+ B型号灯饰y个
 *  输出：材料出单详情 + 工程总造价
 */
@Service
public class ComputeService {

    @Autowired
    private ProductService productService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private ProductMaterialService productMaterialService;

    @Autowired
    private MaterialManufacturerService materialManufacturerService;

    public ZaojiaContext computeM(List<ProductItem> productItems){
        Map<Long,Long> materialsCounter = new HashMap<Long, Long>();//材料累加器
        for (ProductItem productItem : productItems) {
            Long productId = productItem.getProductId();
            Long productCount = productItem.getCount();//产品个数
            List<ProductMaterialRL> rlByProductId = productMaterialService.getRLByProductId(productId);
            for(long i=0;i<productCount;i++){
                for (ProductMaterialRL relation : rlByProductId) {
                    Long materialId = relation.getMaterialId();
                    Long materialCount = relation.getCount();
                    if(null == materialsCounter.get(materialId)){
                        materialsCounter.put(materialId,0L);
                    }
                    Long counter = materialsCounter.get(materialId);
                    counter += materialCount;
                    //回填计数器
                    materialsCounter.put(materialId,counter);
                }
            }
        }
        ZaojiaContext context = new ZaojiaContext();
        List<MaterialItem> items = new ArrayList<MaterialItem>();
        Set<Map.Entry<Long, Long>> entries = materialsCounter.entrySet();
        for(Map.Entry<Long, Long> entry:entries){
            Long materialId = entry.getKey();
            Material material = materialService.getMaterial(materialId);
            Double material_danjia = material.getJinjia();
            Long count = entry.getValue();
            MaterialItem item = new MaterialItem();
            item.setMaterialId(materialId);
            item.setMaterialName(material.getName());
            item.setMaterialDanjia(material.getJinjia());
            item.setCount(count);
            item.setZaojia(count*material_danjia);//回写价格
            //计算供货商
            List<MaterialManufacturerRL> rlByMaterialId = materialManufacturerService.getRLByMaterialId(materialId);
            for (MaterialManufacturerRL materialManufacturerRL : rlByMaterialId) {
                Long manufacturerId = materialManufacturerRL.getManufacturerId();
                item.getManufacturerIds().add(manufacturerId);
            }
            items.add(item);//添加为一个正式材料项
        }
        context.setItems(items);
        Double zaojia = this.getZaonjia(context);
        context.setZaojiao(zaojia);//回填总造价
        return context;
    }

    public Double getZaonjia(ZaojiaContext context){
        List<MaterialItem> items = context.getItems();
        //计算总投入造价
        Double zaojia = 0.0;
        for (MaterialItem item : items) {
            Long materialId = item.getMaterialId();
            Material material = materialService.getMaterial(materialId);
            Double jinjia = material.getJinjia();
            Long count = item.getCount();
            //计算出该材料项的造价值
            Double zaojiaItem = jinjia * count;
            zaojia += zaojiaItem;
        }
        return zaojia;
    }

    /*
    * 将zaojia上下文拆分成多个zaojiacontext
    * 按照供应商拆分
    * 材料分配按照：供应商先得先分配材料的原则
    * */
    public List<ZaojiaContext> getZaojiContextByManufactures(ZaojiaContext context){
        List<ZaojiaContext> result = new ArrayList<ZaojiaContext>();
        Map<Long,ZaojiaContext> zaojiaContextMap = new HashMap<Long, ZaojiaContext>();
        ZaojiaContext otherContext  =new ZaojiaContext();//其他未能分配的材料项统一纳入一个分组
        List<MaterialItem> items = context.getItems();
        for (MaterialItem item : items) {
            List<Long> manufacturerIds = item.getManufacturerIds();
            if(manufacturerIds.size()>0){//材料分配给第一个供货商
                Long manufatureId = manufacturerIds.get(0);
                if(null==zaojiaContextMap.get(manufatureId)){
                    zaojiaContextMap.put(manufatureId,new ZaojiaContext());
                }
                ZaojiaContext mContext  = zaojiaContextMap.get(manufatureId);
                mContext.getItems().add(item);//分配给改供货商
                Manufacturer manufacturer = manufacturerService.get(manufatureId);
                if(null!=manufacturer){
                    mContext.setManufacturerId(manufatureId);
                    mContext.setManufacturerName(manufacturer.getName());
                }

            }else{//分配给otherContext
                otherContext.getItems().add(item);
            }
        }
        result.addAll(zaojiaContextMap.values());
        result.add(otherContext);
        return result;
    }
}

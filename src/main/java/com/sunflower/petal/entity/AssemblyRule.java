package com.sunflower.petal.entity;

import com.sunflower.petal.entity.ext.AssemblyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangkui on 14-2-22.
 * 组装规则-非Db用
 */
public class AssemblyRule {
    private Long id;//规则号
    private Product product;//产出品
    private List<AssemblyItem> items=new ArrayList<AssemblyItem>();//装配项清单

    /**
     * 添加一个配备项
     * 支持重复添加,自动求和
     * @param material 材料
     * @param count 个数
     */
    public void addMaterial(Material material,int count){
       if(count<0){
           throw new IllegalArgumentException("count cannot smaller than 0");
       }
        AssemblyItem item=new AssemblyItem(material,count);

        addAssemblyItem(item);
    }
    public void addMaterial(Material material,int count,String beizhu){
        if(count<0){
            throw new IllegalArgumentException("count cannot smaller than 0");
        }
        AssemblyItem item=new AssemblyItem(material,count);
        item.setBeizhu(beizhu);
        addAssemblyItem(item);
    }
    public void addAssemblyItem(AssemblyItem item){
        items.add(item);
    }

    /**
     * 规则紧缩，包括去掉重复的材料规则
     */
    public void tighten(){

    }

    /**
     * 规则合法性校验
     * @return
     */
    public boolean isIllegal(){
        return true;
    }
    //getter and setter

    public List<AssemblyItem> getItems() {
        return items;
    }

    public void setItems(List<AssemblyItem> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

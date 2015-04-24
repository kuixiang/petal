package com.sunflower.petal.entity.ext;

import com.sunflower.petal.entity.Danwei;
import com.sunflower.petal.entity.Material;

/**
 * Created by xiangkui on 14-2-22
 * 临时查询计算产出
 */
public class AssemblyItem {
    private Long id;
    private Long productId;
    private String productName;//产品名称

    private Long materialId;
    private String materialName;//材料名称
    private String materialGuige;
    private String materialYanse;//材料颜色
    private Danwei materialDanwei;
    private String materialDanjia;//单价
    private Long count;//数量


    public AssemblyItem() {
    }

    public String getMaterialGuige() {
        return materialGuige;
    }

    public void setMaterialGuige(String materialGuige) {
        this.materialGuige = materialGuige;
    }

    public Danwei getMaterialDanwei() {
        return materialDanwei;
    }

    public void setMaterialDanwei(Danwei materialDanwei) {
        this.materialDanwei = materialDanwei;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialYanse() {
        return materialYanse;
    }

    public void setMaterialYanse(String materialYanse) {
        this.materialYanse = materialYanse;
    }

    public String getMaterialDanjia() {
        return materialDanjia;
    }

    public void setMaterialDanjia(String materialDanjia) {
        this.materialDanjia = materialDanjia;
    }

    private String beizhu;

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public AssemblyItem(Material material, int count) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

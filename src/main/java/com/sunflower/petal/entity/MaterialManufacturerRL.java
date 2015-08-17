package com.sunflower.petal.entity;

/**
 * Created by xiangkui on 2015/2/27.
 */
public class MaterialManufacturerRL {
    private Long id;
    private Long materialId;
    private Long manufacturerId;
    private String beizhu;

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
}

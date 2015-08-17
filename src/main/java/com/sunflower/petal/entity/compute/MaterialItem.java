package com.sunflower.petal.entity.compute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangkui on 2015/3/3.
 */
public class MaterialItem {
    private Long id;
    private Long materialId;
    private Long count;
    private String materialName;
    private Double materialDanjia;
    private Double zaojia;
    private List<Long> manufacturerIds = new ArrayList();
    private List<String> manufacturerNames = new ArrayList();

    public List<String> getManufacturerNames() {
        return this.manufacturerNames;
    }

    public void setManufacturerNames(List<String> manufacturerNames) {
        this.manufacturerNames = manufacturerNames;
    }

    public List<Long> getManufacturerIds() {
        return this.manufacturerIds;
    }

    public void setManufacturerIds(List<Long> manufacturerIds) {
        this.manufacturerIds = manufacturerIds;
    }

    public Double getMaterialDanjia() {
        return this.materialDanjia;
    }

    public void setMaterialDanjia(Double materialDanjia) {
        this.materialDanjia = materialDanjia;
    }

    public String getMaterialName() {
        return this.materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Double getZaojia() {
        return this.zaojia;
    }

    public void setZaojia(Double zaojia) {
        this.zaojia = zaojia;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaterialId() {
        return this.materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
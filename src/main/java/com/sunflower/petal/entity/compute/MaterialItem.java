package com.sunflower.petal.entity.compute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangkui on 2015/3/3.
 */
public class MaterialItem {
    private Long id;
    private Long materialId;//某个材料
    private Long count;//count 个

    //因变量
    private String materialName;
    private Double materialDanjia;
    private Double zaojia; //材料造价

    private List<Long> manufacturerIds=new ArrayList<Long>();//提供材料的供应商列表

    public List<Long> getManufacturerIds() {
        return manufacturerIds;
    }

    public void setManufacturerIds(List<Long> manufacturerIds) {
        this.manufacturerIds = manufacturerIds;
    }

    public Double getMaterialDanjia() {
        return materialDanjia;
    }

    public void setMaterialDanjia(Double materialDanjia) {
        this.materialDanjia = materialDanjia;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Double getZaojia() {
        return zaojia;
    }

    public void setZaojia(Double zaojia) {
        this.zaojia = zaojia;
    }

    public Long getId() {
        return id;
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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

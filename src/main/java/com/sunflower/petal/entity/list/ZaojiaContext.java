package com.sunflower.petal.entity.list;

import com.sunflower.petal.entity.compute.MaterialItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangkui on 2015/3/3.
 * 每日出单造价上下文
 */
public class ZaojiaContext {
    private List<MaterialItem>  items = new ArrayList<MaterialItem>();//需要材料上下文

    private Double zaojiao = Double.valueOf(0.0D);
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

    public List<MaterialItem> getItems() {
        return this.items;
    }

    public void setItems(List<MaterialItem> items) {
        this.items = items;
    }

    public Double getZaojiao() {
        return this.zaojiao;
    }

    public void setZaojiao(Double zaojiao) {
        this.zaojiao = zaojiao;
    }
}
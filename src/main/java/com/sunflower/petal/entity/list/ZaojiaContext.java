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

    //计算使用因变量
    private Double zaojiao = 0.0;//计算出来的总的造价
    private Long manufacturerId;//分供应商打印订单的时候用到
    private String manufacturerName;


    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public List<MaterialItem> getItems() {
        return items;
    }

    public void setItems(List<MaterialItem> items) {
        this.items = items;
    }

    public Double getZaojiao() {
        return zaojiao;
    }

    public void setZaojiao(Double zaojiao) {
        this.zaojiao = zaojiao;
    }
}

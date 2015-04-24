package com.sunflower.petal.entity.order;

import com.sunflower.petal.entity.CategoryType;

import java.util.List;

/**
 * Created by xiangkui on 2015/4/16.
 * 材料采购单
 */
public class MaterialOrder {
    private Long id;//单号
    private Long pid;//父单号
    private CategoryType categoryType;//类别
    private String order_man;//下单人
    private String order_time;

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getOrder_man() {
        return order_man;
    }

    public void setOrder_man(String order_man) {
        this.order_man = order_man;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }
}

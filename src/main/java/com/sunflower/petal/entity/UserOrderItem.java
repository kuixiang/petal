/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.entity;

/**
 * Created by xiangkui on 2015/8/26.
 */
public class UserOrderItem {
    private Long id;
    private Long productId;
    private Long count;
    private Double danjia;
    private String beizhu;

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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getDanjia() {
        return danjia;
    }

    public void setDanjia(Double danjia) {
        this.danjia = danjia;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
}

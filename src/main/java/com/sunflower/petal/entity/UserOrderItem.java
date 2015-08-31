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
    private Long userOrderId;
    //查询数据库辅助用
    private Long user_order_item_id;
    private Long product_id;
    private Long product_name;

    //getter and setter

    public Long getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(Long userOrderId) {
        this.userOrderId = userOrderId;
    }

    public Long getUser_order_item_id() {
        return user_order_item_id;
    }

    public void setUser_order_item_id(Long user_order_item_id) {
        this.user_order_item_id = user_order_item_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getProduct_name() {
        return product_name;
    }

    public void setProduct_name(Long product_name) {
        this.product_name = product_name;
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

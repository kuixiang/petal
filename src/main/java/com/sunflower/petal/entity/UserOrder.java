/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by xiangkui on 2015/8/25.
 * 用户订单对象
 * 记录用户下单所需要的商品的上下文信息和下单时间，订单状态等
 */
public class UserOrder {
    private Long id;
    private String title;
    // 新提交 --> 处理中 --> 完成
    //       -->       --> 废弃
    private UserOrderState state;
    private Date orderTime;
    private Long userId;
    private String beizhu="";
    private List<UserOrderItem> items;

    //下单用户名称 user.name
    private String user_name;


    //getter and setter


    public UserOrderState getState() {
        return state;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setState(UserOrderState state) {
        this.state = state;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<UserOrderItem> getItems() {
        return items;
    }

    public void setItems(List<UserOrderItem> items) {
        this.items = items;
    }
}

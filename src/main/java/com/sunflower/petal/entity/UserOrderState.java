/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.entity;

/**
 * Created by xiangkui on 2015/8/26.
 */
public enum  UserOrderState {
    NEW("新建立"),PROCESSING("处理中"),DONE("完成"),DISCARD("废弃");
    private String name;
    UserOrderState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.valueOf(this.name);
    }

    public static String StringValue(UserOrderState state) {
        return state.getName();
    }
}

/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.entity;

/**
 * Created by xiangkui on 2015/8/27.
 */
public enum LoginState {
    NOLOGIN("未登陆"),LOGIN("已登陆");

    private String state;
     LoginState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

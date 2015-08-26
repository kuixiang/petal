/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.entity;

import java.util.List;

/**
 * Created by xiangkui on 2015/8/25.
 */
public enum RepertoryType {
     MATERIAL("MATERIAL"),PRODUCT("PRODUCT");
    private String type;
    RepertoryType(String type) {
        this.type = type;
    }
    public RepertoryType[] listAll(){
        return RepertoryType.values();
    }
}

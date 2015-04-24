package com.sunflower.petal.entity;

/**
 * Created by xiangkui on 2015/4/16.
 */
public enum  CategoryType {
    MANUFACTURE_GROUP(0),MANUFACTURE(1),MATERIAL_TYPE(2);
    private int code;
    CategoryType(int code) {
        this.code = code;
    }
}

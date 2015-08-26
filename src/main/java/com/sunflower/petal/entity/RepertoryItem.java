/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.entity;

import java.util.Map;

/**
 * Created by xiangkui on 2015/8/25.
 * 仓库库存项，描述的是仓库中 材料或产品的库存情况
 */
public class RepertoryItem {
    private Long id;
    private Long goodsId;
    private String goodsType;
    private Long count;
    private String danwei;
    private String beizhu;
    private Map properties;
    //临时用辅助字段
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map getProperties() {
        return properties;
    }

    public void setProperties(Map properties) {
        this.properties = properties;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
}

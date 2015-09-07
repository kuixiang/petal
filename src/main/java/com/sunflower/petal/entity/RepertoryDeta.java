/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.entity;

import java.util.Date;

/**
 * Created by xiangkui on 2015/9/1.
 * 一次入库或者出库的存单
 * 记录了某个入库或者出库的
 */
public class RepertoryDeta {
    public enum RepertoryDetaType {
        INPUT("入库"),OUTPUT("出库");
        private String value;
        RepertoryDetaType(String value) {
            this.value = value ;
        }
    }
    private Long id;//单号
    //入库、出库
    private RepertoryDetaType type;
    //deta数值 由type决定是'+'还是'-'
    private RepertoryItem deta;
    private User operater;//操作人
    private Date operate_time;//操作时间
    private String beizhu;//备注

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RepertoryDetaType getType() {
        return type;
    }

    public void setType(RepertoryDetaType type) {
        this.type = type;
    }

    public RepertoryItem getDeta() {
        return deta;
    }

    public void setDeta(RepertoryItem deta) {
        this.deta = deta;
    }

    public User getOperater() {
        return operater;
    }

    public void setOperater(User operater) {
        this.operater = operater;
    }

    public Date getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(Date operate_time) {
        this.operate_time = operate_time;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
}

package com.sunflower.petal.entity;

import java.util.Arrays;

/**
 * Created by xiangkui on 14-2-13.
 */
public class Material {
    private Long id;
    private String name;
    private String guige;
    private String yanse;
    private Danwei danwei;
    private Double jinjia;
    private String beizhu;

    //辅助字段
    private Long[] manufacturerIds;
    private boolean selected;

    public Long[] getManufacturerIds() {
        return manufacturerIds;
    }

    public void setManufacturerIds(Long[] manufacturerIds) {
        this.manufacturerIds = manufacturerIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuige() {
        return guige;
    }

    public void setGuige(String guige) {
        this.guige = guige;
    }

    public String getYanse() {
        return yanse;
    }

    public void setYanse(String yanse) {
        this.yanse = yanse;
    }

    public Danwei getDanwei() {
        return danwei;
    }

    public void setDanwei(Danwei danwei) {
        this.danwei = danwei;
    }

    public Double getJinjia() {
        return jinjia;
    }

    public void setJinjia(Double jinjia) {
        this.jinjia = jinjia;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
}

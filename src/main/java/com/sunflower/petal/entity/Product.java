package com.sunflower.petal.entity;

import java.net.URL;

/**
 * Created by xiangkui on 14-2-22.
 */
public class Product {
    private Long id;
    private String name;
    private String beizhu;
    private String danjia;
    private Danwei danwei;
    //getter and setter

    public String getDanjia() {
        return danjia;
    }

    public void setDanjia(String danjia) {
        this.danjia = danjia;
    }

    public Danwei getDanwei() {
        return danwei;
    }

    public void setDanwei(Danwei danwei) {
        this.danwei = danwei;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
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
}

package com.sunflower.petal.entity;

import java.net.URL;

/**
 * Created by xiangkui on 14-2-22.
 */
public class Product {
    private Long id;
    private String name;
    private String beizhu;


    //getter and setter


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

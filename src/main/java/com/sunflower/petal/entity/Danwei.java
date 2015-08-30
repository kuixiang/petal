package com.sunflower.petal.entity;

/**
 * Created by xiangkui on 2015/2/20.
 */
public enum Danwei {
    GE("个"),JIAN("件"),TAO("套"),ZU("组");
    private String name;
    Danwei() {
    }
    Danwei(String name) {
        this.name= name ;
    }
     public String getName() {
         return this.name;
    }


}

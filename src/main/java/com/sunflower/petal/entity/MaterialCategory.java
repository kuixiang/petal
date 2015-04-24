package com.sunflower.petal.entity;

/**
 * Created by xiangkui on 14-2-13.
 * 材料类别
 */
public class MaterialCategory extends Tag{
    private Long id;
    private String name;
    public MaterialCategory(){

    }
    public MaterialCategory(Long id,String name){
        this.id=id;
        this.name=name;
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

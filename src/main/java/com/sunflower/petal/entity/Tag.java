package com.sunflower.petal.entity;

/**
 * Created by xiangkui on 2015/2/1.
 */
public class Tag {
    private Long id;
    private String name;

    public TagType getType() {
        return type;
    }

    public void setType(TagType type) {
        this.type = type;
    }

    private TagType type;
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

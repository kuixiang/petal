package com.sunflower.petal.entity;

/**
 * Created by xiangkui on 2014/6/8.
 */
public class RenderEvent {
    private Long id;
    private Long uid;
    private String content;
    private String coment;

    //setter and getter


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }
}

package com.sunflower.petal.entity;

import java.util.Date;

/**
 * Created by xiangkui on 2014/6/8.
 */
public class CalendarJob {
    private Long id;
    private Long uid;
    private Long reid;
    private RenderEvent renderEvent;//用于关联查询


    private Date startDay;
    private Date endDay;

    //getter and setter
    public RenderEvent getRenderEvent() {
        return renderEvent;
    }

    public void setRenderEvent(RenderEvent renderEvent) {
        this.renderEvent = renderEvent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getReid() {
        return reid;
    }

    public void setReid(Long reid) {
        this.reid = reid;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }
}

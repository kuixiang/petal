package com.sunflower.petal.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuBeanDto implements Serializable {

    public MenuBeanDto(){}

    public MenuBeanDto(TblBaseResource resourceItem){
        this.setId(resourceItem.getId());
        this.setPid(resourceItem.getpId().toString());
        this.setName(resourceItem.getName());
        this.setValue(resourceItem.getValue());
        this.setType(resourceItem.getType());
        this.setAppResourceId(resourceItem.getAppResourceId());
        this.setAppRId(resourceItem.getAppRId());
        this.setAppRPId(resourceItem.getAppRPid());
    }

    private String id;
    private String pid;
    private String appResourceId;
    private String name;
    private String value;
    private String type;
    private String appRId;
    private String appRPId;
    private List<MenuBeanDto> list = new ArrayList<MenuBeanDto>();


    public String getAppRId() {
        return appRId;
    }

    public void setAppRId(String appRId) {
        this.appRId = appRId;
    }

    public String getAppRPId() {
        return appRPId;
    }

    public void setAppRPId(String appRPId) {
        this.appRPId = appRPId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAppResourceId() {
        return appResourceId;
    }

    public void setAppResourceId(String appResourceId) {
        this.appResourceId = appResourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<MenuBeanDto> getList() {
        return list;
    }

    public void setList(List<MenuBeanDto> list) {
        this.list = list;
    }
}

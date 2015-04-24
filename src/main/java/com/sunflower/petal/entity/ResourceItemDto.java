package com.sunflower.petal.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yfwangbing on 1/9/14.
 */
public class ResourceItemDto implements Serializable {
    private Integer id;
    private Integer gId;
    private Integer pId;
    private String appResourceId;
    private String name;
    private String value;
    private String type;
    private String appRId;
    private String appRPid;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;

    private List<ResourceItemDto> list = new ArrayList<ResourceItemDto>();
    public ResourceItemDto() {

    }
    public ResourceItemDto(TblBaseResource resourceItem){
        this.setId(Integer.parseInt(resourceItem.getId()));
        this.setgId(resourceItem.getGId());
        this.setpId(resourceItem.getpId());
        this.setName(resourceItem.getName());
        this.setValue(resourceItem.getValue());
        this.setType(resourceItem.getType());
        this.setAppResourceId(resourceItem.getAppResourceId());
        this.setAppRId(resourceItem.getAppRId());
        this.setAppRPid(resourceItem.getAppRPid());
        this.setExt1(resourceItem.getExt1());
        this.setExt2(resourceItem.getExt2());
        this.setExt3(resourceItem.getExt3());
        this.setExt4(resourceItem.getExt4());
        this.setExt5(resourceItem.getExt5());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
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

    public String getAppRId() {
        return appRId;
    }

    public void setAppRId(String appRId) {
        this.appRId = appRId;
    }

    public String getAppRPid() {
        return appRPid;
    }

    public void setAppRPid(String appRPid) {
        this.appRPid = appRPid;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4;
    }

    public String getExt5() {
        return ext5;
    }

    public void setExt5(String ext5) {
        this.ext5 = ext5;
    }

    public List<ResourceItemDto> getList() {
        return list;
    }

    public void setList(List<ResourceItemDto> list) {
        this.list = list;
    }
}

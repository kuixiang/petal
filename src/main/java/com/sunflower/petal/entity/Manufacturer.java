package com.sunflower.petal.entity;

/**
 * Created by xiangkui on 2015/2/25.
 * 供货商
 */
public class Manufacturer {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String beizhu;

    private Long[] materialIds;

    public Long[] getMaterialIds() {
        return materialIds;
    }

    public void setMaterialIds(Long[] materialIds) {
        this.materialIds = materialIds;
    }

    private boolean selected ;//辅助展示，被选中状态

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
}

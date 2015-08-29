package com.sunflower.petal.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by xiangkui on 2015/8/27.
 */
@Component
public class Company {
    @Value("${company.name}")
    private String name;
    @Value("${company.address}")
    private String address;
    @Value("${company.telephone}")
    private String telephone;
    @Value("${company.phone}")
    private String phone;
    @Value("${company.fax}")
    private String fax;
    @Value("${company.ceo}")
    private String ceo;
    @Value("${company.description}")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

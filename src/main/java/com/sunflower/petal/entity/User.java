package com.sunflower.petal.entity;

import java.util.List;

/**
 * Created by xiangkui on 14-2-15.
 * 系统分为未注册用户和已注册用户
 * 1: 已注册用户信息自己维护，管理员辅助维护
 * 2：未注册用户信息由管理员维护，默认给予 username = name ,password = username , userRole = [common]
 * 3: 用户可以有多个角色，角色由管理员维护
 */
public class User {
    //基础信息维护------------------------------------------------
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String beizhu;
    //系统账户相关------------------------------------------------
    //用户是否能使用该系统
    private boolean enable;
    //登录系统 用户名&密码
    private String username;
    private String password;
    //用户角色，可以有多个
    private List<UserRole> userRole;
    //用户的登陆状态
    private LoginState loginState;
    //----------------------------------------------------------

    //getter and setter

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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(List<UserRole> userRole) {
        this.userRole = userRole;
    }

    public LoginState getLoginState() {
        return loginState;
    }

    public void setLoginState(LoginState loginState) {
        this.loginState = loginState;
    }
}

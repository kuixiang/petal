package com.sunflower.petal.entity;

import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by xiangkui on 2014/6/8.
 */
public class UserHolder implements Cloneable{

    public static final String COOKIE_KEY_USERNAME = "_c_k_u";
    public static final String COOKIE_KEY_PWD = "_c_v_p";
    public static final String REQUEST_KEY_HOLDER = "userHolder";
    public static final String COOKIE_KEY_OUT_TIME = "_c_k_o_t_%s";

    public UserHolder(User user){
        this.user=user;
    }
    private User user;//用户属性
    private List<MenuBeanDto> menuInfo;//用户权限信息列表
    private boolean isLogin = false;//用户是否登录标记，true已登录；

    @Override
    public UserHolder clone(){
        return new UserHolder(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MenuBeanDto> getMenuInfo() {
        return menuInfo;
    }
    public void setMenuInfo(List<MenuBeanDto> menuInfo) {
        this.menuInfo = menuInfo;
    }

    public boolean isLogin() {
        return isLogin;
    }
    public void setLogin(boolean b) {
        isLogin = true;
    }
}

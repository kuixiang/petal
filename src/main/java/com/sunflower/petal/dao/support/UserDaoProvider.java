/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.dao.support;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunflower.petal.dao.UserDao;
import com.sunflower.petal.entity.LoginState;

/**
 * Created by xiangkui on 2015/8/27.
 */
public class UserDaoProvider {

    private static final String TNAME = UserDao.TNAME;
    public final static Logger logger = LoggerFactory.getLogger(UserDaoProvider.class);
    public String login(Map map){
        Long id = (Long) map.get("id");
        StringBuilder sb = new StringBuilder("");
        sb.append("update "+TNAME + " set loginState='"+ LoginState.LOGIN+"'"+" where id="+id);
        logger.debug("UserDaoProvider.logout：{}", sb.toString());
        return sb.toString();
    }
    public String logout(Map map) {
        Long id = (Long) map.get("id");
        StringBuilder sb = new StringBuilder("");
        sb.append("update "+TNAME + " set loginState='"+ LoginState.NOLOGIN+"'"+" where id="+id);
        logger.debug("UserDaoProvider.logout：{}",sb.toString());
        return sb.toString();
    }
}

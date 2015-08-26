/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunflower.petal.dao.UserOrderDao;
import com.sunflower.petal.entity.UserOrder;
import com.sunflower.petal.utils.CommonUtil;

/**
 * Created by xiangkui on 2015/8/25.
 * 用户订单服务类对象
 */
@Service
public class UserOrderService extends AbstractDataTableHelper{
    @Autowired
    private UserOrderDao userOrderDao;
    @Override
    public Long getCountBySearchName(String search) {
        return userOrderDao.getCountBySearchName(search);
    }

    @Override
    public List listPageAndSearchByName(Integer start, Integer length, String search) {
        return  userOrderDao.listPageAndSearchByName(start, length,search);
    }

    public void discard(Long[] ids) {
        userOrderDao.discard(ids);
    }

    public UserOrder get(Long id) {
        return userOrderDao.get(id);
    }

    public void save(UserOrder userOrder) {
        if(CommonUtil.IsNotNull(userOrder.getId())){
            userOrderDao.update(userOrder);
        } else {
            userOrderDao.add(userOrder);
            userOrderDao.addItems(userOrder.getItems());
        }
    }
}

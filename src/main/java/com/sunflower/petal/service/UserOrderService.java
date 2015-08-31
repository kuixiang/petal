/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunflower.petal.dao.UserOrderDao;
import com.sunflower.petal.entity.UserOrder;
import com.sunflower.petal.entity.UserOrderItem;
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
        List<UserOrderItem> items = userOrder.getItems();
        if(CommonUtil.IsNotNull(userOrder.getId())){//更新模式
            userOrderDao.update(userOrder);
            userOrderDao.deleteAllItems(userOrder.getId());
            if(CommonUtil.IsNotNull(items)) {
                Long userOrderId = userOrder.getId();
                for (UserOrderItem item : items) {
                    //同步关联上订单内容Id
                    item.setUserOrderId(userOrderId);
                }
                userOrderDao.addItems(userOrder.getItems());
            }
        } else { //新增模式
            userOrder.setOrderTime(new Date());
            userOrderDao.add(userOrder);
            if(CommonUtil.IsNotNull(items)) {
                Long user_order_id = userOrder.getId();
                for (UserOrderItem item : items) {
                    //同步关联上订单内容Id
                    item.setUserOrderId(user_order_id);
                }
                userOrderDao.addItems(userOrder.getItems());
            }
        }
    }
}

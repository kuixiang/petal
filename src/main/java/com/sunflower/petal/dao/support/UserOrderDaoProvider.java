/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.dao.support;

import java.util.List;
import java.util.Map;

import com.sunflower.petal.dao.UserOrderDao;
import com.sunflower.petal.entity.UserOrder;
import com.sunflower.petal.entity.UserOrderItem;

/**
 * Created by xiangkui on 2015/8/26.
 */
public class UserOrderDaoProvider {
    private static final String COLUMNS = UserOrderDao.COLUMNS;
    private static final String VALUES = UserOrderDao.VALUES;
    private static final String  TNAME = UserOrderDao.TNAME;

    private static final String  TNAME_ITEM = UserOrderDao.TNAME_ITEM;
    private static final String COLUMNS_ITEM = UserOrderDao.COLUMNS_ITEM;

    public String get(Map map){
        Long id = (Long) map.get("id");
        String sql = "select * from "+TNAME +","+TNAME_ITEM+" where "+TNAME+".id="+TNAME_ITEM+".productId"+" AND "
                + "id="+id;
        return sql;
    }
    public String add(Map map) {
        UserOrder userOrder = (UserOrder) map.get("userOrder");
        StringBuilder sb = new StringBuilder("insert into "+TNAME +"("+COLUMNS+")"+" values "+"(");
        sb.append(userOrder.getTitle());
        sb.append(","+userOrder.getState());
        sb.append(","+userOrder.getOrderTime());
        sb.append(","+userOrder.getUserId());
        sb.append(","+userOrder.getBeizhu());
        sb.append(")");
        return sb.toString();
    }

    public String addItems(Map map) {
        List<UserOrderItem> items = (List<UserOrderItem>) map.get("items");
        StringBuilder sb = new StringBuilder();
        sb.append("insert into "+ TNAME_ITEM + "("+COLUMNS_ITEM+") values " );
        for (int i = 0; i < items.size(); i++) {
            sb.append("(");
            UserOrderItem item = items.get(i);
            sb.append(item.getProductId());
            sb.append(","+item.getCount());
            sb.append(","+item.getDanjia());
            sb.append(","+item.getBeizhu());
            sb.append(")");
            if (i < items.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }

}

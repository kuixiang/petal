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
//        String sql = "select * from "+TNAME +","+TNAME_ITEM+" where "+TNAME+".id="+TNAME_ITEM+".productId"+" AND "
//                + TNAME+".id="+id;
        String sql = new StringBuilder()
                .append("select \n")
                .append("a.id\n")
                .append(",a.title\n")
                .append(",a.userId as userId\n")
                .append(",a.orderTime as orderTime\n")
                .append(",a.state as state\n")
                .append(",c.`name` as user_name\n")
                .append(",b.id as user_order_item_id\n")
                .append(",b.productId as product_id\n")
                .append(",d.name as product_name\n")
                .append(",b.danjia\n")
                .append(",b.count\n")
                .append("from user_order a\n")
                .append("LEFT JOIN user_order_item b\n")
                .append("ON\n")
                .append("a.id = b.userorderId\n")
                .append("LEFT JOIN `user` c\n")
                .append("ON\n")
                .append("a.userId = c.id\n")
                .append("LEFT JOIN product d\n")
                .append("ON b.productId = d.id").toString();
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

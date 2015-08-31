/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.dao.support;

import java.util.List;
import java.util.Map;

import com.sunflower.petal.dao.ProductDao;
import com.sunflower.petal.dao.UserDao;
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

    private static final String TNAME_USER = UserDao.TNAME;
    private static final String TNAME_PRODUCT = ProductDao.TNAME;

    public String get(Map map){
        Long id = (Long) map.get("id");
        String sql = new StringBuilder()
                .append("SELECT\n")
                .append("\ta.id,\n")
                .append("\ta.title,\n")
                .append("\ta.userId AS userId,\n")
                .append("\ta.orderTime AS orderTime,\n")
                .append("\ta.state AS state,\n")
                .append("\tc.`name` AS user_name\n")
                .append("FROM\n")
                .append("\t"+TNAME+" a\n")
                .append("LEFT JOIN "+"`"+TNAME_USER+"`"+" c ON a.userId = c.id\n")
                .append("WHERE\n")
                .append("\ta.id = "+id)
                .toString();
        return sql;
    }

    public String getItems(Long id) {
//        Long id = (Long) map.get("id");
        String sql = new StringBuilder()
                .append("SELECT\n"
                        + "\ta.*\n"
                        + ",a.id as user_order_item_id\n"
                        + ",b.id as product_id\n"
                        + ",b.name as product_name\n"
                        + "FROM\n"
                        + "\t "+TNAME_ITEM+" a\n"
                        + "JOIN "+TNAME_PRODUCT+" b\n"
                        + "ON a.productId = b.id\n"
                        + "WHERE\n"
                        + "\tuserOrderId = "+id)
                .toString();
        return sql;
    }
    public String add(Map map) {
        UserOrder userOrder = (UserOrder) map.get("userOrder");
        StringBuilder sb = new StringBuilder("insert into "+TNAME +"("+COLUMNS+")"+" values "+"(");
        sb.append(""+"'"+userOrder.getTitle()+"'");
        sb.append(","+userOrder.getState());
        sb.append(","+"#{userOrder.orderTime,jdbcType=DATE}");
        sb.append(","+userOrder.getUserId());
        sb.append(","+"'"+userOrder.getBeizhu()+"'");
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
            sb.append(","+item.getUserOrderId());
            sb.append(")");
            if (i < items.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

}

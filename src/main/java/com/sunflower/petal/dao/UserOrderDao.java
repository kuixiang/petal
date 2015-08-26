/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.sunflower.petal.dao.support.UserOrderDaoProvider;
import com.sunflower.petal.entity.UserOrder;
import com.sunflower.petal.entity.UserOrderItem;

/**
 * Created by xiangkui on 2015/8/26.
 * 用户订单持久化类
 */
public interface UserOrderDao {
    //主表
    public final String TNAME = "user_order";
    public final String COLUMNS = "title,state,orderTime,userId,beizhu";
    public final String VALUES = "#{title},#{state},#{orderTime},#{userId},#{beizhu}";
    public final String UPDATES = "title=#{userOrder.title}"+
            ",state=#{userOrder.state}"+
            ",orderTime=#{userOrder.orderTime}"+
            ",userId=#{userOrder.userId}"+
            ",beizhu=#{userOrder.beizhu}";
    public final String SEARCHKEY = "id";

    //上下文内容表
    public final String TNAME_ITEM = "user_order_item";
    String COLUMNS_ITEM = "productId,count,danjia,beizhu";
    String VALUES_ITEM = "#{productId},#{count},#{danjia},#{beizhu}";

    @Select("select count(*) from "+TNAME+" where "+SEARCHKEY+" like CONCAT('%',#{search},'%')")
    public Long getCountBySearchName(String search) ;

    @Select("select * from "+TNAME+" where "+SEARCHKEY+" like CONCAT('%',#{search},'%') limit #{length} offset #{start}")
    List listPageAndSearchByName(Integer start, Integer length, String search);

    @Update("update " + TNAME + "set state='DISCARD' where id in #{ids}")
    void discard(@Param("ids") Long[] ids);

    @SelectProvider(type= UserOrderDaoProvider.class,method = "get")
    UserOrder get(@Param("id")Long id);

    @SelectProvider(type = UserOrderDaoProvider.class,method = "add")
    void add(UserOrder userOrder);

    @SelectProvider(type = UserOrderDaoProvider.class,method = "addItems")
    void addItems(List<UserOrderItem> items);

    // 不支持订单中添加或增加商品信息
    @Update("update "+TNAME+" set "+UPDATES)
    void update(UserOrder userOrder);

    @Insert("insert into "+TNAME_ITEM +"("+COLUMNS_ITEM+")"+"("+VALUES_ITEM+")")
    void addUserOrderItem(Long id,UserOrderItem item);

    @Delete("delete * from "+TNAME_ITEM +" where id=#{id}")
    void removeUserOrderItem(@Param("id")Long userOrderItemId);
}

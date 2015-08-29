/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
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

    //用户表
    public final String TNAME_USER = UserDao.TNAME;
    public final String SEARCHKEY_USER = TNAME_USER+".name";

    @Select("select count(*) from "+TNAME+" where "+SEARCHKEY+" like CONCAT('%',#{search},'%')")
    public Long getCountBySearchName(String search) ;

    @Select("select "+TNAME+".*,"+TNAME_USER+".name as user_name"+" from "+TNAME+","+TNAME_USER+" where "
            +TNAME+".userId="+TNAME_USER+".id"
            +" and "
            +SEARCHKEY_USER+" like "
            + "CONCAT"
            + "('%',"
            + "#{search},"
            + "'%') "
            + "limit "
            + "#{length} "
            + "offset "
            + "#{start}")
    List<UserOrder> listPageAndSearchByName(@Param("start")Integer start, @Param("length")Integer length, @Param("search")String search);

    @Update("update " + TNAME + "set state='DISCARD' where id in #{ids}")
    void discard(@Param("ids") Long[] ids);


    @SelectProvider(type= UserOrderDaoProvider.class,method = "get")
//    @Results(
//            @Result(property="list",column="id",javaType=List.class,
//                    many=@Many(select="com.wzkj.manage.mapper.RoutePointMapper.getByRoute"))
//    )
    UserOrder get(@Param("id")Long id);

//    查询用户订单页上下文

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

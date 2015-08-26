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
import com.sunflower.petal.dao.support.RepertoryDaoProvider;
import com.sunflower.petal.entity.RepertoryItem;

/**
 * Created by xiangkui on 2015/8/25.
 */
public interface RepertoryDao {
    String TNAME = "repertory";
    String COLUMNS = "goodsId,goodsType,count,beizhu";
    String VALUES = "#{goodsId},#{goodsType},#{count},#{beizhu}";
    String UPDATES = "goodsId=#{repertoryItem.goodsId}" +
            ",goodsType=#{repertoryItem.goodsType}"+
            ",count=#{repertoryItem.count}"+
            ",beizhu=#{repertoryItem.beizhu}";
    /**
     * 分页查询接口
     * @return
     */
    @SelectProvider(type = RepertoryDaoProvider.class, method = "listPageAndSearchByName")
    public List<RepertoryItem> listPageAndSearchByName(@Param("start") int start, @Param("length") int length, @Param
            ("search") String search,@Param("goodsType") String goodsType);

    @SelectProvider(type = RepertoryDaoProvider.class, method = "getCountBySearchName")
    Long getCountBySearchName(@Param("search") String search,@Param("goodsType") String goodsType);

    @Select("select * from "+TNAME+" where id=#{id}")
    public RepertoryItem get(@Param("id")Long id);

    @Insert("insert into "+TNAME+"("+COLUMNS+") values ("+VALUES+")")
    public void add(RepertoryItem repertoryItem);

    @Update("update "+TNAME+ " set "+UPDATES)
    public int update(@Param("repertoryItem") RepertoryItem repertoryItem);

    @Delete("delete from "+TNAME+" where id in #{ids}")
    void delete(@Param("ids")Long[] ids);
}

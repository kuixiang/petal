/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.sunflower.petal.dao.support.RepertoryDaoProvider;
import com.sunflower.petal.entity.RepertoryDeta;
import com.sunflower.petal.entity.RepertoryItem;
import com.sunflower.petal.entity.RepertoryType;

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

    String TNAME_DAN = "repertory_dan";
    String COLUMS_DAN = "deta_goodsId,deta_goodsType,deta_count"
            +",operater"
            + ",operate_time"
            + ",type"
            + ",beizhu";
    String VALUES_DAN = "#{deta.goodsId},#{deta.goodsType},#{deta.count}"
            + ",#{user.operater},"
            + "#{operate_time}"
            + ",#{type}"
            + ",#{beizhu}";
    /**
     * 分页查询接口
     * @return
     */
    @SelectProvider(type = RepertoryDaoProvider.class, method = "listDanPageAndSearchByName")
    public List<RepertoryItem> listDanPageAndSearchByName(@Param("start") int start, @Param("length") int length, @Param
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

    @Delete("delete from "+TNAME+" where id in #{ids}")
    void delete(@Param("ids") List<Long> ids);

    @Select("select * from " + TNAME + " where goodsType=#{goodsType} and id=#{id}")
    RepertoryItem getByRelationId(@Param("goodsType")RepertoryType goodsType, @Param("id")Long id);

    @Select("select * from "+TNAME)
    List<RepertoryItem> getAll();

    @InsertProvider(type = RepertoryDaoProvider.class,method = "addBatch")
    void addBatch(@Param("items")List<RepertoryItem> items);

    @UpdateProvider(type = RepertoryDaoProvider.class,method = "updateRepertoryCount")
    void updateRepertoryCount(@Param("id")Long id, @Param("type")RepertoryDeta.RepertoryDetaType type, @Param("count")Long count);

    /*****************************************************************************************************************/
//    入库、出库单
    @Insert("inser into "+TNAME_DAN+"("+COLUMS_DAN+")"+"values ("+VALUES_DAN+")")
    void add_dan(@Param("repertoryDeta")RepertoryDeta repertoryDeta);

    // 多条件查询查询库存情况
    @SelectProvider(type = RepertoryDaoProvider.class,method = "getListByQueryTerms")
    List<RepertoryItem> getListByQueryTerms(
            @Param("type") RepertoryType type
            , @Param("start")Integer start
            , @Param("length")Integer length
            , @Param("queryTerms")List<String> queryTerms);
}

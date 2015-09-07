/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.dao.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sunflower.petal.dao.MaterialDao;
import com.sunflower.petal.dao.ProductDao;
import com.sunflower.petal.dao.RepertoryDao;
import com.sunflower.petal.entity.RepertoryDeta;
import com.sunflower.petal.entity.RepertoryItem;
import com.sunflower.petal.entity.RepertoryType;

/**
 * Created by xiangkui on 2015/8/25.
 */
public class RepertoryDaoProvider {
    private final String TNAME = RepertoryDao.TNAME;
    private final String COLUMNS = RepertoryDao.COLUMNS;

    private final String Material_TNAME = MaterialDao.TNAME;
    private final String Product_TNAME = ProductDao.TNAME;
    public String listDanPageAndSearchByName(Map map) {
        int start = (Integer) map.get("start");
        int length = (Integer) map.get("length");
        String search = (String) map.get("search");
        String goodsType = (String) map.get("goodsType");
        String splitTb = "";
        if (goodsType.equals(Material_TNAME)) {
            splitTb = Material_TNAME;
        } else if (goodsType.equals(Product_TNAME)) {
            splitTb = Product_TNAME;
        } else {
            throw new IllegalStateException("Illegal goodsType:"+goodsType);
        }
        String sql = "select "+TNAME+".*,"+splitTb+".name from "+TNAME+","+splitTb
                +" where "+TNAME+".goodsId="+splitTb+".id and "+TNAME+".goodsType="+goodsType+" and "
                + splitTb+".name like CONCAT('%',"+search+",'%') ";

        String mysql = "select * from ("+sql+") result limit "+length+" offset "+start;
        return mysql;
    }
    public String getCountBySearchName(Map map){
        String search = (String) map.get("search");
        String goodsType = (String) map.get("goodsType");
        String splitTb = "";
        if (goodsType.equals(Material_TNAME)) {
            splitTb = Material_TNAME;
        } else if (goodsType.equals(Product_TNAME)) {
            splitTb = Product_TNAME;
        } else {
            throw new IllegalStateException("Illegal goodsType:"+goodsType);
        }
        String sql = "select "+TNAME+".*,"+splitTb+".name from "+TNAME+","+splitTb
                +" where "+TNAME+".goodsId="+splitTb+".id and "+TNAME+".goodsType="+goodsType+" and "
                + splitTb+".name like CONCAT('%',"+search+",'%') ";

        String mysql = "select count(*) from ("+sql+")";
        return mysql;
    }

    public String addBatch(Map map) {
        List<RepertoryItem> items = (List<RepertoryItem>) map.get("items");
        StringBuilder sb = new StringBuilder();
        //goodsId,goodsType,count,beizhu
        sb.append("insert into "+TNAME +"("+COLUMNS+") values ");
        for(int i=0;i<items.size();i++) {
             RepertoryItem item = items.get(i);
            sb.append("(");
            sb.append(item.getGoodsId());
            sb.append(","+item.getGoodsType());
            sb.append(","+item.getCount());
            sb.append(","+"'"+item.getBeizhu()+"'");
            sb.append(")");
            if (i<items.size()-1){
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public String updateRepertoryCount(Map map){
        Long id = (Long) map.get("id");
        RepertoryDeta.RepertoryDetaType type = (RepertoryDeta.RepertoryDetaType) map.get("type");
        Long count = (Long) map.get("count");
        StringBuilder sb = new StringBuilder();
        String operaterStr = "+";
        if(RepertoryDeta.RepertoryDetaType.INPUT.equals(type)){ // 入库
            operaterStr = "+";
        } else { //出库
            operaterStr = "-";
        }
        sb.append("update "+TNAME+" set count=count"+operaterStr+count+" where id="+id);
        return sb.toString();
    }

    /** 查询库存量情况
     *
     * @param map
     * @return
     */
    public String getListByQueryTerms(Map map) {
        StringBuilder sb = new StringBuilder("");
        String RELATION_TNAME = "";
        RepertoryType type = (RepertoryType) map.get("type");
        Integer start = (Integer) map.get("start");
        Integer length = (Integer) map.get("length");
        List<String> queryTerms  = (List<String>) map.get("queryTerms");
        switch (type) {
            case MATERIAL://材料相关
                RELATION_TNAME = Material_TNAME;
                break;
            case PRODUCT://产品相关
                RELATION_TNAME = Product_TNAME;
                break;
            default:
                throw new IllegalArgumentException("非法类别:"+type);
        }

        StringBuilder subTableA = new StringBuilder("");
        subTableA.append("select * from "+TNAME+" where goodsType = "+type);
        for (String queryTerm : queryTerms) {
            subTableA.append(" and ");
            subTableA.append(queryTerm);
        }
        sb.append("select a.*,b.`name` from "+"("+subTableA+") a LEFT JOIN "+RELATION_TNAME +" b ON b.id=a.goodsId");
        sb.append(" limit "+length+" offset "+start);
        return sb.toString();
    }
}

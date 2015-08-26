/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.dao.support;

import java.util.Map;

import com.sunflower.petal.dao.MaterialDao;
import com.sunflower.petal.dao.ProductDao;
import com.sunflower.petal.dao.RepertoryDao;

/**
 * Created by xiangkui on 2015/8/25.
 */
public class RepertoryDaoProvider {
    private final String TNAME = RepertoryDao.TNAME;
    private final String MaterialName = MaterialDao.TNAME;
    private final String ProductName  = ProductDao.TNAME;
    public String listPageAndSearchByName(Map map) {
        int start = (Integer) map.get("start");
        int length = (Integer) map.get("length");
        String search = (String) map.get("search");
        String goodsType = (String) map.get("goodsType");
        String splitTb = "";
        if (goodsType.equals(MaterialName)) {
            splitTb = MaterialName;
        } else if (goodsType.equals(ProductName)) {
            splitTb = ProductName;
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
        if (goodsType.equals(MaterialName)) {
            splitTb = MaterialName;
        } else if (goodsType.equals(ProductName)) {
            splitTb = ProductName;
        } else {
            throw new IllegalStateException("Illegal goodsType:"+goodsType);
        }
        String sql = "select "+TNAME+".*,"+splitTb+".name from "+TNAME+","+splitTb
                +" where "+TNAME+".goodsId="+splitTb+".id and "+TNAME+".goodsType="+goodsType+" and "
                + splitTb+".name like CONCAT('%',"+search+",'%') ";

        String mysql = "select count(*) from ("+sql+")";
        return mysql;
    }
}

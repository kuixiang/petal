/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunflower.petal.dao.RepertoryDao;
import com.sunflower.petal.entity.DataTableRequest;
import com.sunflower.petal.entity.DataTableResponse;
import com.sunflower.petal.entity.Manufacturer;
import com.sunflower.petal.entity.Material;
import com.sunflower.petal.entity.RepertoryItem;
import com.sunflower.petal.entity.RepertoryType;
import com.sunflower.petal.utils.CommonUtil;

/**
 * Created by xiangkui on 2015/8/24.
 */
@Service
public class RepertoryService{

    @Autowired
    private RepertoryDao repertoryDao;

    public Long getCountBySearchName(String search,String goodsType) {
        return repertoryDao.getCountBySearchName(search,goodsType);
    }

    public List<RepertoryItem> listPageAndSearchByName(Integer start, Integer length, String search,String goodsType) {
        return repertoryDao.listPageAndSearchByName(start, length, search, goodsType);
    }
    public DataTableResponse getDataTableList(DataTableRequest request) {
        Integer start = request.getStart();
        Integer length = request.getLength();
        String search = request.getSearch().get(DataTableRequest.SEAECH_VALUE);
        String goodsType = request.getSearch().get("goodsType");
        List<RepertoryItem> results = this.listPageAndSearchByName(start, length, search,goodsType);
        Long count = this.getCountBySearchName(search,goodsType);
        DataTableResponse response = new DataTableResponse();
        response.setData(results.toArray());
        response.setDraw(request.getDraw());
        response.setRecordsTotal(count);
        response.setRecordsFiltered(count);
        return response;
    }

    public RepertoryItem get(Long id) {
        return repertoryDao.get(id);
    }

    public void save(RepertoryItem repertoryItem) {
        Long id = repertoryItem.getId();
        if(CommonUtil.IsNotNull(id)){
            repertoryDao.update(repertoryItem);
        }else{
            repertoryDao.add(repertoryItem);
        }
    }

    /*
     *  手动更新了仓库库存信息,用于批量添加了一批材料后，需要同步库存量信息
     *  新增加了材料项，如果没有库存信息，则认为库存量为0
     *  如果删除了材料项，则库存记录项清理
     * */
    public void sync() {

    }

    public void delete(Long[] ids) {
        repertoryDao.delete(ids);
    }
}

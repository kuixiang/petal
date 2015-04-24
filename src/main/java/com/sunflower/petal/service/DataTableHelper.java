package com.sunflower.petal.service;

import com.sunflower.petal.entity.DataTableRequest;
import com.sunflower.petal.entity.DataTableResponse;

/**
 * Created by xiangkui on 2015/2/18.
 */
public interface DataTableHelper{
    public DataTableResponse getDataTableList(DataTableRequest request);
}
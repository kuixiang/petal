package com.sunflower.petal.service;

import java.util.List;
import com.sunflower.petal.entity.DataTableRequest;
import com.sunflower.petal.entity.DataTableResponse;

/**
 * Created by xiangkui on 2015/8/25.
 */
public abstract class AbstractDataTableHelper<T> implements DataTableHelper {
    public DataTableResponse getDataTableList(DataTableRequest request) {
        Integer start = request.getStart();
        Integer length = request.getLength();
        String search = request.getSearch().get(DataTableRequest.SEAECH_VALUE);
        List<T> results = this.listPageAndSearchByName(start, length, search);
        Long count = this.getCountBySearchName(search);
        DataTableResponse response = new DataTableResponse();
        response.setData(results.toArray());
        response.setDraw(request.getDraw());
        response.setRecordsTotal(count);
        response.setRecordsFiltered(count);
        return response;
    }
    public abstract Long getCountBySearchName(String search) ;
    public abstract List<T> listPageAndSearchByName(Integer start, Integer length, String search);
}

package com.sunflower.petal.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiangkui on 2015/2/1.
 */
public class DataTableRequest {
    private Integer draw;
    private Integer start;
    private Integer length;

    private HashMap<String,String> search;
    public static String SEAECH_VALUE = "value";

//    private HashMap<String,HashMap> [] columns;
//    private DataTableColumns[] columns;


    //
    private HashMap [] order;

    public HashMap [] getOrder() {
        return order;
    }

    public void setOrder(HashMap[] order) {
        this.order = order;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public HashMap<String,String> getSearch() {
        return search;
    }

    public void setSearch(HashMap<String,String> search) {
        this.search = search;
    }
//
//    public HashMap[] getColumns() {
//        return columns;
//    }
//
//    public void setColumns(HashMap[] columns) {
//        this.columns = columns;
//    }
}

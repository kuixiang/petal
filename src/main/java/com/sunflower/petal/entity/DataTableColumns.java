package com.sunflower.petal.entity;

import java.util.HashMap;

/**
 * Created by xiangkui on 2015/2/18.
 */
public class DataTableColumns {
    private String data;
    private String name;
    private String searchable;
    private String orderable;
    private HashMap search;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearchable() {
        return searchable;
    }

    public void setSearchable(String searchable) {
        this.searchable = searchable;
    }

    public String getOrderable() {
        return orderable;
    }

    public void setOrderable(String orderable) {
        this.orderable = orderable;
    }

    public HashMap getSearch() {
        return search;
    }

    public void setSearch(HashMap search) {
        this.search = search;
    }
}

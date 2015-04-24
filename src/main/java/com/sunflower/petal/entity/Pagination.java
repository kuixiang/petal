package com.sunflower.petal.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangkui on 14-3-1.
 * 分页对象实体
 */
public class Pagination<T> implements Serializable{
    /*一页大小*/
    private int pageSize;
    /*当前页*/
    private int currentPage;
    /*数据集合*/
    private List<T> list =new ArrayList<T>();

    public Pagination(){

    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", list=" + list +
                '}';
    }
}

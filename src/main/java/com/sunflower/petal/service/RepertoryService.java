/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunflower.petal.dao.MaterialDao;
import com.sunflower.petal.dao.ProductDao;
import com.sunflower.petal.dao.RepertoryDao;
import com.sunflower.petal.entity.Danwei;
import com.sunflower.petal.entity.DataTableRequest;
import com.sunflower.petal.entity.DataTableResponse;
import com.sunflower.petal.entity.Manufacturer;
import com.sunflower.petal.entity.Material;
import com.sunflower.petal.entity.Product;
import com.sunflower.petal.entity.RepertoryDeta;
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
    @Autowired
    private MaterialDao materialDao;
    @Autowired
    private ProductDao productDao;

    public Long getCountBySearchName(String search,String goodsType) {
        return repertoryDao.getCountBySearchName(search,goodsType);
    }

//    public List<RepertoryItem> listPageAndSearchByName(Integer start, Integer length, String search,String goodsType) {
//        return repertoryDao.listPageAndSearchByName(start, length, search, goodsType);
//    }

    /**
     * 多条件查询服务接口，应当了解DB模型
     * @param start 开始位置,翻页查询偏移位置
     * @param length 结果集限制长度
     * @param queryTerms 多种查询条件 映射为数据库中 ["A=x","B<y"]等sql语句
     * @return
     */
    public List<RepertoryItem> getDataTableList(RepertoryType type,Integer start,Integer length,List<String>
            queryTerms) {
        List<RepertoryItem> results =repertoryDao.getListByQueryTerms(type,start, length, queryTerms);
        return results;
    }

    /**
     * * 查询入库 出库单
     * @param request
     * @return
     */
    public DataTableResponse getDataTableListDan(DataTableRequest request) {
        Integer start = request.getStart();
        Integer length = request.getLength();
        String search = request.getSearch().get(DataTableRequest.SEAECH_VALUE);
        String goodsType = request.getSearch().get("goodsType");
        List<RepertoryItem> results = repertoryDao.listDanPageAndSearchByName(start, length, search, goodsType);
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

    /**
     * 保存 入库或出库单
     * 会影响库存量变化
     * @param deta
     */
    @Transactional
    public void add_dan(RepertoryDeta deta) {
        repertoryDao.add_dan(deta);
        RepertoryDeta.RepertoryDetaType type = deta.getType();
        RepertoryItem repertoryItem = deta.getDeta();
        Long count = repertoryItem.getCount();
        Long goodsId = repertoryItem.getGoodsId();
        RepertoryType goodsType = repertoryItem.getGoodsType();
        RepertoryItem item = repertoryDao.getByRelationId(goodsType, goodsId);
        if(null==item){ //还没有对应的库存信息，则默认认为初始库存量为 0
            item = this.buildItem(repertoryItem.getGoodsType(),repertoryItem.getId());
            repertoryDao.add(item);//增加默认库存信息
        }
        repertoryDao.updateRepertoryCount(item.getId(),type,count);
    }

    /**
     * 输出给出一个默认的 item
     * 定义数量为 0
     * @param type
     * @param relationId
     * @return
     */
    protected RepertoryItem buildItem(RepertoryType type,Long relationId) {
        RepertoryItem item = new RepertoryItem();
        item.setGoodsType(type);
        item.setGoodsId(relationId);
        item.setCount(0L);
        item.setBeizhu("");
        return item;
    }

    /**
     * 入库或者出库影响库存量变化数值
     * @param count
     */
    public void deta(Long id,RepertoryDeta.RepertoryDetaType type,Long count) {
        repertoryDao.updateRepertoryCount(id,type,count);
    }

    /*
     *  为异步处理的过程
     *  增量处理数据库对应表
     *  手动更新了仓库库存信息,用于批量添加了一批材料后，需要同步库存量信息
     *  新增加了材料项，如果没有库存信息，则认为库存量为0
     *  如果删除了材料项，则库存记录项清理
     * */
    @Async
    public void sync() {
        List<Material> materials = materialDao.listAll();
        List<Product> products = productDao.listAll();
        List<RepertoryItem> repertoryItems = repertoryDao.getAll();
        //新增库存项
        List<RepertoryItem> addItems = new ArrayList<RepertoryItem>();
        for (Material material : materials) {
            Long id = material.getId();
            RepertoryItem item = repertoryDao.getByRelationId(RepertoryType.MATERIAL, id);
            if(null==item){
                item = this.buildItem(RepertoryType.MATERIAL,material.getId());
                addItems.add(item);
            }
        }
        for (Product product : products) {
            Long id = product.getId();
            RepertoryItem item = repertoryDao.getByRelationId(RepertoryType.PRODUCT, id);
            if(null==item){
                item = this.buildItem(RepertoryType.PRODUCT,product.getId());
                addItems.add(item);
            }
        }
        if(addItems.size()>0){
            repertoryDao.addBatch(addItems);
        }
        //删除无效的库存项目
        ArrayList<Long> deleteIds = new ArrayList<Long>();
        for (RepertoryItem repertoryItem : repertoryItems) {
            RepertoryType goodsType = repertoryItem.getGoodsType();
            Long relationId = repertoryItem.getGoodsId();
            switch (goodsType) {
                case MATERIAL:
                    Material material = materialDao.get(relationId);
                    if(null==material){//不存在关联项目，则本库存描述信息是无效的
                        deleteIds.add(repertoryItem.getId());
                    }
                    break;
                case PRODUCT:
                    Product product = productDao.get(relationId);
                    if(null==product){//不存在关联项目，则本库存描述信息是无效的
                        deleteIds.add(repertoryItem.getId());
                    }
                    break;
                default:
                    throw new IllegalStateException("非法类别:"+goodsType);
            }
        }
        if(deleteIds.size()>0)
            repertoryDao.delete(deleteIds);

    }

    public void delete(Long[] ids) {
        repertoryDao.delete(ids);
    }

}

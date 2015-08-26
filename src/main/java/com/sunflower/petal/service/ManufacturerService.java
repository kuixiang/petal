package com.sunflower.petal.service;

import com.sunflower.petal.dao.ManufacturerDao;
import com.sunflower.petal.entity.DataTableRequest;
import com.sunflower.petal.entity.DataTableResponse;
import com.sunflower.petal.entity.Manufacturer;
import com.sunflower.petal.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xiangkui on 14-2-13.
 */
@Service("manufactureService")
public class ManufacturerService implements DataTableHelper {
    @Autowired
    private ManufacturerDao manufacturerDao;

    public void save(Manufacturer manufacturer){
        Long id = manufacturer.getId();
        if(CommonUtil.IsNotNull(id)){
            manufacturerDao.update(manufacturer);
        }else{
            manufacturerDao.add(manufacturer);
        }

    }
    public List<Manufacturer> getAll(){
       return manufacturerDao.listAll();
    }
    public Manufacturer get(Long id){
        return manufacturerDao.queryById(id);
    }
    public int delete(Long[] ids){
        return manufacturerDao.deleteBatchByIds(ids);
    }

    public DataTableResponse getDataTableList(DataTableRequest request) {
        Integer start = request.getStart();
        Integer length = request.getLength();
        String search = request.getSearch().get(DataTableRequest.SEAECH_VALUE);

        List<Manufacturer> materials = manufacturerDao.listPageAndSearchByName(start, length,search);

        Long count = manufacturerDao.getCountBySearchName(search);
        DataTableResponse response = new DataTableResponse();
        response.setData(materials.toArray());
        response.setDraw(request.getDraw());
        response.setRecordsTotal(count);
        response.setRecordsFiltered(count);
        return response;
    }

    public List<Manufacturer> getListByIds(List<Long> manufacturerIds) {
        return manufacturerDao.listByIds(manufacturerIds);
    }
}

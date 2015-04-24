package com.sunflower.petal.service;

import com.sunflower.petal.dao.MaterialDao;
import com.sunflower.petal.entity.DataTableRequest;
import com.sunflower.petal.entity.DataTableResponse;
import com.sunflower.petal.entity.Manufacturer;
import com.sunflower.petal.entity.Material;
import com.sunflower.petal.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by xiangkui on 14-2-13.
 */
@Service("materialService")
public class MaterialService implements DataTableHelper {
    @Autowired
    private MaterialDao materialDao;

    /**
     * 保存一个材料，材料名字区分唯一
     * @param material 材料
     */
    public void putMaterialByName(Material material){
//        Material material_db=materialDao.queryByName(material.getName());
//        if(null==material_db){
//            materialDao.add(material);
//        }else{
//            materialDao.updateByName(material);
//        }
    }

    public Material getMaterialByName(String name){
//        return materialDao.queryByName(name);
        return null;
    }

    /**
     * 添加一个材料项
     * @param material
     */
    public void saveMaterial(Material material){
        Long id = material.getId();
        if(CommonUtil.IsNotNull(id)){
            materialDao.update(material);
        }else{
            materialDao.add(material);
        }

    }

    public List<Material> getAllMaterials(){
       return materialDao.listAll();
    }
    public Material getMaterial(Long id){
        return materialDao.queryById(id);
    }
    public int delete(Long[] ids){
        return materialDao.deleteBatchByIds(ids);
    }

    @Override
    public DataTableResponse getDataTableList(DataTableRequest request) {
        Integer start = request.getStart();
        Integer length = request.getLength();
        String search = request.getSearch().get(DataTableRequest.SEAECH_VALUE);

        List<Material> materials = materialDao.listPageAndSearchByName(start, length,search);

        Long count = materialDao.getCountBySearchName(search);
        DataTableResponse response = new DataTableResponse();
        response.setData(materials.toArray());
        response.setDraw(request.getDraw());
        response.setRecordsTotal(count);
        response.setRecordsFiltered(count);
        return response;
    }

    public List<Material> getAll() {
        return materialDao.listAll();
    }

    public List<Material> getMaterialsByIds(List<Long> materialIds) {
        if(0==materialIds.size())
            return Collections.EMPTY_LIST;
        return materialDao.listByIds(materialIds);
    }


//    @Override
//    public Pagination<Material> getPagination(int pageSize, int pageIndex) {
//        int start=pageSize*pageIndex;
//        List<Material> list=materialDao.listPage(start,pageSize);
//        Pagination<Material> pagination=new Pagination<Material>();
//        pagination.setPageSize(pageSize);
//        pagination.setCurrentPage(pageIndex);
//        pagination.setList(list);
//        return pagination;
//    }
}

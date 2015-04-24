package com.sunflower.petal.service;

import com.sunflower.petal.dao.MaterialDao;
import com.sunflower.petal.dao.ProductDao;
import com.sunflower.petal.entity.DataTableRequest;
import com.sunflower.petal.entity.DataTableResponse;
import com.sunflower.petal.entity.Material;
import com.sunflower.petal.entity.Product;
import com.sunflower.petal.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiangkui on 14-2-13.
 */
@Service("productService")
public class ProductService implements DataTableHelper {
    @Autowired
    private ProductDao productDao;

    public void saveProduct(Product product){
        Long id = product.getId();
        if(CommonUtil.IsNotNull(id)){
            productDao.update(product);
        }else{
            productDao.add(product);
        }

    }
    public List<Product> getAllProducts(){
       return productDao.listAll();
    }
    public Product getMaterial(Long id){
        return productDao.queryById(id);
    }
    public int delete(Long[] ids){
        return productDao.deleteBatchByIds(ids);
    }

    @Override
    public DataTableResponse getDataTableList(DataTableRequest request) {
        Integer start = request.getStart();
        Integer length = request.getLength();
        String search = request.getSearch().get(DataTableRequest.SEAECH_VALUE);

        List<Product> materials = productDao.listPageAndSearchByName(start, length,search);

        Long count = productDao.getCountBySearchName(search);
        DataTableResponse response = new DataTableResponse();
        response.setData(materials.toArray());
        response.setDraw(request.getDraw());
        response.setRecordsTotal(count);
        response.setRecordsFiltered(count);
        return response;
    }

    public Product getProduct(Long id) {
        return productDao.queryById(id);
    }
}

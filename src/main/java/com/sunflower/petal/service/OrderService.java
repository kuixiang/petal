package com.sunflower.petal.service;

import com.sunflower.petal.dao.OrderDao;
import com.sunflower.petal.entity.*;
import com.sunflower.petal.entity.order.MaterialOrder;
import com.sunflower.petal.entity.order.MaterialOrderItem;
import com.sunflower.petal.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiangkui on 2015/4/16.
 *
 * 出单服务
 */
@Service
public class OrderService implements DataTableHelper{

    @Autowired
    private OrderDao orderDao;
    private List<Material> all;

    private MaterialOrder generateAndGet(String order_man){
        Long id = orderDao.getnerateOrderId();
        return orderDao.getOrder(id);
    }
    /*
    * 分供应商类别的订单
    * */
    public void materialChudan(User user,List<MaterialOrderItem> orders){
        String order_man = user.getUsername();
        MaterialOrder materialOrder = this.generateAndGet(order_man);
        Long orderId = materialOrder.getId();
        for (MaterialOrderItem order : orders) {
            order.setOrderId(orderId);
        }
        orderDao.batchAdd(orders);//一批订单项的保存
    }

    /*删除制定单号的订单项*/
    public void deleteMaterialOrder(Long id){
        orderDao.deleteOrderItemByOrderId(id);
        orderDao.deleteOrderById(id);
    }

    @Override
    public DataTableResponse getDataTableList(DataTableRequest request) {
        Integer start = request.getStart();
        Integer length = request.getLength();
        String search = request.getSearch().get(DataTableRequest.SEAECH_VALUE);

        List<MaterialOrder> materials = orderDao.listPageAndSearchByName(start, length,search);

        Long count = orderDao.getCountBySearchName(search);
        DataTableResponse response = new DataTableResponse();
        response.setData(materials.toArray());
        response.setDraw(request.getDraw());
        response.setRecordsTotal(count);
        response.setRecordsFiltered(count);
        return response;
    }

    public List<MaterialOrder> getAll() {
        return orderDao.listAll();
    }

    public void delete(Long[] ids) {
         orderDao.deleteBatchByIds(ids);
    }

    public void save(MaterialOrder order) {
        Long id = order.getId();
        if(CommonUtil.IsNotNull(id)){
            orderDao.update(order);
        }else{
            orderDao.add(order);
        }

    }
    public MaterialOrder getById(Long id) {
        return orderDao.queryById(id);
    }
}

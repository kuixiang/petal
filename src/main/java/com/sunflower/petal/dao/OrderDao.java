package com.sunflower.petal.dao;

import com.sunflower.petal.entity.Manufacturer;
import com.sunflower.petal.entity.Product;
import com.sunflower.petal.entity.order.MaterialOrder;
import com.sunflower.petal.entity.order.MaterialOrderItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by xiangkui on 2015/4/16.
 */
public interface OrderDao {
    public final String TNAME = "manufacturer";
    public final String COLUMNS = "name,phone,email,address,beizhu";
    public final String VALUES = "#{name},#{phone},#{email},#{address},#{beizhu}";
    public final String UPDATES = "name=#{manufacturer.name}" +
            ",phone=#{manufacturer.phone}"+
            ",email=#{manufacturer.email}"+
            ",address=#{manufacturer.address}"+
            ",beizhu=#{manufacturer.beizhu}";
    public final String SEARCHKEY = "name";


    void batchAdd(List<MaterialOrderItem> orders);

    MaterialOrder getOrder(Long id);

    Long getnerateOrderId();

    @Delete("delete from ")
    void deleteOrderItemByOrderId(Long id);

    void deleteOrderById(Long id);

    /**
     * 分页查询接口
     * @return
     */
    @Select("select * from "+TNAME+" where "+SEARCHKEY+" like CONCAT('%',#{search},'%') limit #{length} offset #{start}")
    public List<MaterialOrder> listPageAndSearchByName(@Param("start") int start, @Param("length") int length, @Param("search") String search);

    @Select("select count(*) from "+TNAME+"")
    public Long getCount();

    @Select("select count(*) from "+TNAME+" where "+SEARCHKEY+" like CONCAT('%',#{search},'%')")
    Long getCountBySearchName(String search);

    List<MaterialOrder> listAll();


    void deleteBatchByIds(Long[] ids);

    void update(MaterialOrder order);

    void add(MaterialOrder order);


    MaterialOrder queryById(Long id);
}

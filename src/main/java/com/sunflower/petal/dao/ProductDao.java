package com.sunflower.petal.dao;

import com.sunflower.petal.entity.Material;
import com.sunflower.petal.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by xiangkui on 14-2-13
 * 原材料数据库持久层.
 */
public interface ProductDao {
    public final String TNAME = "product";
    @Insert("insert into product(name,beizhu) values (#{name},#{beizhu})")
    @SelectKey(keyProperty="id" ,before = false,resultType =Long.class,statement="SELECT LAST_INSERT_ID() AS ID")
    public void add(Product product);

    @Update("update product set name=#{product.name},beizhu=#{product.beizhu} where id=#{product.id}")
    public int update(@Param("product") Product product);

    @Select("select * from product where id=#{id}")
    public Product queryById(@Param("id") Long id);

    @Select("select * from product")
    public List<Product> listAll();
    /**
     * 分页查询接口
     * @return
     */
    @Select("select * from product where name like CONCAT('%',#{search},'%') limit #{length} offset #{start}")
    public List<Product> listPageAndSearchByName(@Param("start") int start, @Param("length") int length, @Param("search") String search);

    @Select("select count(*) from product")
    public Long getCount();

    @Select("select count(*) from product where name like CONCAT('%',#{search},'%')")
    Long getCountBySearchName(String search);

    int deleteBatchByIds(@Param("ids") Long[] ids);

}

package com.sunflower.petal.dao;

import com.sunflower.petal.entity.Material;
import com.sunflower.petal.entity.MaterialCategory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiangkui on 14-2-13
 * 原材料数据库持久层.
 */
@Component
public interface MaterialCategoryDao {
    @Insert("insert into materialcategory(id,name) values(#{category.id},#{category.name})")
    public void add(@Param("category") MaterialCategory category);

    @Delete("delete from materialcategory where id=#{id}")
    public int deleteById(@Param("id") Long id);

    @Update("update materialcategory set name=#{category.name} where id=#{category.id}")
    public void updateById(@Param("category")MaterialCategory category);

    @Select(value = "select * from materialcategory where id=#{id}")
    public MaterialCategory findById(@Param("id") Long id);
    
    @Select(value = "select * from materialcategory where name=#{name}")
    public MaterialCategory findByName(@Param("name")String name);
    
    @Select(value = "select * from materialcategory")
    public List<MaterialCategory> listAll();

    @Select(value = "select * from materialcategory where id=#{id}")
    public MaterialCategory queryById(Long id);
}

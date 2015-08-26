package com.sunflower.petal.dao;

import com.sunflower.petal.dao.support.ManufacturerDaoProvider;
import com.sunflower.petal.dao.support.MaterialDaoProvider;
import com.sunflower.petal.entity.Manufacturer;
import com.sunflower.petal.entity.Material;
import com.sunflower.petal.entity.MaterialCategory;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiangkui on 14-2-13
 * 原材料数据库持久层.
 */
public interface MaterialDao {
    public final String TNAME = "material";
    @Insert("insert into material(name,guige,yanse,danwei,jinjia,beizhu) values " +
            "(#{name},#{guige},#{yanse},#{danwei},#{jinjia},#{beizhu})")
    public void add(Material material);

    @Update(" update material set name=#{material.name}," +
            "guige=#{material.guige},yanse=#{material.yanse}," +
            "danwei=#{material.danwei},jinjia=#{material.jinjia},beizhu=#{material.beizhu} where id=#{material.id}")
    public int update(@Param("material") Material material);

    @Update(" update material set name=#{material.name},categoryId=#{material.materialCategory.id}," +
            "guige=#{material.guige},yanse=#{material.yanse}," +
            "danwei=#{material.danwei},jinjia=#{material.jinjia},beizhu=#{material.beizhu} " +
            "where name=#{material.name}")
    public int updateByName(@Param("material") Material material);

    @Select("select * from material where id=#{id}")
    public Material queryById(@Param("id") Long id);

    @Select("select * from material")
    public List<Material> listAll();

    @SelectProvider(type = MaterialDaoProvider.class, method = "listByIds")
    List<Material> listByIds(@Param("ids") List<Long> ids);
    /**
     * 分页查询接口
     * @return
     */
    @Select("select * from material where name like CONCAT('%',#{search},'%') limit #{length} offset #{start}")
    public List<Material> listPageAndSearchByName(@Param("start") int start,@Param("length") int length,@Param("search")String search);

    @Select("select count(*) from material")
    public Long getCount();

    @Select("select count(*) from material where name like CONCAT('%',#{search},'%')")
    Long getCountBySearchName(String search);

//    @Delete("delete from material where id in #{ids}")
//    int deleteByIds(Long[] ids);

    int deleteBatchByIds(@Param("ids") Long[] ids);

}

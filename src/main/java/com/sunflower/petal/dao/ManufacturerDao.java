package com.sunflower.petal.dao;

import com.sunflower.petal.dao.support.ManufacturerDaoProvider;
import com.sunflower.petal.entity.Manufacturer;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by xiangkui on 14-2-13
 * 原材料数据库持久层.
 */
public interface ManufacturerDao {
    public final String TNAME = "manufacturer";
    public final String COLUMNS = "name,phone,email,address,beizhu";
    public final String VALUES = "#{name},#{phone},#{email},#{address},#{beizhu}";
    public final String UPDATES = "name=#{manufacturer.name}" +
            ",phone=#{manufacturer.phone}"+
            ",email=#{manufacturer.email}"+
            ",address=#{manufacturer.address}"+
            ",beizhu=#{manufacturer.beizhu}";
    public final String SEARCHKEY = "name";

    @Insert("insert into "+TNAME+"("+COLUMNS+") values ("+VALUES+")")
    public void add(Manufacturer manufacturer);

    @Update("update "+TNAME+" set  "+UPDATES+"where id=#{manufacturer.id}")
    public int update(@Param("manufacturer") Manufacturer manufacturer);

    @Select("select * from "+TNAME+" where id=#{id}")
    public Manufacturer queryById(@Param("id") Long id);

    @Select("select * from "+TNAME+"")
    public List<Manufacturer> listAll();
    /**
     * 分页查询接口
     * @return
     */
    @Select("select * from "+TNAME+" where "+SEARCHKEY+" like CONCAT('%',#{search},'%') limit #{length} offset #{start}")
    public List<Manufacturer> listPageAndSearchByName(@Param("start") int start, @Param("length") int length, @Param("search") String search);

    @Select("select count(*) from "+TNAME+" where "+SEARCHKEY+" like CONCAT('%',#{search},'%')")
    Long getCountBySearchName(String search);

    @Select("select count(*) from "+TNAME+"")
    public Long getCount();

    int deleteBatchByIds(@Param("ids") Long[] ids);

    @SelectProvider(type = ManufacturerDaoProvider.class, method = "listByIds")
    List<Manufacturer> listByIds(@Param("ids") List<Long> ids);
}

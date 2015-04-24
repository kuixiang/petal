package com.sunflower.petal.dao;

import com.sunflower.petal.entity.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiangkui on 14-2-13
*  数据库持久层.
 */
@Component
public interface TagDao {
    @Insert("insert into tag(id,name) values(#{tag.id},#{tag.name})")
    public void add(@Param("tag") Tag tag);

    @Delete("delete from tag where id=#{id}")
    public int delete(@Param("id") Long id);

    @Update("update tag set name=#{tag.name} where id=#{tag.id}")
    public void update(@Param("tag") Tag tag);

    @Select(value = "select * from tag")
    public List<Tag> listAll();

    @Select(value = "select * from tag where id=#{id}")
    public Tag findById(@Param("id") Long id);

    @Select(value = "select * from tag where name=#{name}")
    public Tag findByName(@Param("name") String name);
}

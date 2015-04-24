package com.sunflower.petal.dao;

import com.sunflower.petal.entity.RenderEvent;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by xiangkui on 2014/6/8.
 * 管理代办事件等
 */
public interface RenderEventDao {
    public final String TABLE="render_event";
    public final String INSERT_FIElLD="uid,content,coment";
    public final String VALUE_FILELD="#{uid},#{content},#{coment}";
    public final String UPDATE_FILELD="uid=#{uid},content=#{content},coment=#{coment}";

    @Insert("insert into "+TABLE+"("+INSERT_FIElLD+") values("+VALUE_FILELD+")")
    @SelectKey(keyProperty="id" ,before = false,resultType =Long.class,statement="SELECT LAST_INSERT_ID() AS ID")
    public void add(RenderEvent renderEvent);

    @Delete("delete from "+TABLE+" where id=#{id}")
    public void delete(Long id);

    @Update("update "+TABLE + " set "+UPDATE_FILELD +" where id=#{id}")
    public void update(RenderEvent renderEvent);

    @Select("select * from "+TABLE+" where id=#{id}")
    public RenderEvent getById(Long id);

    @Select("select * from "+TABLE+" where uid=#{uid}")
    public List<RenderEvent> getByUserId(Long uid);//关联查询结果集合
}

package com.sunflower.petal.dao;

import com.sunflower.petal.entity.CalendarJob;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by xiangkui on 2014/6/8.
 * 管理日历上的待办件等
 */
public interface CalendarJobDao {
    public final String TABLE="calendar_job";
    public final String TABLE_RENDER_EVENT="render_event";
    public final String INSERT_FIElLD="uid,reid,startDay,endDay";
    public final String VALUE_FILELD="#{uid},#{reid},#{startDay},#{endDay}";

    public final String SELECT_FILELD = TABLE+".id,"+TABLE+".uid,"+"reid,startDay,endDay,content,coment,"+TABLE_RENDER_EVENT+".id as rid,"+TABLE_RENDER_EVENT+".uid as ruid";

    @Insert("insert into "+TABLE+"("+INSERT_FIElLD+") values("+VALUE_FILELD+")")
    public void add(CalendarJob calendarJob);

    @Delete("delete from "+TABLE+" where id=#{id}")
    public void delete(Long id);
    @Delete("delete from "+TABLE+" where reid=#{reid}")
    void deleteByReId(Long reid);

    @Update("update "+TABLE + " set "+VALUE_FILELD +" where id=#{id}")
    public void update(CalendarJob calendarJob);

    @Select("select "+SELECT_FILELD+" from "+TABLE+","+TABLE_RENDER_EVENT+" where "+TABLE+".uid=#{uid} and "+TABLE+".reid="+TABLE_RENDER_EVENT+".id")
    @Results(value={
      @Result(property="renderEvent",column="reid",one=@One(select = "com.sunflower.petal.dao.RenderEventDao.getById")),
    })
    public List<CalendarJob> getByUserId(Long uid);


}

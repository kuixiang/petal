package com.sunflower.petal.dao;

import com.sunflower.petal.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by xiangkui on 14-2-15.
 */
public interface UserDao {
    @Insert(" insert into user (id,username,password) values (#{user.id},#{user.username},#{user.password})")
    void add(@Param("user") User user);

    @Delete(" delete from user where id=#{id}")
    void delete(@Param("id")Long id);

    @Update(" update user set username=#{user.username},password=#{user.password} where id=#{user.id}")
    int updateById(@Param("user") User user);

    @Select(" select * from user where id=#{id}")
    User getUserById(@Param("id")Long id);

    @Select(" select * from user")
    List<User> getUser();

    @Select(" select * from user limit #{pageSize} offset #{offset} ")
    List<User> getUserByPage(@Param("offset")int offset,@Param("pageSize") int pageSize);//offset=pageSize*(page-1)}?
}
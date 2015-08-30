package com.sunflower.petal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.sunflower.petal.dao.support.UserDaoProvider;
import com.sunflower.petal.entity.User;

/**
 * Created by xiangkui on 14-2-15.
 * 用户Id 由外部算法统一生成
 */
public interface UserDao {
    public final String TNAME = "user";
    public final String COLUMNS = "id,name,phone,email,address,beizhu,username";
    public final String VALUES = "#{user.id},#{user.name},#{user.phone},#{user.email},#{user.address},#{user.beizhu}"
            + ",#{user.username}";
    public final String UPDATES = "name=#{user.name}" +
            ",phone=#{user.phone}"+
            ",email=#{user.email}"+
            ",address=#{user.address}"+
            ",beizhu=#{user.beizhu}";
    public final String SEARCHKEY = "name";
    public final String LOGINSTATE = "loginState";
    public final String ENABLE = "enable";

//    事务性动作,应当在插入前获取Id
    @Insert("insert into "+TNAME+" ("+COLUMNS+") values ("+VALUES+")")
//    @SelectKey(keyProperty="user.id" ,before = false,resultType =Long.class,statement="SELECT LAST_INSERT_ID() AS ID")
    int add(@Param("user")User user);

    @Delete(" delete from "+TNAME+" where id=#{id}")
    void delete(@Param("id")Long id);

    @Delete("delete from "+TNAME+" where id in #{ids}")
    void batchDelete(@Param("ids")Long[] ids);

    @Update(" update "+TNAME+" set "+UPDATES+" where id=#{user.id}")
    int update(@Param("user") User user);

    //更新账户系统
    @Update("update "+TNAME+" set username=#{username},password=#{password} where id=#{id}")
    void updateAccount(@Param("id")Long id,@Param("username")String username,@Param("password")String password);
    //登入
    @UpdateProvider(type = UserDaoProvider.class,method = "login")
    void login(Long id);

    //退出
    @UpdateProvider(type = UserDaoProvider.class,method = "logout")
    void logout(@Param("id")Long id);

    @Select(" select * from "+TNAME+" where id=#{id}")
    User get(@Param("id")Long id);

    @Select("select * from "+TNAME+" where "+SEARCHKEY+" like CONCAT('%',#{search},'%') limit #{length} offset #{start}")
    List<User> listPageAndSearchByNamess(@Param("start")int start,@Param("length")int length,@Param("search")String
            search);

    @Select("select count(*) from "+TNAME+" where "+SEARCHKEY+" like CONCAT('%',#{search},'%')")
    Long getCountBySearchName(String search);

    @Select("select count(*) from "+TNAME+"")
    Long getCount();

    @Select("select * from "+TNAME+" where username = #{username} and password = #{password}")
    public User getUserByCount(@Param("username")String username, @Param("password")String password);

    @Update("update "+TNAME+" set "+ENABLE +"=false where id=#{id}")
    void disable(Long id);


    @Update("update "+TNAME+" set "+ENABLE +"=true where id=#{id}")
    void enable(Long id);

    @Select("select * from "+TNAME)
    List<User> getAll();

}
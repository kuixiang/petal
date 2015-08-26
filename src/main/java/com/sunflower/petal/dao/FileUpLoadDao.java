/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sunflower.petal.entity.FileUpload;

/**
 * Created by xiangkui on 2015/8/17.
 */
public interface FileUpLoadDao {
    public final String TNAME = "fileUpload";
    public final String COLUMNS = "name,url,type,beizhu";
    public final String VALUES = "#{name},#{url},#{type},#{beizhu}";
    public final String UPDATES_KEY = "id=#{fileUpload.id}";
    public final String UPDATES =
            "name=#{fileUpload.name}" +
            ",url=#{fileUpload.url}"+
            ",type=#{fileUpload.type}"+
            ",beizhu=#{fileUpload.beizhu}";
    public final String SEARCHKEY = "name";
    public final String SEARCHTYPE = "image_product";

    @Insert("insert into "+TNAME+"("+COLUMNS+") values ("+VALUES+")")
    public void add(FileUpload fileUpload);

    @Update("update "+TNAME+" set  "+UPDATES+"where "+UPDATES_KEY)
    public int update(@Param("fileUpload") FileUpload fileUpload);

    @Select("select * from "+TNAME+" where id=#{id}")
    public FileUpload queryById(@Param("id") Long id);

}

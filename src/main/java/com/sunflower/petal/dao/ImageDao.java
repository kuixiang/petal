package com.sunflower.petal.dao;

import com.sunflower.petal.entity.Image;
import com.sunflower.petal.entity.Material;
import com.sunflower.petal.entity.MaterialCategory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiangkui on 14-2-13
 * 原材料数据库持久层.
 */
public interface ImageDao {
    public List<Image> list();

    public Image create(Image image);

    public Image get(Long id);

    public void delete(Image image);

}

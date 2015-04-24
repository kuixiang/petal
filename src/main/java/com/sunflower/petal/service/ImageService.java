package com.sunflower.petal.service;

import com.sunflower.petal.dao.ImageDao;
import com.sunflower.petal.entity.Image;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiangkui on 14-3-20.
 */
@Service
public class ImageService{

    @Autowired
    private ImageDao imageDao;

    public List<Image> list() {
        return imageDao.list();
    }

    public Image create(Image image) {
        return imageDao.create(image);
    }

    public Image get(Long id) {
        return imageDao.get(id);
    }

    public void delete(Image image) {
        imageDao.delete(image);
    }
}

package com.sunflower.petal.service;

import com.sunflower.petal.dao.TagDao;
import com.sunflower.petal.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by xiangkui on 2015/2/1.
 */
public class TagService {
    @Autowired
    private TagDao tagDao;

    public void add(Tag tag){
        tagDao.add(tag);
    }

    public void delete(Long id){
        tagDao.delete(id);
    }


    public void update(Tag tag){
        tagDao.update(tag);
    }

    public List<Tag> listAll(){
        return tagDao.listAll();
    }

}

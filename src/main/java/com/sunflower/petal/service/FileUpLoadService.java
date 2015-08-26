/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunflower.petal.dao.FileUpLoadDao;
import com.sunflower.petal.entity.FileUpload;

/**
 * Created by xiangkui on 2015/8/17.
 */
@Service
public class FileUpLoadService {
    @Autowired
    private FileUpLoadDao fileUpLoadDao;

    public void add(FileUpload fileUpLoad){
        fileUpLoadDao.add(fileUpLoad);
    }

    public void update(FileUpload fileUpLoad){
        fileUpLoadDao.update(fileUpLoad);
    }
}

/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.service;

import java.util.List;

/**
 * Created by xiangkui on 2015/8/17.
 * 模糊查询关键字
 */
public interface FuzzyQueryHelper<T> {
//    public int PRE_MATCH=0;//前序匹配
//    public int TAIL_MATCH = 1;//后续匹配
//    public int ALL_MATH = 2;//全匹配
//    public int REVERSE_INDEX = 3;//倒排索引
//
//    public int QUERY_MODEL = ALL_MATH;
    public List<T> fuzzyQuery(String key);
}

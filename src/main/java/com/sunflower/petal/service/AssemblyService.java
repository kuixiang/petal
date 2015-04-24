package com.sunflower.petal.service;

import com.sunflower.petal.dao.AssemblyItemDao;
import com.sunflower.petal.dao.AssemblyRuleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiangkui on 14-2-22.
 */
@Service
public class AssemblyService {
    @Autowired
    private AssemblyRuleDao ruleDao;
    @Autowired
    private AssemblyItemDao itemDao;

}

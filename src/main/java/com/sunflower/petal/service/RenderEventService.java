package com.sunflower.petal.service;

import com.sunflower.petal.dao.RenderEventDao;
import com.sunflower.petal.entity.RenderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiangkui on 2014/6/8.
 */
@Service
public class RenderEventService {
    @Autowired
    private RenderEventDao renderEventDao;

    public void add(RenderEvent renderEvent){
        renderEventDao.add(renderEvent);
    }
    public void delete(Long id){
     renderEventDao.delete(id);
    }
    public void update(RenderEvent renderEvent){
       renderEventDao.update(renderEvent);
    }
    public List<RenderEvent> getByUserId(Long uid){
      return renderEventDao.getByUserId(uid);
    }

}

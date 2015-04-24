package com.sunflower.petal.service;

import com.sunflower.petal.dao.CalendarJobDao;
import com.sunflower.petal.entity.CalendarJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiangkui on 2014/6/8.
 */
@Service
public class CanlendarJobService {

    @Autowired
    private CalendarJobDao calendarJobDao;

    public void add(CalendarJob calendarJob){
        calendarJobDao.add(calendarJob);
    }
    public void delete(Long id){
        calendarJobDao.delete(id);
    }
    public void deleteByReId(Long reid){
        calendarJobDao.deleteByReId(reid);
    }
    public void update(CalendarJob calendarJob){
        calendarJobDao.update(calendarJob);
    }
    public List<CalendarJob> getByUserId(Long uid){
      return calendarJobDao.getByUserId(uid);
    }

}

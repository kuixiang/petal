package com.sunflower.petal.controller;

import com.sunflower.petal.entity.CalendarJob;
import com.sunflower.petal.entity.RenderEvent;
import com.sunflower.petal.entity.UserHolder;
import com.sunflower.petal.service.CanlendarJobService;
import com.sunflower.petal.service.RenderEventService;
import com.sunflower.petal.utils.AjaxUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by xiangkui on 2014/6/8.
 */
@Controller
@RequestMapping("/calendar")
public class CalendarCtrl {
    private static final Logger log = LoggerFactory.getLogger(ImageController.class);
    @Autowired
    private CanlendarJobService canlendarJobService;
    @Autowired
    private RenderEventService renderEventService;

    /**
     * 日历面板页
     * @param model
     * @return
     */
    @RequestMapping(value = "/calendar.html")
    public String calendar(Model model,UserHolder userHolder) {
        Long uid=userHolder.getUser().getId();
        model.addAttribute("renserEvents",JSONArray.fromObject(renderEventService.getByUserId(uid)));
        return "/calendar/calendar";
    }
    @RequestMapping(value = "/calendarJob/listByUid.ajax")
    @ResponseBody
    public List<CalendarJob> getCalendarJobs(Long start,Long end,UserHolder userHolder) {
        Long uid=userHolder.getUser().getId();
        return canlendarJobService.getByUserId(uid);
    }
    @RequestMapping(value = "/renderEvent/listByUid.ajax")
    @ResponseBody
    public List<RenderEvent> getRenderEvents(UserHolder userHolder) {
        Long uid=userHolder.getUser().getId();
        return renderEventService.getByUserId(uid);
    }
    @RequestMapping(value = "/save.ajax")
    @ResponseBody
    public JSONObject save(CalendarJob calendarJob,HttpServletRequest request) {
        UserHolder userHolder= (UserHolder) request.getAttribute("userHolder");
        Long uid=userHolder.getUser().getId();
        calendarJob.setUid(uid);
        if(null==calendarJob.getId())
            canlendarJobService.add(calendarJob);
        else
            canlendarJobService.update(calendarJob);
        return AjaxUtil.success("保存成功",calendarJob);
    }
    @RequestMapping(value = "/delete.ajax")
    @ResponseBody
    public JSONObject delete(Long id) {
        canlendarJobService.delete(id);
        return AjaxUtil.success("删除成功");
    }




}

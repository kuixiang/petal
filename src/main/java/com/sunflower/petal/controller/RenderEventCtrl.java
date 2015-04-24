package com.sunflower.petal.controller;

import com.sunflower.petal.entity.RenderEvent;
import com.sunflower.petal.entity.UserHolder;
import com.sunflower.petal.service.CanlendarJobService;
import com.sunflower.petal.service.RenderEventService;
import com.sunflower.petal.utils.AjaxUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiangkui on 2014/6/18.
 */
@Controller
@RequestMapping("/renderEvent")
public class RenderEventCtrl {
    private static final Logger log = LoggerFactory.getLogger(RenderEventCtrl.class);
    @Autowired
    private RenderEventService renderEventService;
    @Autowired
    private CanlendarJobService canlendarJobService;


    @RequestMapping(value = "/save.ajax")
    @ResponseBody
    public JSONObject save(RenderEvent renderEvent,HttpServletRequest request) {
        UserHolder userHolder= (UserHolder) request.getAttribute("userHolder");
        Long uid=userHolder.getUser().getId();
        renderEvent.setUid(uid);
        if(null==renderEvent.getId())
            renderEventService.add(renderEvent);
        else
            renderEventService.update(renderEvent);
        return JSONObject.fromObject(renderEvent);
    }

    @RequestMapping(value = "/delete.ajax")
    @ResponseBody
    public JSONObject delete(Long id) {
        canlendarJobService.deleteByReId(id);
        renderEventService.delete(id);
        return AjaxUtil.success("删除成功");
    }


}

package com.sunflower.petal.interceptor;

import com.sunflower.petal.entity.User;
import com.sunflower.petal.entity.UserHolder;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by xiangkui on 2014/6/8.
 */
public class UserHolderInterceptor implements HandlerInterceptor{
    private Long startTimestrap=System.currentTimeMillis();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        request.setAttribute("staticVersion",startTimestrap);
        User user=new User();
        UserHolder userHolder=new UserHolder(user);
        user.setId(0L);
        user.setUsername("system");
        userHolder.setUser(user);
        request.setAttribute("userHolder",userHolder);

        request.setAttribute("currentTime",new Date());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

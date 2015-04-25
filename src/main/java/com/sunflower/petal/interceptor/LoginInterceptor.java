package com.sunflower.petal.interceptor;

import com.alibaba.fastjson.JSON;
import com.sunflower.petal.common.CookieContext;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * LoginInterceptor
 *
 * @author sunny
 * @version 1.0.0
 * @since 2014-11-12
 */
public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger = Logger.getLogger(LoginInterceptor.class);

    public List<String> doNotInterceptUrl = new ArrayList<String>();

    public String cookieName;

    public String loginUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Boolean result = false;
        String requestURI = request.getRequestURI();
        logger.info("|LoginInterceptor|preHandle|requestURI|" + requestURI);
        //判断是否拦截，不拦截的直接返回请求页面，拦截的调用验证用户是否已经登陆
        //异常，登陆失败，跳转到登陆页面
        if (doNotInterceptUrl.contains(request.getRequestURI())) {
            result = true;
        } else {
            try {
                String cookieValue = CookieContext.getCookie(request, response, cookieName);
                logger.info("|LoginInterceptor|preHandle|cookieValue|" + cookieValue);
                CookieContext cookieContext = JSON.parseObject(cookieValue, CookieContext.class);
                CookieContext.setCookieContext(cookieContext);
                logger.info("|LoginInterceptor|preHandle|cookieModel|" + JSON.toJSONString(cookieContext));
                result = true;
            } catch (Exception e) {
                response.sendRedirect(loginUrl);
            }
        }
        return result;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //设置昵称，用户名，用户id
        CookieContext.removeCookieContext();
    }

    public List<String> getDoNotInterceptUrl() {
        return doNotInterceptUrl;
    }

    public void setDoNotInterceptUrl(List<String> doNotInterceptUrl) {
        this.doNotInterceptUrl = doNotInterceptUrl;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }
}

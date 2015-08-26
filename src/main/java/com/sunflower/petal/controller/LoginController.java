package com.sunflower.petal.controller;

import com.alibaba.fastjson.JSON;
import com.sunflower.petal.common.CookieContext;
import com.sunflower.petal.common.Result;
import com.sunflower.petal.entity.User;
import com.sunflower.petal.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理员登陆控制层
 *
 * @author sunny
 * @version 1.0.0
 * @since 2014-11-10
 */
//@Controller
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    //todo cookie name
    @Value("#{settings[admin_cookie_name]}")
    public String cookieName;

    //todo domain
    @Value("#{settings[domain]}")
    public String domain;

    @RequestMapping(value = "/login_view")
    public String index() {
        return "login";
    }

    /**
     * 管理员登陆
     */
    @RequestMapping(value = "/login_validate", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> login(User model, String flag, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("param model " + JSON.toJSONString(model));
        logger.debug("param flag " + flag);
        Result<Object> result = new Result<Object>();
        try {
            //todo validate params

            //login
            if (!userService.login(model)){
                throw new RuntimeException("登陆失败");
            }

            //todo 查询用户信息
            User user = new User();

            //set cookie
            //set cookie
            CookieContext cookieContext = new CookieContext();
            cookieContext.setId(user.getId().toString());
            cookieContext.setUsername(user.getUsername());
            cookieContext.setNickname(user.getUsername());
            CookieContext.setCookieContext(cookieContext);
            CookieContext.setCookie(request, response, domain, "id", user.getId().toString());
            CookieContext.setCookie(request, response, domain, "username", user.getUsername());
            CookieContext.setCookie(request, response, domain, "nickname", user.getUsername());
            //JSON.toJSONString(cookieContext) 可以加密下
            CookieContext.setCookie(request, response, domain, cookieName, JSON.toJSONString(cookieContext));

            //return
            result.setCode(0);
            result.setMessage("success");
            logger.info("|AdminAdministratorController|login|result|" + JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            result.setCode(-1);
            result.setMessage("fail");
            logger.error("AdminAdministratorController|login|" + e);
            return result;
        }
    }

    @SuppressWarnings(value = "unused")
    public String getCustomCookieName() {
        return cookieName;
    }

    @SuppressWarnings(value = "unused")
    public void setCustomCookieName(String customCookieName) {
        this.cookieName = customCookieName;
    }
}

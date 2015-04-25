package com.sunflower.petal.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CookieContext
 *
 * @author sunny
 * @version 1.0.0
 * @since 2014-11-11
 */
@SuppressWarnings(value = "unused")
public class CookieContext {

    /**
     * path
     */
    public static transient String path = "/";

    /**
     * 主键
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称，如果没有，设置成用户名
     */
    private String nickname;

    /**
     * 其他数据
     */
    private Object data;

    private static ThreadLocal<CookieContext> actionContext = new ThreadLocal<CookieContext>();

    public static void setCookieContext(CookieContext cookieContext) {
        actionContext.remove();
        actionContext.set(cookieContext);
    }

    public static CookieContext getCookieContext() {
        return actionContext.get();
    }

    public static void removeCookieContext() {
        actionContext.remove();
    }

    public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        String result = "";
        Cookie[] cookieArray = request.getCookies();
        if (cookieArray != null) {
            for (Cookie cookie : cookieArray) {
                if (cookie.getName().equals(name)) {
                    result = cookie.getValue();
                }
            }
        }
        return result;
    }

    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String domain, int maxAge, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
//        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 默认过期时间 2 小时
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String domain, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
//        cookie.setHttpOnly(true);
        cookie.setMaxAge(2 * 60 * 60 * 1000);
        response.addCookie(cookie);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}

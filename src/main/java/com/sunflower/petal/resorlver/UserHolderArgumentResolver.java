package com.sunflower.petal.resorlver;

import com.sunflower.petal.entity.UserHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jeasywebframework@gmail.com on 13-12-23.
 */
public class UserHolderArgumentResolver implements HandlerMethodArgumentResolver {


    private static final Log logger = LogFactory.getLog(UserHolderArgumentResolver.class);


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.getParameterType().equals(UserHolder.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        return (UserHolder) request.getAttribute(UserHolder.REQUEST_KEY_HOLDER);
    }


}

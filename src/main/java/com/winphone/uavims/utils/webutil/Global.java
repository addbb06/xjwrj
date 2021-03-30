package com.winphone.xjwrj.utils.webutil;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: zhou
 * @Description:
 * @Date:Create in 2018/04/19
 * @Modified By:
 */

public class Global {

    /**
     * 获取当前请求 request
     * @return
     */
    public static HttpServletRequest getHttpServletRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();
        return request;
    }
    /**
     * 获取当前请求session
     * @return
     */
    public static HttpSession getHttpSession(){
        return getHttpServletRequest().getSession();
    }
}

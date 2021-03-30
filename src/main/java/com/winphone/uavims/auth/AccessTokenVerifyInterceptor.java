package com.winphone.xjwrj.auth;

import com.winphone.xjwrj.auth.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: zhou
 * @Description: access-token 校验拦截器
 * @Date:Create in 2017/12/05
 * @Modified By:
 */
@Component
public class AccessTokenVerifyInterceptor extends HandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(AccessTokenVerifyInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (logger.isDebugEnabled()){
            logger.debug("AccessTokenVerifyInterceptor executing.......");
        }
        boolean flag = true;
        //accesstoken 参数
        String token = request.getHeader(JwtUtil.HEADER_STRING);
//        Enumeration headerNames = request.getHeaderNames();
        JwtUtil.validateToken(token);
        return flag;
    }
}

package com.winphone.xjwrj.config;

import com.winphone.xjwrj.auth.AccessTokenVerifyInterceptor;
import com.winphone.xjwrj.interceptor.AllInterceptor;
import com.winphone.xjwrj.interceptor.CommonInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author: zhou
 * @Description:
 * @Date:Create in 2017/12/17
 * @Modified By:
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private final static Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //增加跨域访问拦截器
        registry.addInterceptor(new CommonInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new AllInterceptor()).addPathPatterns("/**");
        // 注册token 验证拦截器
        registry.addInterceptor(new AccessTokenVerifyInterceptor())
                .addPathPatterns("/**")        // 配置拦截的路径

                .excludePathPatterns("/**/swagger*/**")
                .excludePathPatterns("/**/v2*/**")
                .excludePathPatterns("/**/configuration*/**")

                .excludePathPatterns("/genCaptcha")
                .excludePathPatterns("/file/getImage/**")
                .excludePathPatterns("/file/getQRCode/**")
                .excludePathPatterns("/file/upload/**")
                .excludePathPatterns("/flyer/appSave")
                .excludePathPatterns("/**/login");    // 配置不拦截的路径 (登录，注册，退出，找回密码等不需要拦截)
    }
}

package com.ctgu.j604.springbootchat.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loginInterceptor).addPathPatterns("/main").excludePathPatterns("/login","/register","/");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

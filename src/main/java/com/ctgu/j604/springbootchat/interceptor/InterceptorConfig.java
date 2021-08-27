package com.ctgu.j604.springbootchat.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor;
    @Resource
    LoggedController loggedController;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/main").excludePathPatterns("/login","/register","/");
        WebMvcConfigurer.super.addInterceptors(registry);
        List<String> pathPatterns = new LinkedList<>();
        pathPatterns.add("/");
        pathPatterns.add("/registerView");
        pathPatterns.add("/register");
        pathPatterns.add("/login");
        registry.addInterceptor(loggedController).addPathPatterns(pathPatterns);
    }

}

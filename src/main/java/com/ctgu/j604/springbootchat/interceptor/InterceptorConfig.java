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
        List<String> pathPatterns1 = new LinkedList<>();
        pathPatterns1.add("/ctgu");
        pathPatterns1.add("/main");
        pathPatterns1.add("/ctgu/auto-report");
        pathPatterns1.add("/ctgu/close-auto-report");
        registry.addInterceptor(loginInterceptor).addPathPatterns(pathPatterns1).excludePathPatterns("/login","/register","/");
        WebMvcConfigurer.super.addInterceptors(registry);
        List<String> pathPatterns = new LinkedList<>();
        pathPatterns.add("/");
        pathPatterns.add("/registerView");
        pathPatterns.add("/register");
        pathPatterns.add("/login");
        registry.addInterceptor(loggedController).addPathPatterns(pathPatterns);
    }

}

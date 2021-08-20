package com.ctgu.j604.springbootchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

//@Configuration
//public class UserIconConfigure implements WebMvcConfigurer
//{
//    public UserIconConfigure(){
//        System.out.println("配置类创建成功");
//    }
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry)
//    {
//        /** 上传路径 */
//

//
//    }
//}

@Configuration
public class UserIconConfigure implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File file = new File("/root/user-icon/example.jpg");
        if (file.exists()){
            registry.addResourceHandler("/img/user-icon/**").addResourceLocations("file:/root/user-icon/");
        }
        else{
            registry.addResourceHandler("/img/user-icon/**").addResourceLocations("file:C:/user-icon/");
        }
    }
}

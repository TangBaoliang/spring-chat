package com.ctgu.j604.springbootchat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ctgu.j604.springbootchat.mapper")
public class SpringbootChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootChatApplication.class, args);
    }

}

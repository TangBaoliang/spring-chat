package com.ctgu.j604.springbootchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author TangBaoLiang
 * @date 2022/7/7
 * @email developert163@163.com
 **/
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
public class SessionConfig {

}

package com.ctgu.j604.springbootchat.service.impl;

import com.ctgu.j604.springbootchat.service.VerifyService;
import com.ctgu.j604.springbootchat.utils.EmailUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class VerifyServiceImpl implements VerifyService {
    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    @Transactional
    public boolean sendEmailAndSaveVerifyCode(String verifyCode, String sessionId, String receiveEmail) {
        try {
            redisTemplate.opsForValue().set(sessionId,verifyCode,60, TimeUnit.MINUTES);
            return EmailUtils.sendMimeMessage(verifyCode,receiveEmail,sessionId);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkClick(String sessionId, String verifyCode) {
        String realVerifyCode = (String) redisTemplate.opsForValue().get(sessionId);
        if (realVerifyCode==null){
            return false;
        }

        if(verifyCode.equals(realVerifyCode)){
            redisTemplate.opsForValue().set(sessionId,"success",1,TimeUnit.DAYS);
            return true;
        }

        return false;
    }

}

package com.ctgu.j604.springbootchat.service;

public interface VerifyService {
    boolean sendEmailAndSaveVerifyCode(String verifyCode,String sessionId,String receiveEmail);
    boolean checkClick(String sessionId,String verifyCode);

}

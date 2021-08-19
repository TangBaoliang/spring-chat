package com.ctgu.j604.springbootchat.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

public class MessageUtils {
    public static String getMessage(Integer typeCode, String fromUserNum, Object message, Date sendTime){
        try{
            ResultMessage resultMessage = new ResultMessage();
            resultMessage.setMsgTypeCode(typeCode);
            resultMessage.setMessage(message);
            resultMessage.setSendTime(sendTime);
            if (fromUserNum != null){
                resultMessage.setFromUserNum(fromUserNum);
            }
            ObjectMapper mapper = new ObjectMapper();

            return mapper.writeValueAsString(resultMessage);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

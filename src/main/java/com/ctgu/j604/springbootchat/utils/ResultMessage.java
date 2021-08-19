package com.ctgu.j604.springbootchat.utils;

import java.util.Date;

public class ResultMessage {
    private Integer msgTypeCode;
    private String fromUserNum;
    private Object message;//如果是系统消息则是数组，否则是字符串
    private Date sendTime;

    public Integer getMsgTypeCode() {
        return msgTypeCode;
    }

    public void setMsgTypeCode(Integer msgTypeCode) {
        this.msgTypeCode = msgTypeCode;
    }

    public String getFromUserNum() {
        return fromUserNum;
    }

    public void setFromUserNum(String fromUserNum) {
        this.fromUserNum = fromUserNum;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}

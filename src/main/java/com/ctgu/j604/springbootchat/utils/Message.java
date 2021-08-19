package com.ctgu.j604.springbootchat.utils;


import java.util.Date;

public class Message {
    private Integer msgTypeCode;

    public Integer getMsgTypeCode() {
        return msgTypeCode;
    }

    public void setMsgTypeCode(Integer msgTypeCode) {
        this.msgTypeCode = msgTypeCode;
    }

    private String toUserNum;
    private String message;
    private Date sendTime;

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public void setToUserNum(String toUserNum) {
        this.toUserNum = toUserNum;
    }

    public String getToUserNum() {
        return toUserNum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

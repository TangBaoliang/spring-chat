package com.ctgu.j604.springbootchat.utils;

import java.util.Date;

public class ResultMessage {
    private Integer msgTypeCode;                        //消息类别码
    private String fromUserNum;                         //如果是一对一消息此字段为用户账号，如果是群消息此字段为群号码
    private String fromMemberNum;                       //如果此消息不是群消息则此字段为空
    private Object message;                             //如果是系统消息则是数组，否则是字符串
    private Date sendTime;                              //消息的发送时间，时间在前端就已经决定

    public String getFromMemberNum() {
        return fromMemberNum;
    }

    public void setFromMemberNum(String fromMemberNum) {
        this.fromMemberNum = fromMemberNum;
    }

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

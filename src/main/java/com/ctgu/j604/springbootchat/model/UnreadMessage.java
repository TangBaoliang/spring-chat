package com.ctgu.j604.springbootchat.model;

import java.io.Serializable;
import java.util.Date;

public class UnreadMessage implements Serializable {


    private Integer id;


    /**
     * 这个字段是消息接收人的账号，这里不能是群号
     */
    private String toUserNum;


    /**
     * 无效的冗余字段
     */
    private Integer toUserId;


    /**
     * 消息的发送人，如果是群消息则是群号
     */
    private String fromUserNum;


    /**
     * 此字段为冗余字段
     */
    private Integer fromUserId;


    /**
     * 消息内容
     */
    private String content;


    /**
     * 消息发送时间
     */
    private Date sendTime;


    /**
     * 消息类别码
     */
    private Integer msgTypeCode;


    /**
     * 发送群消息的群成员，如果不是群消息则此字段为空
     */
    private String fromMemberNum;

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getToUserNum() {
        return toUserNum;
    }

    public void setToUserNum(String toUserNum) {
        this.toUserNum = toUserNum == null ? null : toUserNum.trim();
    }


    public Integer getToUserId() {
        return toUserId;
    }


    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }


    public String getFromUserNum() {
        return fromUserNum;
    }


    public void setFromUserNum(String fromUserNum) {
        this.fromUserNum = fromUserNum == null ? null : fromUserNum.trim();
    }

    public Integer getFromUserId() {
        return fromUserId;
    }



    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }


    public Date getSendTime() {
        return sendTime;
    }


    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }


    public Integer getMsgTypeCode() {
        return msgTypeCode;
    }


    public void setMsgTypeCode(Integer msgTypeCode) {
        this.msgTypeCode = msgTypeCode;
    }


    public String getFromMemberNum() {
        return fromMemberNum;
    }


    public void setFromMemberNum(String fromMemberNum) {
        this.fromMemberNum = fromMemberNum == null ? null : fromMemberNum.trim();
    }
}
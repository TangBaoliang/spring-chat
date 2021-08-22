package com.ctgu.j604.springbootchat.service.impl;

import com.ctgu.j604.springbootchat.mapper.GroupAndUserMapper;
import com.ctgu.j604.springbootchat.mapper.UnreadMessageMapper;

import com.ctgu.j604.springbootchat.model.*;

import com.ctgu.j604.springbootchat.service.GroupService;
import com.ctgu.j604.springbootchat.service.MessageService;
import com.ctgu.j604.springbootchat.service.TUserService;
import com.ctgu.j604.springbootchat.utils.Message;
import com.ctgu.j604.springbootchat.utils.MessageUtils;
import com.ctgu.j604.springbootchat.websocket.ChatEndPoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class MessageServiceImpl implements MessageService {
    @Resource
    private TUserService tUserService;

    @Resource
    public UnreadMessageMapper unreadMessageMapper;

    @Resource
    public GroupService groupService;

    @Override
    public void sendAddFriendMessageOneToOne(ChatEndPoint chatEndPoint, Message message) {
        String fromUserNum = (String)chatEndPoint.getHttpSession().getAttribute("userNum");
        Integer fromUserId = (Integer) chatEndPoint.getHttpSession().getAttribute("curUserId");
        String toUserNum = message.getToUserNum();

        UnreadMessage unreadMessage = new UnreadMessage();
        unreadMessage.setSendTime(message.getSendTime());
        unreadMessage.setContent(message.getMessage());
        unreadMessage.setFromUserId(fromUserId);
        unreadMessage.setFromUserNum(fromUserNum);
        unreadMessage.setToUserNum(toUserNum);
        unreadMessage.setMsgTypeCode(5);
        unreadMessageMapper.insert(unreadMessage);

        ChatEndPoint objectChatEndPoint = ChatEndPoint.onlineUserPoint.get(toUserNum);
        if(objectChatEndPoint!=null){
            String resultMessage = MessageUtils.getMessage(5,fromUserNum,message.getMessage(),message.getSendTime(),null);
            try {
                objectChatEndPoint.getSession().getBasicRemote().sendText(resultMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void sendInviteMessage(TUser tUser, String[] toInviteNums, String groupNum, String groupName) {
        UnreadMessage unreadMessage = new UnreadMessage();
        unreadMessage.setMsgTypeCode(8);
        unreadMessage.setFromMemberNum(tUser.getUserNum());
        unreadMessage.setSendTime(new Date());
        unreadMessage.setContent(groupName);
        unreadMessage.setFromUserNum(groupNum);
        for (String toUserNum:
             toInviteNums) {
            unreadMessage.setToUserNum(toUserNum);
            unreadMessageMapper.insert(unreadMessage);
            ChatEndPoint objectChatEndPoint = ChatEndPoint.onlineUserPoint.get(toUserNum);
            if(objectChatEndPoint!=null){
                String resultMessage = MessageUtils.getMessage(8,groupNum,groupName,new Date(),tUser.getUserNum());
                try {
                    objectChatEndPoint.getSession().getBasicRemote().sendText(resultMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onlineBroadcastToFriends(ChatEndPoint chatEndPoint, Message message) {

    }

    @Override
    public void sendMessageOneToGroup(ChatEndPoint chatEndPoint, Message message) {
        //提取发送此群消息的成员的账号和id
        String fromUserNum = (String)chatEndPoint.getHttpSession().getAttribute("userNum");
        Integer fromUserId = (Integer) chatEndPoint.getHttpSession().getAttribute("curUserId");
        //提取消息隶属的群的群号
        String groupNum = message.getToUserNum();

        //获取发送此消息的群成员在群里的备注,用来封装到消息里面展示到前端
        String memberComment = groupService.getMemberCommentByNumAndUserId(groupNum,fromUserId);

        //获取发送此消息的成员的昵称，如果群备注为空则显示昵称
        if(memberComment==null || "".equals(memberComment)){
           memberComment = ((TUser)chatEndPoint.getHttpSession().getAttribute("curUser")).getNickName();
        }

        //如果用户没有昵称，就显示账号
        if(memberComment==null || "".equals(memberComment)){
            memberComment=fromUserNum;
        }

        //获取群内的所有成员的账号
        List<String> groupAndUserList = groupService.getAllUsersByGroupNum(groupNum);

        //分发群消息
        for(int i = 0; i < groupAndUserList.size(); i++){
            //获取群成员
            String toUserNum = groupAndUserList.get(i);
            //发送人不能接受到自己的消息
            if(toUserNum.equals(fromUserNum)) continue;

            /***********************start将消息封装存到未读消息，给每一个成员都加一条未读消息************************/
            UnreadMessage unreadMessage = new UnreadMessage();
            unreadMessage.setSendTime(message.getSendTime());

            ObjectMapper mapper = new ObjectMapper();
            String content = "";
            try {
                content = mapper.writeValueAsString(new String[] {message.getMessage(),memberComment});
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            unreadMessage.setContent(content);
            unreadMessage.setFromUserId(fromUserId);

            //将此消息的发送号码设为发起此消息的成员账号，以便于前端
            unreadMessage.setFromMemberNum(fromUserNum);

            //将发送方转化为群号，这样方便前端接收
            unreadMessage.setFromUserNum(groupNum);   //发送方群号

            //设置未读消息的接收人账号
            unreadMessage.setToUserNum(toUserNum);

            unreadMessage.setMsgTypeCode(4);

            //将此未读消息存到数据库，如果用户没有读，就不会删除，如果读了就会删除自己对应的那一条
            unreadMessageMapper.insert(unreadMessage);
            /***********************end将消息封装存到未读消息，给每一个成员都加一条未读消息************************/

            ChatEndPoint objectChatEndPoint = ChatEndPoint.onlineUserPoint.get(toUserNum);
            if(objectChatEndPoint != null){
                String resultMessage = MessageUtils.getMessage(4, groupNum, new String[] {message.getMessage(),memberComment}, message.getSendTime(), fromUserNum);
                try{
                    objectChatEndPoint.getSession().getBasicRemote().sendText(resultMessage);
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }





    }

    @Override
    public void sendMessageOneToOne(ChatEndPoint chatEndPoint, Message message) {
        String fromUserNum = (String)chatEndPoint.getHttpSession().getAttribute("userNum");
        Integer fromUserId = (Integer) chatEndPoint.getHttpSession().getAttribute("curUserId");
        String toUserNum = message.getToUserNum();

        UnreadMessage unreadMessage = new UnreadMessage();
        unreadMessage.setSendTime(message.getSendTime());
        unreadMessage.setContent(message.getMessage());
        unreadMessage.setFromUserId(fromUserId);
        unreadMessage.setFromUserNum(fromUserNum);
        unreadMessage.setToUserNum(toUserNum);
        unreadMessage.setMsgTypeCode(2);
        unreadMessageMapper.insert(unreadMessage);

        ChatEndPoint objectChatEndPoint = ChatEndPoint.onlineUserPoint.get(toUserNum);
        if(objectChatEndPoint!=null){
           String resultMessage = MessageUtils.getMessage(2,fromUserNum,message.getMessage(),message.getSendTime(),null);
            try {
                objectChatEndPoint.getSession().getBasicRemote().sendText(resultMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void sendUnreadMsgToUser(ChatEndPoint chatEndPoint) {
        String toUserNum = (String)chatEndPoint.getHttpSession().getAttribute("userNum");
        UnreadMessageExample unreadMessageExample = new UnreadMessageExample();
        unreadMessageExample.createCriteria().andToUserNumEqualTo(toUserNum);
        List<UnreadMessage> list = unreadMessageMapper.selectByExample(unreadMessageExample);
        if(list!=null && list.size()>0){
            for (UnreadMessage u:
                 list) {
                String msg = "";
                String[] messageBody = {};
                if(u.getMsgTypeCode()==4){//这里的4代表消息是群消息
                    String content = u.getContent();
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        messageBody = mapper.readValue(content,String[].class);
                        msg = MessageUtils.getMessage(u.getMsgTypeCode(),u.getFromUserNum(),messageBody,u.getSendTime(),u.getFromMemberNum());
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    msg = MessageUtils.getMessage(u.getMsgTypeCode(),u.getFromUserNum(),u.getContent(),u.getSendTime(),null);
                }

                try {
                    chatEndPoint.getSession().getBasicRemote().sendText(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    @Transactional
    public void acceptReadMessageConfirm(String toUserNum, String fromUserNum) {
        UnreadMessageExample unreadMessageExample = new UnreadMessageExample();
        unreadMessageExample.createCriteria().andToUserNumEqualTo(toUserNum).andFromUserNumEqualTo(fromUserNum);
        unreadMessageMapper.deleteByExample(unreadMessageExample);
    }
}

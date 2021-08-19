package com.ctgu.j604.springbootchat.service.impl;

import com.ctgu.j604.springbootchat.mapper.GroupAndUserMapper;
import com.ctgu.j604.springbootchat.mapper.UnreadMessageMapper;

import com.ctgu.j604.springbootchat.model.GroupAndUser;
import com.ctgu.j604.springbootchat.model.GroupAndUserExample;
import com.ctgu.j604.springbootchat.model.UnreadMessage;

import com.ctgu.j604.springbootchat.model.UnreadMessageExample;
import com.ctgu.j604.springbootchat.service.GroupService;
import com.ctgu.j604.springbootchat.service.MessageService;
import com.ctgu.j604.springbootchat.service.TUserService;
import com.ctgu.j604.springbootchat.utils.Message;
import com.ctgu.j604.springbootchat.utils.MessageUtils;
import com.ctgu.j604.springbootchat.websocket.ChatEndPoint;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
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
    public void onlineBroadcastToFriends(ChatEndPoint chatEndPoint, Message message) {

    }

    @Override
    public void sendMessageOneToGroup(ChatEndPoint chatEndPoint, Message message) {
        String fromUserNum = (String)chatEndPoint.getHttpSession().getAttribute("userNum");
        Integer fromUserId = (Integer) chatEndPoint.getHttpSession().getAttribute("curUserId");
        String groupNum = message.getToUserNum();

        //获取群内的所有成员
        List<String> groupAndUserList = groupService.getAllUsersByGroupNum(groupNum);
        for(int i = 0; i < groupAndUserList.size(); i++){
            //获取群成员
            String toUserNum = groupAndUserList.get(i);
            //发送人不能接受到自己的消息
            if(toUserNum.equals(fromUserNum)) continue;
            //设置未读消息
            UnreadMessage unreadMessage = new UnreadMessage();
            unreadMessage.setSendTime(message.getSendTime());
            unreadMessage.setContent(message.getMessage());
            unreadMessage.setFromUserId(fromUserId);
            unreadMessage.setFromMemberNum(fromUserNum);
            unreadMessage.setFromUserNum(groupNum);   //发送方群号
            unreadMessage.setToUserNum(toUserNum);
            unreadMessage.setMsgTypeCode(4);
            unreadMessageMapper.insert(unreadMessage);

            ChatEndPoint objectChatEndPoint = ChatEndPoint.onlineUserPoint.get(toUserNum);
            if(objectChatEndPoint != null){
                String resultMessage = MessageUtils.getMessage(4, groupNum, message.getMessage(), message.getSendTime(), fromUserNum);
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
                String msg = MessageUtils.getMessage(u.getMsgTypeCode(),u.getFromUserNum(),u.getContent(),u.getSendTime(),null);
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

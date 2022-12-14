package com.ctgu.j604.springbootchat.service.impl;

import com.ctgu.j604.springbootchat.mapper.*;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class MessageServiceImpl implements MessageService {
    @Resource
    private TUserService tUserService;

    @Resource
    private UnreadMessageMapper unreadMessageMapper;

    @Resource
    private LinkmanListMapper linkmanListMapper;

    @Resource
    private GroupService groupService;

    @Resource
    private TUserMapper tUserMapper;

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
    @Transactional
    public void sendAgreeAddFriendMessageOneToOne(ChatEndPoint chatEndPoint, Message message) {
        LinkmanList linkmanList1 = new LinkmanList();
        LinkmanList linkmanList2 = new LinkmanList();
        String fromUserNum = (String)chatEndPoint.getHttpSession().getAttribute("userNum");
        String fromNickName = (String)((TUser)chatEndPoint.getHttpSession().getAttribute("curUser")).getNickName();
        Integer fromUserId = (Integer) chatEndPoint.getHttpSession().getAttribute("curUserId");
        String toUserNum = message.getToUserNum();

        UnreadMessageExample unreadMessageExample = new UnreadMessageExample();
        UnreadMessageExample.Criteria criteria = unreadMessageExample.createCriteria();
        criteria.andToUserNumEqualTo(fromUserNum);
        criteria.andFromUserNumEqualTo(toUserNum);
        criteria.andMsgTypeCodeEqualTo(5);
        List<UnreadMessage> unreadMessageList = unreadMessageMapper.selectByExample(unreadMessageExample);
        Integer toUserId=null;
        if(unreadMessageList!=null && unreadMessageList.size()>0){
            toUserId = unreadMessageList.get(0).getFromUserId();
        }
        String toUserNickname = tUserMapper.selectByPrimaryKey(toUserId).getNickName();//?????????????????????????????????
        if(toUserId != null){
            unreadMessageMapper.deleteByExample(unreadMessageExample);

            linkmanList1.setUserId(fromUserId);
            linkmanList1.setFriendId(toUserId);
            linkmanList1.setAddTime(new Date());
            linkmanList1.setCommentForFriend(toUserNickname);
            linkmanListMapper.insertSelective(linkmanList1);

            linkmanList2.setUserId(toUserId);
            linkmanList2.setFriendId(fromUserId);
            linkmanList2.setAddTime(new Date());
            linkmanList2.setCommentForFriend(fromNickName);
            linkmanListMapper.insertSelective(linkmanList2);
        }
    }

    @Override
    public void sendRefuseAddFriendMessageOneToOne(ChatEndPoint chatEndPoint, Message message) {
        String fromUserNum = (String)chatEndPoint.getHttpSession().getAttribute("userNum");
        String toUserNum = message.getToUserNum();
        UnreadMessageExample unreadMessageExample = new UnreadMessageExample();
        UnreadMessageExample.Criteria criteria = unreadMessageExample.createCriteria();
        criteria.andToUserNumEqualTo(fromUserNum);
        criteria.andFromUserNumEqualTo(toUserNum);
        criteria.andMsgTypeCodeEqualTo(5);
        List<UnreadMessage> unreadMessageList = unreadMessageMapper.selectByExample(unreadMessageExample);
        unreadMessageMapper.deleteByExample(unreadMessageExample);
    }

    @Override
    @Transactional
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
    @Transactional
    public void sendMessageOneToGroup(ChatEndPoint chatEndPoint, Message message) {
        //?????????????????????????????????????????????id
        String fromUserNum = (String)chatEndPoint.getHttpSession().getAttribute("userNum");
        Integer fromUserId = (Integer) chatEndPoint.getHttpSession().getAttribute("curUserId");
        //?????????????????????????????????
        String groupNum = message.getToUserNum();

        //???????????????????????????????????????????????????,??????????????????????????????????????????
        String memberComment = groupService.getMemberCommentByNumAndUserId(groupNum,fromUserId);

        //??????????????????????????????????????????????????????????????????????????????
        if(memberComment==null || "".equals(memberComment)){
           memberComment = ((TUser)chatEndPoint.getHttpSession().getAttribute("curUser")).getNickName();
        }

        //??????????????????????????????????????????
        if(memberComment==null || "".equals(memberComment)){
            memberComment=fromUserNum;
        }

        //????????????????????????????????????
        List<String> groupAndUserList = groupService.getAllUsersByGroupNum(groupNum);

        //???????????????
        for(int i = 0; i < groupAndUserList.size(); i++){
            //???????????????
            String toUserNum = groupAndUserList.get(i);
            //???????????????????????????????????????
            if(toUserNum.equals(fromUserNum)) continue;

            /***********************start??????????????????????????????????????????????????????????????????????????????************************/
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

            //?????????????????????????????????????????????????????????????????????????????????
            unreadMessage.setFromMemberNum(fromUserNum);

            //??????????????????????????????????????????????????????
            unreadMessage.setFromUserNum(groupNum);   //???????????????

            //????????????????????????????????????
            unreadMessage.setToUserNum(toUserNum);

            unreadMessage.setMsgTypeCode(4);

            //??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
            unreadMessageMapper.insert(unreadMessage);
            /***********************end??????????????????????????????????????????????????????????????????????????????************************/

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
                if(u.getMsgTypeCode()==4){//?????????4????????????????????????
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
                    msg = MessageUtils.getMessage(u.getMsgTypeCode(),u.getFromUserNum(),u.getContent(),u.getSendTime(),u.getFromMemberNum());
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

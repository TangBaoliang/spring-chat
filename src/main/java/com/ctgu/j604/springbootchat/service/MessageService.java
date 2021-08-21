package com.ctgu.j604.springbootchat.service;

import com.ctgu.j604.springbootchat.model.TUser;
import com.ctgu.j604.springbootchat.utils.Message;
import com.ctgu.j604.springbootchat.websocket.ChatEndPoint;

public interface MessageService {
    void onlineBroadcastToFriends(ChatEndPoint chatEndPoint, Message message);
    void sendMessageOneToOne(ChatEndPoint chatEndPoint, Message message);
    void sendUnreadMsgToUser(ChatEndPoint chatEndPoint);
    void acceptReadMessageConfirm(String toUserNum, String fromUserNum);
    void sendMessageOneToGroup(ChatEndPoint chatEndPoint, Message message);
    void sendAddFriendMessageOneToOne(ChatEndPoint chatEndPoint, Message message);
    void sendInviteMessage(TUser tUser, String[] toInviteNums,String groupNum,String groupName);
}

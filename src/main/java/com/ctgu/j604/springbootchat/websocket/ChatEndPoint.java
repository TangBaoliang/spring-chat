package com.ctgu.j604.springbootchat.websocket;

import com.ctgu.j604.springbootchat.SpringbootChatApplication;
import com.ctgu.j604.springbootchat.service.MessageService;
import com.ctgu.j604.springbootchat.service.impl.MessageServiceImpl;
import com.ctgu.j604.springbootchat.utils.Message;
import com.ctgu.j604.springbootchat.utils.MessageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 每个客户端都有一个对应的该对象
 * 使用session就可以获取数据发送对象
 * 该类对象有很多个，但是容器只需要有一个
 */
@ServerEndpoint(value = "/chat",configurator = GetHttpSessionConfigurator.class)//注意这里要声明配置器，不然取不到Httpsession
@Component
public class ChatEndPoint {

    public static MessageService messageService;

    @Autowired public void setChatService(MessageService messageService) {
        ChatEndPoint.messageService = messageService;
    }

    //用来存储每一个客户端对象对应的ChatEndPoint对象,一般通过能唯一表示客户的字段做键名
    //ConcurrentHashMap主要是为了解决线程安全问题，其他与普通的HashMap没有什么区别
    public static Map<String, ChatEndPoint> onlineUserPoint = new ConcurrentHashMap<>();

    //声明session对象，通过该对象可以发送消息给指定的用户
    private Session session;

    //声明一个HttpSession对象，我们之前在HttpSession对象中存储了用户名
    private HttpSession httpSession;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }



    //连接建立时执行
    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig){
        /************************连接初始化*********************/
        //将局部的session对象负值给成员session
        this.session = session;
        this.httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        //从HttpSession中获取用户名，作为不同ChatEndpoint对象的键,这里我用的是账号
        String userChatNum = (String) httpSession.getAttribute("userNum");
        //将当前对象存起来，因为每一个客户端都有一个对应的对象，存起来是为了下次通信的时候要找到它
        onlineUserPoint.put(userChatNum,this);
        /************************初始化完毕**********************/


        /************************用户上线要推送的服务*******************/

        //1.推送未读消息
        messageService.sendUnreadMsgToUser(this);


        /************************用户上线要推送的服务*******************/
    }


    //这里使用的Set集合特点是容器内不会出现重复的集合



    //接受到客户端发送的数据时被调用
    @OnMessage
    public void onMessage(String message, Session session){
        try{
            /*******************将JSON字符串消息转化成可读取的对象********************/
            ObjectMapper mapper = new ObjectMapper();
            Message message1 = mapper.readValue(message,Message.class);


            //取出消息类别，判断进行对应的处理
            Integer msgTypeCode = message1.getMsgTypeCode();
            switch (msgTypeCode){
                case 2:{
                    messageService.sendMessageOneToOne(this,message1);
                }break;
                case 3:{
                    messageService.acceptReadMessageConfirm((String)this.httpSession.getAttribute("userNum"),message1.getToUserNum());
                }break;
                case 4:{
                    messageService.sendMessageOneToGroup(this,message1);
                }break;
            }


//            String resultMessage = MessageUtils.getMessage(msgTypeCode,curUserNum,content,sendTime);

            //发送
//            ChatEndPoint objectChatEndPoint = onlineUserPoint.get(toUserNum);
//            if(objectChatEndPoint!=null) {
//                objectChatEndPoint.session.getBasicRemote().sendText(resultMessage);
//            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //连接关闭时被调用
    @OnClose
    public void onClose(){
        //将此websocket连接点从集合中移除
        onlineUserPoint.remove(httpSession.getAttribute("userNum"));
    }


//    /**
//     * 将消息推送给所有的客户端
//     * @param message
//     */
//    private void broadcastToFriends(String message){
//        Set<String> userNums = onlineUserPoint.keySet();
//        for (String userNum:
//             userNums) {
//            ChatEndPoint chatEndPoint = onlineUserPoint.get(userNum);
//            RemoteEndpoint.Basic basicRemote = chatEndPoint.session.getBasicRemote();
//
//            if(!userNum.equals(httpSession.getAttribute("userNum"))){
//                try {
//                    basicRemote.sendText(message);//将消息发送到客户端
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//    }
}

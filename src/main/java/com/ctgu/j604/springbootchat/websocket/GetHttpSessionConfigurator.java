package com.ctgu.j604.springbootchat.websocket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        HttpSession httpSession = (HttpSession) request.getHttpSession();

        //将HttpSession对象存储存储到配置对象中
        //这里的键只要能标识即可，用类的字节码名称标识
        //其实sec就是连接建立时的形参EndpointConfig endpointConfig，是配置对象，具体怎么联系起来我并不是很清楚
        // public void onOpen(Session session, EndpointConfig endpointConfig)在ChatEndpoint类里面,
        //在这里把HttpSession对象存到配置对象，然后在OnOpen方法中就可以直接取到
        sec.getUserProperties().put(HttpSession.class.getName(),httpSession);

    }
}

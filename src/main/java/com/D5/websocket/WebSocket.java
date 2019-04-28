package com.D5.websocket;


import com.D5.domain.Data;
import com.D5.service.impl.DataService;
import org.json.JSONObject;
import org.springframework.web.context.ContextLoader;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * WebSocket类,用于实时图表获取最新数据
 */
@ServerEndpoint("/websocket")
public class WebSocket {
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();
    private Session session;
    private DataService service = (DataService) ContextLoader.getCurrentWebApplicationContext().getBean("service");

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
    }

    @OnMessage
    public void onMessage(String message) {
        Data last = service.selectOneData();//获取最新一条数据
        JSONObject jsonObject = new JSONObject(last);//将数据放进json中
        String json = jsonObject.toString();
        send(json);//发送最新的一条数据
    }

    private void send(String message) {
        this.session.getAsyncRemote().sendText(message);
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
    }
}

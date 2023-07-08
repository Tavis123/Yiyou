package com.work.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.config.WebSocketConfig;
import com.work.mapper.ChatMapper;
import com.work.pojo.ChatMessage;
import com.work.pojo.User;
import com.work.service.ChatService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint(value = "/websocket",configurator= WebSocketConfig.class)
@Component
@SuppressWarnings("all")
public class WebSocketController {

    //�������ÿ���ͻ��˶�Ӧ��WebSocketController����
    private static CopyOnWriteArraySet<WebSocketController> webSocketSet = new CopyOnWriteArraySet<WebSocketController>();
    //������¼username�͸�session���а�
    private static Map<String, Session> map = new HashMap<String, Session>();
    //��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
    private Session session;
    //�û���
    private String username;
    //��ȡȫ������
    private ApplicationContext applicationContext;
    //�����߼���service
    private ChatService chatService;
    //�������ݲ�mapper
    private ChatMapper chatMapper;


    /**
     * ���ӽ����ɹ����õķ�������ʼ���ǳơ�session
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {

        //��ȡ��¼ʱ���httpSession���û�����
        HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(httpSession.getServletContext());

        User user = (User) httpSession.getAttribute("user");

        //��ʼ������
        this.applicationContext = applicationContext;
        this.session = session;
        this.username = user.getUsername();
        this.chatService = (ChatService) applicationContext.getBean("chatService");
        this.chatMapper = (ChatMapper) applicationContext.getBean("chatMapper");

        //��username��session
        map.put(username, session);

        //����set��
        webSocketSet.add(this);

    }

    /**
     * ���ӹرյ��õķ���
     */
    @OnClose
    public void onClose() {

        //����ǰ��sessionɾ��
        webSocketSet.remove(this);
    }

    /**
     * �յ��ͻ�����Ϣ����õķ���
     *
     * @param message �ͻ��˷��͹�������Ϣ
     */
    @OnMessage
    public void onMessage(String message) {

        //�ӿͻ��˴�������������json���ݣ���������ʹ��jackson����ת��ΪchatMsg����
        ObjectMapper objectMapper = new ObjectMapper();
        ChatMessage chatMsg;

        try {
            chatMsg = objectMapper.readValue(message, ChatMessage.class);

            //��chatMsg����װ��
            chatMsg.setFromUser(username);
            chatMsg.setSendTime(new Date());
            chatMsg.setLatest(true);

            Session fromSession = map.get(chatMsg.getFromUser());
            Session toSession = map.get(chatMsg.getToUser());


            //����һ��map����װֱ�ӷ�����Ϣ���ݷ���ǰ��
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("sendUser", username);
            resultMap.put("content", chatMsg.getContent());
            resultMap.put("sendTime", chatMsg.getSendTime());

            JSONObject json = new JSONObject(resultMap);

            //���͸�������.
            fromSession.getAsyncRemote().sendText(json.toString());

            // 1.�жϽ��շ���toSession�Ƿ�Ϊnull
            // 2.�ж�������ҳ�� ==> ֱ�ӷ��� �������Ǵ洢�����ݿ���
            int flag = chatMapper.selectIsSaveWindows(chatMsg.getLinkId(), chatMsg.getFromUser(), chatMsg.getToUser());

            if (toSession != null && toSession.isOpen()) {
                //���͸�������.
                toSession.getAsyncRemote().sendText(json.toString());
            }

            //���������¼��Ϣ
            chatService.saveMessage(chatMsg);

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * ��������ʱ����
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * Ⱥ���Զ�����Ϣ
     */
    public void broadcast(String message) {
        for (WebSocketController item : webSocketSet) {
            //�첽������Ϣ.
            item.session.getAsyncRemote().sendText(message);
        }
    }
}
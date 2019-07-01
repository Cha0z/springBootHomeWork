package com.homework.home.web.controller;


import com.homework.home.models.Message;
import com.homework.home.web.encoder.MessageDecoder;
import com.homework.home.web.encoder.MessageEncoder;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "socket/{user}", decoders = MessageDecoder.class, encoders = MessageEncoder.class
)
public class SocketEndPoint {


    private Session session;
    private static Set<SocketEndPoint> chatEndpoints
            = new CopyOnWriteArraySet<>();

    private static HashMap<String, String> users = new HashMap<>();

    @OnOpen
    public void onOpen(
            Session session,
            @PathParam("user") String username) throws IOException {

        this.session = session;
        chatEndpoints.add(this);
        users.put(session.getId(), username);

        Message message = new Message();
        message.setFrom(username);
        message.setContent("Connected!");
        broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, Message message)
            throws IOException {

        message.setFrom(users.get(session.getId()));
        broadcast(message);
    }

    @OnClose
    public void onClose(Session session) throws IOException {

        chatEndpoints.remove(this);
        Message message = new Message();
        message.setFrom(users.get(session.getId()));
        message.setContent("Disconnected!");
        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    private static void broadcast(Message message) {

        chatEndpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote().
                            sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}



package com.example.dockerdemo.websocket;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

//@ServerEndpoint(value = "/websocket")
public class WebSocketController {
    private Session session;

//    @OnOpen
    public void onOpen(Session session) throws Exception {
        this.session = session;
        // TODO
    }
}

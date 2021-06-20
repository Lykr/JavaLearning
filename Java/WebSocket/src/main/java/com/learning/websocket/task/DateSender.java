package com.learning.websocket.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/ws/date")
public class DateSender {
    private static Logger log = LoggerFactory.getLogger(DateSender.class);
    private static ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();

    @Scheduled(fixedRate = 1000)
    public void sendDate() {
        log.info("Send current date to the clients");
        for (Map.Entry<String, Session> entry : sessionMap.entrySet()) {
            Session session = entry.getValue();
            log.info("Send to session: {}", session.getId());
            session.getAsyncRemote().sendText("Current date: " + new Date());
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        String sessionId = session.getId();
        log.info("New session established, whose ID is {}", sessionId);
        sessionMap.put(sessionId, session);
    }

    @OnClose
    public void onClose(Session session) {
        String sessionId = session.getId();
        log.info("A session close, whose ID is {}", sessionId);
        sessionMap.remove(sessionId);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("Get message \"{}\" from session {}", message, session.getId());
        try {
            session.getBasicRemote().sendText("Echo: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("Error");
        sessionMap.remove(session.getId());
        error.printStackTrace();
    }
}

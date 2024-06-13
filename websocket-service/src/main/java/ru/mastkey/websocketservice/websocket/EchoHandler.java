package ru.mastkey.websocketservice.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Slf4j
public class EchoHandler extends TextWebSocketHandler {

    private final WebSocketSession clientSession;

    public EchoHandler(WebSocketSession clientSession) {
        this.clientSession = clientSession;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connection established with echo server");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("Received message: {}", message.getPayload());
        if (clientSession != null && clientSession.isOpen()) {
            try {
                clientSession.sendMessage(new TextMessage(message.getPayload()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

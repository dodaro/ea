package it.unical.inf.ea.auth.config.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;


import java.io.IOException;
import java.util.Map;

import static org.springframework.web.socket.CloseStatus.SERVER_ERROR;

@RequiredArgsConstructor
public class CustomWebSocketHandler extends AbstractWebSocketHandler {

    private final Map<String, WebSocketSession> sessions;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        var principal = session.getPrincipal();

        if (principal == null || principal.getName() == null) {
            session.close(SERVER_ERROR.withReason("User must be authenticated"));
            return;
        }

        sessions.put(principal.getName(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        var principal = session.getPrincipal();
        sessions.remove(principal.getName());
    }

}
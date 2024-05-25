package org.unical.demacs.realtimechat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.unical.demacs.realtimechat.utils.ChatMessage;


@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChatController {

    private static Logger logger = LoggerFactory.getLogger(ChatController.class);
    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage sendMessage(@RequestBody ChatMessage chatMessage, @DestinationVariable String roomId) {
        logger.info("*********START REQUEST WEB SOCKET*********");
        logger.info("roomId: " + roomId + " utente: "+chatMessage.getUser() + " messaggio: " + chatMessage.getMessage());
        logger.info("*********END REQUEST WEB SOCKET*********");
        return chatMessage;
    }
}


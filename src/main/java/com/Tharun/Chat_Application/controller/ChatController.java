package com.Tharun.Chat_Application.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class ChatController {

    // Thread-safe set to maintain active users
    private final Set<String> activeUsers = Collections.newSetFromMap(new ConcurrentHashMap<>());

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/sendMessage") // Endpoint: app/sendMessage
    @SendTo("/topic/messages") // Destination for subscribers
    public ChatMessage chatMessage(ChatMessage message) {
        return message;
    }

    @MessageMapping("/addUser") // Endpoint: app/addUser
    public void addUser(UserAction userAction) {
        if (userAction.getType().equals("JOIN")) {
            activeUsers.add(userAction.getUsername());
        } else if (userAction.getType().equals("LEAVE")) {
            activeUsers.remove(userAction.getUsername());
        }

        broadcastActiveUsers();
    }

    private void broadcastActiveUsers() {
        messagingTemplate.convertAndSend("/topic/users", new ActiveUsersResponse(activeUsers));
    }
}

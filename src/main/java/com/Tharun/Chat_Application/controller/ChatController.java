package com.Tharun.Chat_Application.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

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

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage chatMessage(ChatMessage message) {
        return message;
    }

    @MessageMapping("/addUser")
    public void addUser(UserAction userAction) {
        if (userAction.getType().equals("JOIN")) {
            activeUsers.add(userAction.getUsername());
        } else if (userAction.getType().equals("LEAVE")) {
            activeUsers.remove(userAction.getUsername());
        }

        // Broadcast updated list of active users to all clients
        broadcastActiveUsers();
    }

    private void broadcastActiveUsers() {
        messagingTemplate.convertAndSend("/topic/users", new ActiveUsersResponse(activeUsers));
    }
}
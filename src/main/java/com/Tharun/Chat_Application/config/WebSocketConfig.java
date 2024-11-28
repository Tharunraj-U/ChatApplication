package com.Tharun.Chat_Application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig  implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat") //this end point is for creating the webSocket object in frontend eg:var socket =new SocketJs("http://localhost:8080/chat");
                .setAllowedOrigins("http://localhost:63343/","http://localhost:63342/").withSockJS();

    }

    @Override
   public void configureMessageBroker(MessageBrokerRegistry registry) {
        // to subscribe the Stomplicent
        //  stompClient.subscribe('/topic/messages', function (messageOutput) {
        //            // Parse the incoming message
        //            var message = JSON.parse(messageOutput.body);
        registry.enableSimpleBroker("/topic");  // Server to Client
        // stompClient.send("/app/sendMessage", {}, JSON.stringify(chatMessage));
        registry.setApplicationDestinationPrefixes("/app"); // Client to Server

    }
}

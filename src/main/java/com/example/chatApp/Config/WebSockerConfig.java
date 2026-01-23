package com.example.chatApp.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;


//  Configuration file for Websocket
@Configuration
@EnableWebSocketMessageBroker
public class WebSockerConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        //  Registry endpoint for web client
        registry.addEndpoint("/ws-web")
                .setAllowedOriginPatterns("*")
                .withSockJS();

        // Registry endpoint for android client
        registry.addEndpoint("/ws-android")
                .setAllowedOriginPatterns("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        //  Path to which server broadcasts messages
        registry.enableSimpleBroker("/topic");

        // Path to which the client sends the messages to the server
        registry.setApplicationDestinationPrefixes("/app");

    }
}

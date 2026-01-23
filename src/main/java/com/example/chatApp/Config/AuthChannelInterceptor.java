package com.example.chatApp.Config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthChannelInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String authHeader = accessor.getFirstNativeHeader("Authorization");
            if (isValidToken(authHeader)) {
                String username = extractUsername(authHeader); // This is still a mock function
                UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList()); // Creates an authentication object
                accessor.setUser(user); //  Authenticates user for this session
            }
            else {
                // Rejecting the connection in case of invalid token
                throw new IllegalArgumentException("Invalid token");
            }
        }

        return message;
    }

    // Function to validate the JWT token
    private boolean isValidToken(String token) {
        //  Need to be updated with proper JWT authorization
        return token != null && token.startsWith("Bearer ");
    }

    // Function to extract username from the JWT token
    private String extractUsername(String token) {
        return token.substring(7);  //  Currently removes the Bearer and returns the remaining string
    }

}

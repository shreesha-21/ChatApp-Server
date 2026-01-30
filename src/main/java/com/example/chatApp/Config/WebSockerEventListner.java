package com.example.chatApp.Config;

import com.example.chatApp.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

//  Event listener to handle user leaving abruptly
@Component
@RequiredArgsConstructor
@Slf4j
public class WebSockerEventListner {

    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("username");
        String roomId = (String) headerAccessor.getSessionAttributes().get("room_id");

        if (username != null) {
            log.info("{} disconnected from {}!", username, roomId);

            ChatMessage chatMessage = ChatMessage
                    .builder()
                    .type(ChatMessage.MessageType.LEAVE)
                    .sender(username)
                    .build();

            messagingTemplate.convertAndSend("/topic/" + roomId, chatMessage);
        }

    }

}

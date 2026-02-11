package com.example.chatApp.controller;

import com.example.chatApp.model.ChatMessage;
import com.example.chatApp.repository.ChatMessageRepository;
import com.example.chatApp.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Objects;

//  Controller to handle messaging and adding new user in the app
@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage) {

        chatMessage.setTimeStamp(new Date());
        chatService.save(chatMessage);

        if (chatMessage.isGroup()) {

            // Sending the message to topic in case of group chats
            messagingTemplate.convertAndSend(
                    "/topic/" + chatMessage.getRecipient(),
                    chatMessage
            );

            //  logs sending message to a room
            log.debug("Sending the message to {} group", chatMessage.getRecipient());

        }
        else {

            // sending the message to the recipient
            messagingTemplate.convertAndSendToUser(
                    chatMessage.getRecipient(),
                    "/queue/private",
                    chatMessage
            );

            // sending the message back to the user
            messagingTemplate.convertAndSendToUser(
                    chatMessage.getRecipient(),
                    "/queue/private",
                    chatMessage
            );

            // logs message sent privately to a user
            log.debug("Sending the message to {} privately", chatMessage.getRecipient());
        }
    }

}

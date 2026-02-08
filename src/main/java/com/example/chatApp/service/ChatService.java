package com.example.chatApp.service;

import com.example.chatApp.model.ChatMessage;
import com.example.chatApp.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// Service which handles chat services
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    public void save(ChatMessage chatMessage) {
        String chatId = getChatId(chatMessage.getSender(), chatMessage.getRecipient(), chatMessage.isGroup());
        chatMessage.setChatId(chatId);
        chatMessageRepository.save(chatMessage);
    }

    // generates unique chat id for the chats
    private String getChatId(String sender, String recipient, boolean isGroup) {
        if (isGroup) {
           return recipient + "_group";
        }
        else {
            if (sender.compareTo(recipient) < 0) {
                return sender + "_" + recipient;
            } else {
                return recipient + "_" + sender;
            }
        }
    }

}

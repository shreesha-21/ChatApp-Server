package com.example.chatApp.repository;

import com.example.chatApp.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Interface to manage the database
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    // Find chat history for a specific room
    List<ChatMessage> findByRoomId(String roomId);

    // Find chat history between sender and recipient
    List<ChatMessage> findBySenderAndRecipient(
            String sender, String recipient, String sender2, String recipient2
    );

}

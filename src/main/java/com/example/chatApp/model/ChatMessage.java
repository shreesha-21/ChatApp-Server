package com.example.chatApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

//  Model for the message in chat
@Entity // Makes this a db table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String recipient;   // For one on one chats
    private String sender;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    private String roomId; // Null for a private message
    private Date timeStamp;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}

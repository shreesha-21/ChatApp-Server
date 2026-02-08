package com.example.chatApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users") // Good practice to pluralize table names
public class User {

    @Id
    private String username;

    private String fullName;

    private String password;

    private Status status;

    public enum Status {
        ONLINE, OFFLINE
    }
}
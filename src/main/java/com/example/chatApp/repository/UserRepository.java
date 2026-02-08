package com.example.chatApp.repository;

import com.example.chatApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    // Find all users who are currently online (for a "Who's Online" list)
    List<User> findAllByStatus(User.Status status);
}
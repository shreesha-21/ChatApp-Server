package com.example.chatApp.controller;

import com.example.chatApp.model.User;
import com.example.chatApp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

// handles the authentication of the user
@RestController
public class AuthController {

    private UserService userService;

    @PostMapping("/api/login")
    public ResponseEntity<LoginResponse> login(@RequestBody User loginRequest) {
        try {
            User user = userService.loginOrRegister(loginRequest.getUsername(), loginRequest.getPassword());

            // TODO: Implement JWT generator here
            String token = "Bearer: " + user.getUsername();

            LoginResponse response = new LoginResponse(token, user);
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return ResponseEntity.status(401).build(); // 401 Unauthorized
        }
    }

    // Simple DTO for response
    public record LoginResponse(String token, User user) {}
}

package com.example.chatApp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class AuthController {

    @PostMapping("/api/guest-login")
    public Map<String, String> guestLogin(@RequestParam String username) {
        // In a real app, you would check a password here.
        // For now, just generate the token immediately.

        String mockToken = "Bearer " + username; // username is used  as mock token for now

        return Collections.singletonMap("token", mockToken);
    }
}
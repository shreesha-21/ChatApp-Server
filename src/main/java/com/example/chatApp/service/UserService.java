package com.example.chatApp.service;

import com.example.chatApp.model.User;
import com.example.chatApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void disconnect(User user) {
        var storedUser = userRepository.findById(user.getUsername()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(User.Status.OFFLINE);
            userRepository.save(storedUser);
        }
    }

    public User connect(User user) {
        var storedUser = userRepository.findById(user.getUsername()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(User.Status.ONLINE);
            return userRepository.save(storedUser);
        }
        return null;
    }

    // Attempt to login or register
    public User loginOrRegister(String username, String password) {
        // Checking for username in the database
        User existingUser = userRepository.findById(username).orElse(null);

        if (existingUser != null) {
            // Validating the password
            if (!existingUser.getPassword().equals(password)) {
                throw new RuntimeException("Invalid Password");
            }
            existingUser.setStatus(User.Status.ONLINE);
            return userRepository.save(existingUser);
        } else {
            // Creating a new user
            var newUser = User.builder()
                    .username(username)
                    .password(password)  // TODO: Hash this password
                    .status(User.Status.ONLINE)
                    .build();
            return userRepository.save(newUser);
        }
    }
}
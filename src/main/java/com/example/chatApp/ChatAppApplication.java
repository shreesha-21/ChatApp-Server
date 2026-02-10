package com.example.chatApp;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class ChatAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatAppApplication.class, args);
	}

    @PostConstruct
    public void init() {
        // Forces the app to use the modern timezone standard
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        System.out.println("Timezone set to UTC");
    }
}

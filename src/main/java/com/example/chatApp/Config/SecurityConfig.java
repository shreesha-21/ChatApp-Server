package com.example.chatApp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

//  this class handles Security configuration
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)   // Disables csrf for websocket
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/ws-android/**", "/ws-web/**").permitAll()    //  Allows handshake
                    .anyRequest().authenticated()   //  Secures everything else
            );
        return http.build();
    }

}

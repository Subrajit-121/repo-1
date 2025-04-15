package com.example.recipe_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    @Value("${spring.security.user.roles}")
    private String roles;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Updated syntax to disable CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/recipes/**").authenticated() // Protect /recipes endpoints
                        .anyRequest().permitAll() // Allow other requests
                )
                .httpBasic(httpBasic -> {
                }); // Use Basic Authentication (modern syntax)

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Create in-memory user with values from application.properties
        UserDetails user = User
                .withUsername(username)
                .password("{noop}" + password) // {noop} for testing
                .roles(roles.split(","))
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}


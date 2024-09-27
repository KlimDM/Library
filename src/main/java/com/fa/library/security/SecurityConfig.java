package com.fa.library.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    //TODO
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.build();
//    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        List<UserDetails> usersList = new ArrayList<>();
        usersList.add(new User(
                "admin", encoder.encode("admin"),
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN")))
        );
        usersList.add(new User(
                "user", encoder.encode("user"),
                List.of(new SimpleGrantedAuthority("ROLE_USER")))
        );
        return new InMemoryUserDetailsManager(usersList);
    }
}

package com.libcode.plant.plant;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class AuthenticationHandler {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .successHandler((request, response, authentication) -> {
                    var authorities = authentication.getAuthorities().toString();

                    if (authorities.contains("ROLE_admin")) {
                        response.sendRedirect("/login/admin");
                    } else if (authorities.contains("ROLE_tutor")) {
                        response.sendRedirect("/login/tutor");
                    } else {
                        response.sendRedirect("/");
                    }
                })
            );

        return http.build();
    }

}

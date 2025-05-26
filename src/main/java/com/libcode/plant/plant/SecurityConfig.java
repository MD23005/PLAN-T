package com.libcode.plant.plant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index", "/login/**", "/css/**", "/js/**","/img/**").permitAll() // 👈 Acceso libre
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

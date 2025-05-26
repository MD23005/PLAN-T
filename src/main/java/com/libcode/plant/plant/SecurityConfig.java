package com.libcode.plant.plant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//import static org.springframework.security.config.Customizer.withDefaults;

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
                .loginPage("/oauth2/authorization/auth0")
                .defaultSuccessUrl("/", true)
            )
            
            .logout(logout -> logout
                .logoutSuccessUrl("https://dev-wc3lul6afa5rcq0p.us.auth0.com/v2/logout?client_id=FvGwy7Qw9bPiTFSbHGI3IfEwJQVzX8n8&returnTo=https://plan-t-production.onrender.com/")
            );
        return http.build();
    }
}

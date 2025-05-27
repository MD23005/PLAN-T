package com.libcode.plant.plant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomSuccessHandler successHandler;

    public SecurityConfig(CustomSuccessHandler successHandler) {
        this.successHandler = successHandler;
        
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index", "/login/**", "/css/**", "/js/**","/img/**").permitAll() // 👈 Acceso libre
                .anyRequest().authenticated()
            )
            
          
        // Habilita login OAuth2
        .oauth2Login(oauth2 -> oauth2
                .loginPage("/oauth2/authorization/auth0")
                .userInfoEndpoint(userInfo -> userInfo
                    .oidcUserService(new CustomOidcUserService()) // 👈 Agregás esto
                    )
                .successHandler(successHandler) // ✅ Usamos el handler
            )

        .logout(logout -> logout
                .logoutSuccessUrl("https://dev-qi8b5nrabbvawh0y.us.auth0.com/v2/logout?client_id=sbj7qQLLPsUFrNliyCs6ZmDZ8olbDP99&returnTo=http://localhost:8080/")
            );

        return http.build();
    }
}

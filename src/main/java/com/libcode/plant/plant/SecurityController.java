package com.libcode.plant.plant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityController {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index", "/login/**", "/css/**", "/js/**","/img/**").permitAll() // 👈 Acceso libre
                .anyRequest().authenticated()
            )
            
             .oauth2Login(withDefaults());
            /*.formLogin(form -> form
                .loginPage("/login") // Opcional: tu vista personalizada
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            );*/

        return http.build();
    }
}

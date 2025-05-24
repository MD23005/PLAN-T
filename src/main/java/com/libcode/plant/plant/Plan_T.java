package com.libcode.plant.plant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class Plan_T {

	public static void main(String[] args) {
		SpringApplication.run(Plan_T.class, args);
	}

}

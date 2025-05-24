package com.libcode.crud.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.libcode.crud.crud.config.EnvLoader;

@SpringBootApplication
public class CrudApplication {
	public static void main(String[] args) {
	    EnvLoader.loadEnv();
		SpringApplication.run(CrudApplication.class, args);
	}

}

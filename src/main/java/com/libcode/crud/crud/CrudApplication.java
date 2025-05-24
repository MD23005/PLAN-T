package com.libcode.crud.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

<<<<<<< HEAD
=======
import com.libcode.crud.crud.config.EnvLoader;

>>>>>>> c54c1dc (Variables de entorno)
@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
<<<<<<< HEAD
=======
		EnvLoader.loadEnv();
>>>>>>> c54c1dc (Variables de entorno)
		SpringApplication.run(CrudApplication.class, args);
	}

}

package com.restapi.usuarioapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.restapi.usuarioapi.repository")
public class UsuarioapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioapiApplication.class, args);
	}

}

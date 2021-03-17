package com.az.jwt.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class AzJwtExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzJwtExampleApplication.class, args);
	}

}

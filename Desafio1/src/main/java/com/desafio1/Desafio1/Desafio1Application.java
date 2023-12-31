package com.desafio1.Desafio1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "DesafioCieloBackend", version = "1", description = "API desenvolvida para o desafio Cielo Backend"))
public class Desafio1Application {

	public static void main(String[] args) {
		SpringApplication.run(Desafio1Application.class, args);
		
	}

}

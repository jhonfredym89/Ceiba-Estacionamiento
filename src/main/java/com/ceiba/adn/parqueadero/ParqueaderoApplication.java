package com.ceiba.adn.parqueadero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ceiba.adn.parqueadero")
public class ParqueaderoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParqueaderoApplication.class, args);
	}

}

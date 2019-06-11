package com.ceiba.adn.parqueadero.infraestructura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ceiba.adn.parqueadero.aplicacion.dto.CobroDto;
import com.ceiba.adn.parqueadero.aplicacion.manejador.ParqueaderoManejador;

@RestController
@RestControllerAdvice
@RequestMapping("/parqueadero")
@CrossOrigin("*")
public class ParqueaderoControlador {
	@Autowired
	private ParqueaderoManejador parqueaderoManejador;

	@PostMapping
	public void ingresarVehiculo(@RequestBody CobroDto cobroDto) {
		System.out.println("Dentro del controler");
		parqueaderoManejador.ingresarVehiculo(cobroDto);
	}
}

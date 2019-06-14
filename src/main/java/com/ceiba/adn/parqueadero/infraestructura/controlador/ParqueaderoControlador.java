package com.ceiba.adn.parqueadero.infraestructura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.adn.parqueadero.aplicacion.dto.CobroDto;
import com.ceiba.adn.parqueadero.aplicacion.manejador.ParqueaderoManejador;

@RestController
@RequestMapping("/parqueadero")
public class ParqueaderoControlador {
	private ParqueaderoManejador parqueaderoManejador;

	@Autowired
	public ParqueaderoControlador(ParqueaderoManejador parqueaderoManejador) {
		this.parqueaderoManejador = parqueaderoManejador;
	}

	@PostMapping("/ingreso")
	public void ingresarVehiculo(@RequestBody CobroDto cobroDto) {
		parqueaderoManejador.ingresarVehiculo(cobroDto);
	}
	
	@PutMapping("/retiro/{placa}")
	public long retirarVehiculo(@PathVariable("placa") String placa) {
		return parqueaderoManejador.retirarVehiculo(placa);
	}
}

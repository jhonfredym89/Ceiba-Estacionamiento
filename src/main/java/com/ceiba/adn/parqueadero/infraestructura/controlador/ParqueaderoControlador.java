package com.ceiba.adn.parqueadero.infraestructura.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.adn.parqueadero.aplicacion.dto.CobroDto;
import com.ceiba.adn.parqueadero.aplicacion.dto.Respuesta;
import com.ceiba.adn.parqueadero.aplicacion.dto.RespuestaDto;
import com.ceiba.adn.parqueadero.aplicacion.manejador.ParqueaderoManejador;

@RestController
@RequestMapping("/parqueadero")
@CrossOrigin("*")
public class ParqueaderoControlador {
	private ParqueaderoManejador parqueaderoManejador;

	@Autowired
	public ParqueaderoControlador(ParqueaderoManejador parqueaderoManejador) {
		this.parqueaderoManejador = parqueaderoManejador;
	}

	@PostMapping("/ingreso")
	public ResponseEntity<Respuesta<Integer>> ingresarVehiculo(@RequestBody CobroDto cobroDto) {
		return new ResponseEntity<>(parqueaderoManejador.ingresarVehiculo(cobroDto), HttpStatus.OK);
	}

	@PutMapping("/retiro/{placa}")
	public ResponseEntity<Long> retirarVehiculo(@PathVariable("placa") String placa) {
		return new ResponseEntity<>(parqueaderoManejador.retirarVehiculo(placa), HttpStatus.OK);
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<RespuestaDto>> listar() {
		return new ResponseEntity<>(this.parqueaderoManejador.listarVehiculos(), HttpStatus.OK);
	}
}

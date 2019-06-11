package com.ceiba.adn.parqueadero.aplicacion.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CobroDto {
	private String tipoVehiculo;
	private String placa;
	private int cilindraje;
	private LocalDate fechaIngreso;
}

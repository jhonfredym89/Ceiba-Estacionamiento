package com.ceiba.adn.parqueadero.aplicacion.dto;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CobroDto {
	private String tipoVehiculo;
	private String placa;
	private int cilindraje;
	private Calendar fechaIngreso;
}

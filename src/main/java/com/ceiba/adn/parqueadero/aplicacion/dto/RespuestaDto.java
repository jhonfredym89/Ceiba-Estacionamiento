package com.ceiba.adn.parqueadero.aplicacion.dto;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RespuestaDto {
	private String tipoVehiculo;
	private String placa;
	private Calendar fechaIngreso;
}

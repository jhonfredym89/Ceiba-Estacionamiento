package com.ceiba.adn.parqueadero.dominio.modelo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Cobro {
	private int id;
	private String tipoVehiculo;
	private String placa;
	private int cilindraje;
	private LocalDate fechaIngreso;
	private LocalDate fechaSalida;
	private double valor;
}

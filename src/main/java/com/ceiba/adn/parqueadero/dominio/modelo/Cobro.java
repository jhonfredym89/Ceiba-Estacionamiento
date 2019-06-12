package com.ceiba.adn.parqueadero.dominio.modelo;

import java.time.LocalDate;

public class Cobro {
	private int id;
	private String tipoVehiculo;
	private String placa;
	private int cilindraje;
	private LocalDate fechaIngreso;
	private LocalDate fechaSalida;
	private double valor;

	public Cobro(int id, String tipoVehiculo, String placa, int cilindraje, LocalDate fechaIngreso,
			LocalDate fechaSalida, double valor) {
		super();
		this.id = id;
		this.tipoVehiculo = tipoVehiculo;
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
	}

	public Cobro(String tipoVehiculo, String placa, int cilindraje, LocalDate fechaIngreso) {
		super();
		this.tipoVehiculo = tipoVehiculo;
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.fechaIngreso = fechaIngreso;
	}

	public int getId() {
		return id;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public double getValor() {
		return valor;
	}
}

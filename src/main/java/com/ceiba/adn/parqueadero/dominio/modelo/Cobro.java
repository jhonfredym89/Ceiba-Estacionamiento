package com.ceiba.adn.parqueadero.dominio.modelo;

import java.util.Calendar;

public class Cobro {
	private int id;
	private String tipoVehiculo;
	private String placa;
	private int cilindraje;
	private Calendar fechaIngreso;
	private Calendar fechaSalida;
	private long valor;

	public Cobro(int id, String tipoVehiculo, String placa, int cilindraje, Calendar fechaIngreso, Calendar fechaSalida,
			long valor) {
		super();
		this.id = id;
		this.tipoVehiculo = tipoVehiculo;
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
	}

	public Cobro(String tipoVehiculo, String placa, int cilindraje, Calendar fechaIngreso) {
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

	public Calendar getFechaIngreso() {
		return fechaIngreso;
	}

	public Calendar getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public long getValor() {
		return valor;
	}

	public void setValor(long valor) {
		this.valor = valor;
	}
}

package com.ceiba.adn.parqueadero.testdatabuilder;

import java.time.LocalDate;

import com.ceiba.adn.parqueadero.dominio.modelo.Cobro;

public class ParqueaderoTestDataBuilder {
	private static final int IDENTIFICADOR = 1;
	private static final String TIPO_VEHICULO = "moto";
	private static final String PLACA_VEHICULO = "BCD-123";
	private static final int CILINDRAJE_VEHICULO = 650;
	private static final LocalDate FECHA_INGRESO = LocalDate.now();
	private static final LocalDate FECHA_SALIDA = null;

	private int id;
	private String tipoVehiculo;
	private String placa;
	private int cilindraje;
	private LocalDate fechaIngreso;
	private LocalDate fechaSalida;

	public ParqueaderoTestDataBuilder() {
		id = IDENTIFICADOR;
		tipoVehiculo = TIPO_VEHICULO;
		placa = PLACA_VEHICULO;
		cilindraje = CILINDRAJE_VEHICULO;
		fechaIngreso = FECHA_INGRESO;
		fechaSalida = FECHA_SALIDA;
	}

	public ParqueaderoTestDataBuilder conTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}

	public ParqueaderoTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public ParqueaderoTestDataBuilder conCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public ParqueaderoTestDataBuilder conFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public ParqueaderoTestDataBuilder conFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}

	public Cobro build() {
		return new Cobro(this.id, this.tipoVehiculo, this.placa, this.cilindraje, this.fechaIngreso, this.fechaSalida,
				0);
	}
}

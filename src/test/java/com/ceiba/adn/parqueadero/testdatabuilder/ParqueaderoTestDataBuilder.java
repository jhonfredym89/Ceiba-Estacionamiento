package com.ceiba.adn.parqueadero.testdatabuilder;

import java.util.Calendar;

import com.ceiba.adn.parqueadero.dominio.modelo.Cobro;
import com.ceiba.adn.parqueadero.dominio.modelo.TipoVehiculo;

public class ParqueaderoTestDataBuilder {
	private static final int IDENTIFICADOR = 1;
	private static final String TIPO_VEHICULO = TipoVehiculo.MOTO.getTipo();
	private static final String PLACA_VEHICULO = "BCD-123";
	private static final int CILINDRAJE_VEHICULO = 250;

	private int id;
	private String tipoVehiculo;
	private String placa;
	private int cilindraje;
	private Calendar fechaIngreso;
	private Calendar fechaSalida;

	public ParqueaderoTestDataBuilder() {
		id = IDENTIFICADOR;
		tipoVehiculo = TIPO_VEHICULO;
		placa = PLACA_VEHICULO;
		cilindraje = CILINDRAJE_VEHICULO;
		fechaIngreso = Calendar.getInstance();
		fechaIngreso.add(Calendar.HOUR, -6);
		fechaIngreso.add(Calendar.MINUTE, 6);
		fechaSalida = Calendar.getInstance();
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

	public ParqueaderoTestDataBuilder conFechaIngreso(Calendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public Cobro build() {
		return new Cobro(this.id, this.tipoVehiculo, this.placa, this.cilindraje, this.fechaIngreso, this.fechaSalida,
				0);
	}
}

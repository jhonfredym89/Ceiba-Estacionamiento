package com.ceiba.adn.parqueadero.testdatabuilder;

import java.util.Calendar;

import com.ceiba.adn.parqueadero.aplicacion.dto.CobroDto;
import com.ceiba.adn.parqueadero.dominio.modelo.TipoVehiculo;

public class ParqueaderoControladorTestDataBuilder {
	private static final String TIPO_VEHICULO = TipoVehiculo.MOTO.getTipo();
	private static final String PLACA_VEHICULO = "BCD-123";
	private static final int CILINDRAJE_VEHICULO = 650;

	private String tipoVehiculo;
	private String placa;
	private int cilindraje;
	private Calendar fechaIngreso;

	public ParqueaderoControladorTestDataBuilder() {
		tipoVehiculo = TIPO_VEHICULO;
		placa = PLACA_VEHICULO;
		cilindraje = CILINDRAJE_VEHICULO;
		fechaIngreso = Calendar.getInstance();
	}

	public ParqueaderoControladorTestDataBuilder conTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}

	public ParqueaderoControladorTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public ParqueaderoControladorTestDataBuilder conCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public ParqueaderoControladorTestDataBuilder conFechaIngreso(Calendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public CobroDto build() {
		return new CobroDto(this.tipoVehiculo, this.placa, this.cilindraje, this.fechaIngreso);
	}
}

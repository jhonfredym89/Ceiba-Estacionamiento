package com.ceiba.adn.parqueadero.aplicacion.manejador;

import java.util.Calendar;

import com.ceiba.adn.parqueadero.aplicacion.dto.CobroDto;
import com.ceiba.adn.parqueadero.dominio.modelo.Cobro;
import com.ceiba.adn.parqueadero.dominio.servicio.ServicioParqueadero;

public class ParqueaderoManejador {
	private ServicioParqueadero servicioParqueadero;

	public ParqueaderoManejador(ServicioParqueadero servicioParqueadero) {
		this.servicioParqueadero = servicioParqueadero;
	}

	public int ingresarVehiculo(CobroDto cobroDto) {
		return servicioParqueadero.ingresarVehiculo(new Cobro(cobroDto.getTipoVehiculo(), cobroDto.getPlaca(),
				cobroDto.getCilindraje(), Calendar.getInstance())).getId();
	}

	public long retirarVehiculo(String placa) {
		return servicioParqueadero.retirarVehiculo(placa);
	}
}

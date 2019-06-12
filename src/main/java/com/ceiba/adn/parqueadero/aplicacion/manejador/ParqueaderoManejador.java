package com.ceiba.adn.parqueadero.aplicacion.manejador;

import java.time.LocalDate;

import com.ceiba.adn.parqueadero.aplicacion.dto.CobroDto;
import com.ceiba.adn.parqueadero.dominio.modelo.Cobro;
import com.ceiba.adn.parqueadero.dominio.servicio.ServicioParqueadero;

public class ParqueaderoManejador {
	private ServicioParqueadero servicioParqueadero;

	public ParqueaderoManejador(ServicioParqueadero servicioParqueadero) {
		this.servicioParqueadero = servicioParqueadero;
	}

	public void ingresarVehiculo(CobroDto cobroDto) {
		servicioParqueadero.ingresarVehiculo(
				new Cobro(cobroDto.getTipoVehiculo(), cobroDto.getPlaca(), cobroDto.getCilindraje(), LocalDate.now()));
	}
}

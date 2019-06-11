package com.ceiba.adn.parqueadero.aplicacion.manejador;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.adn.parqueadero.aplicacion.dto.CobroDto;
import com.ceiba.adn.parqueadero.dominio.modelo.Cobro;
import com.ceiba.adn.parqueadero.dominio.servicio.ServicioParqueadero;

@Component
public class ParqueaderoManejador {
	@Autowired
	private ServicioParqueadero servicioParqueadero;

	public void ingresarVehiculo(CobroDto cobroDto) {
		servicioParqueadero.ingresarVehiculo(new Cobro(0, cobroDto.getTipoVehiculo(), cobroDto.getPlaca(),
				cobroDto.getCilindraje(), LocalDate.now(), null, 0));
	}
}

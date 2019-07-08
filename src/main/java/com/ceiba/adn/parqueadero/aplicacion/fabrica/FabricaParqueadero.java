package com.ceiba.adn.parqueadero.aplicacion.fabrica;

import java.util.Calendar;

import com.ceiba.adn.parqueadero.aplicacion.dto.CobroDto;
import com.ceiba.adn.parqueadero.aplicacion.dto.RespuestaDto;
import com.ceiba.adn.parqueadero.dominio.modelo.Cobro;

public final class FabricaParqueadero {
	private FabricaParqueadero() {
	}

	public static Cobro crearCobro(final CobroDto cobroDto) {
		return new Cobro(cobroDto.getTipoVehiculo(), cobroDto.getPlaca(), cobroDto.getCilindraje(),
				cobroDto.getFechaIngreso() == null ? Calendar.getInstance() : cobroDto.getFechaIngreso());
	}

	public static RespuestaDto crearRespuesta(final Cobro cobro) {
		return new RespuestaDto(cobro.getTipoVehiculo(), cobro.getPlaca(), cobro.getFechaIngreso());
	}
}

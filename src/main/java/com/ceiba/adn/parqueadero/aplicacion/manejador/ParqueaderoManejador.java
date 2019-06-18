package com.ceiba.adn.parqueadero.aplicacion.manejador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.ceiba.adn.parqueadero.aplicacion.dto.CobroDto;
import com.ceiba.adn.parqueadero.aplicacion.dto.Respuesta;
import com.ceiba.adn.parqueadero.aplicacion.dto.RespuestaDto;
import com.ceiba.adn.parqueadero.dominio.modelo.Cobro;
import com.ceiba.adn.parqueadero.dominio.servicio.ServicioParqueadero;

public class ParqueaderoManejador {
	private ServicioParqueadero servicioParqueadero;

	public ParqueaderoManejador(ServicioParqueadero servicioParqueadero) {
		this.servicioParqueadero = servicioParqueadero;
	}

	public Respuesta<Integer> ingresarVehiculo(CobroDto cobroDto) {
		return new Respuesta<>(servicioParqueadero.ingresarVehiculo(new Cobro(cobroDto.getTipoVehiculo(),
				cobroDto.getPlaca(), cobroDto.getCilindraje(), Calendar.getInstance())).getId());
	}

	public long retirarVehiculo(String placa) {
		return servicioParqueadero.retirarVehiculo(placa);
	}

	public List<RespuestaDto> listarVehiculos() {
		final List<Cobro> listaCobros = servicioParqueadero.listarVehiculos();
		final List<RespuestaDto> listaRespuesta = new ArrayList<>();

		listaCobros.forEach(cobro -> listaRespuesta
				.add(new RespuestaDto(cobro.getTipoVehiculo(), cobro.getPlaca(), cobro.getFechaIngreso())));
		return listaRespuesta;
	}
}

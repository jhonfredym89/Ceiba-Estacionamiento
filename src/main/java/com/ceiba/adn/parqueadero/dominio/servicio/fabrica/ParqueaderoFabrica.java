package com.ceiba.adn.parqueadero.dominio.servicio.fabrica;

import com.ceiba.adn.parqueadero.dominio.modelo.TipoVehiculo;

public final class ParqueaderoFabrica {
	private static ParqueaderoFabrica parqueaderoFabrica;

	private ParqueaderoFabrica() {
	}

	public static ParqueaderoFabrica obtenerInstancia() {
		if (parqueaderoFabrica == null) {
			parqueaderoFabrica = new ParqueaderoFabrica();
		}
		return parqueaderoFabrica;
	}

	public CobroParqueadero obtenerTipoVehiculo(String tipoVehiculo) {
		final CobroParqueadero cobroParqueadero;
		if (tipoVehiculo.equalsIgnoreCase(TipoVehiculo.CARRO.getTipo())) {
			cobroParqueadero = new CobroCarro();
		} else {
			cobroParqueadero = new CobroMoto();
		}
		return cobroParqueadero;
	}
}

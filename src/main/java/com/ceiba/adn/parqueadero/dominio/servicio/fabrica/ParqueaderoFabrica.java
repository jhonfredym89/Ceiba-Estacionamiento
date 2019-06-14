package com.ceiba.adn.parqueadero.dominio.servicio.fabrica;

import com.ceiba.adn.parqueadero.dominio.modelo.TipoVehiculo;

public final class ParqueaderoFabrica {
	private static ParqueaderoFabrica parqueaderoFabrica = new ParqueaderoFabrica();

	private ParqueaderoFabrica() {
	}

	public static ParqueaderoFabrica obtenerInstancia() {
		return parqueaderoFabrica;
	}

	public CobroParqueadero obtenerTipoVehiculo(String tipoVehiculo) {
		return tipoVehiculo.equalsIgnoreCase(TipoVehiculo.CARRO.getTipo()) ? new CobroCarro() : new CobroMoto();
	}
}

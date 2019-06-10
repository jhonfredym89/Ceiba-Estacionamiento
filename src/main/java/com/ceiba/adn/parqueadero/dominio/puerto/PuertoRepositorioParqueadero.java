package com.ceiba.adn.parqueadero.dominio.puerto;

import com.ceiba.adn.parqueadero.dominio.modelo.Cobro;

public interface PuertoRepositorioParqueadero {
	/**
	 * Cuenta el n�mero de veh�culos dentro del parqueadero segun el tipo pasado
	 * como parametro.
	 * 
	 * @param tipoVehiculo Tipo de vehiculo a contar (moto, carro).
	 * @return int.
	 */
	int contarVehiculosPorTipo(String tipoVehiculo);

	/**
	 * Registra el ingreso de un veh�culo al parqueadero.
	 *
	 * @param cobro Objeto que contiene la informaci�n del veh�culo y la fecha de
	 *              ingreso.
	 * @return Cobro.
	 */
	Cobro ingresarVehiculo(Cobro cobro);
}

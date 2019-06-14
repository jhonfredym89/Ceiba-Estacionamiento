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
	 * @param cobro Objeto que contiene la informaci�n del servicio prestado.
	 * @return Cobro.
	 */
	Cobro ingresarYactualizarVehiculo(Cobro cobro);

	/**
	 * Busca un vehiculo segun su placa.
	 * 
	 * @param placa N�mero de placa del vehiculo.
	 * @return Cobro.
	 */
	Cobro buscarVehiculoPorPlaca(final String placa);
}

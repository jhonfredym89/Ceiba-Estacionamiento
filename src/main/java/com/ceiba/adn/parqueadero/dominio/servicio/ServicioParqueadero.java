package com.ceiba.adn.parqueadero.dominio.servicio;

import java.util.Calendar;

import com.ceiba.adn.parqueadero.dominio.excepcion.ExcepcionParqueadero;
import com.ceiba.adn.parqueadero.dominio.modelo.Cobro;
import com.ceiba.adn.parqueadero.dominio.puerto.PuertoRepositorioParqueadero;

public class ServicioParqueadero {
	private static final String LETRA_DE_PLACA_CON_RESTRICCION = "A";
	private static final String MENSAJE_INGRESO_NO_AUTORIZADO = "El vehiculo no puede ingresar el dia de hoy";
	private static final String MENSAJE_NO_HAY_CUPO = "En el momento no hay cupo para el vehiculo";
	private static final int MAXIMO_MOTOS = 10;
	private static final int MAXIMO_CARROS = 20;
	private static final String TIPO_VEHICULO_MOTO = "moto";
	private static final String TIPO_VEHICULO_CARRO = "carro";

	private PuertoRepositorioParqueadero repositorioParqueadero;

	public ServicioParqueadero(PuertoRepositorioParqueadero repositorioParqueadero) {
		this.repositorioParqueadero = repositorioParqueadero;
	}

	public Cobro ingresarVehiculo(Cobro cobro) {
		validarIngresoPorPlaca(cobro.getPlaca());
		validarCantidadVehiculosPorTipo(cobro.getTipoVehiculo());
		return repositorioParqueadero.ingresarVehiculo(cobro);
	}

	private void validarIngresoPorPlaca(String placa) {
		Calendar hoy = Calendar.getInstance();

		if (hoy.get(Calendar.DAY_OF_WEEK) > Calendar.MONDAY && placa.startsWith(LETRA_DE_PLACA_CON_RESTRICCION)) {
			throw new ExcepcionParqueadero(MENSAJE_INGRESO_NO_AUTORIZADO);
		}
	}

	private void validarCantidadVehiculosPorTipo(String tipoVehiculo) {
		int cantidad = repositorioParqueadero.contarVehiculosPorTipo(tipoVehiculo);

		if ((tipoVehiculo.equals(TIPO_VEHICULO_MOTO) && cantidad == MAXIMO_MOTOS)
				|| (tipoVehiculo.equals(TIPO_VEHICULO_CARRO) && cantidad == MAXIMO_CARROS)) {
			throw new ExcepcionParqueadero(MENSAJE_NO_HAY_CUPO);
		}
	}
}

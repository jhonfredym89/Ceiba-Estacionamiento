package com.ceiba.adn.parqueadero.dominio.servicio;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.adn.parqueadero.dominio.excepcion.ExcepcionParqueadero;
import com.ceiba.adn.parqueadero.dominio.modelo.Cobro;
import com.ceiba.adn.parqueadero.dominio.puerto.PuertoRepositorioParqueadero;

@Service
public class ServicioParqueadero {
	private static final String LETRA_DE_PLACA_CON_RESTRICCION = "A";
	private static final String MENSAJE_INGRESO_NO_AUTORIZADO = "El vehiculo no puede ingresar el día de hoy";
	private static final String MENSAJE_NO_HAY_CUPO = "En el momento no hay cupo para el vehiculo";
	private static final int MAXIMO_MOTOS = 10;
	private static final int MAXIMO_CARROS = 20;

	@Autowired
	private PuertoRepositorioParqueadero repositorioParqueadero;

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

		if ((tipoVehiculo.equals("moto") && cantidad == MAXIMO_MOTOS)
				|| (tipoVehiculo.equals("carro") && cantidad == MAXIMO_CARROS)) {
			throw new ExcepcionParqueadero(MENSAJE_NO_HAY_CUPO);
		}
	}
}

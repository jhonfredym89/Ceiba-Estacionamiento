package com.ceiba.adn.parqueadero.dominio.servicio;

import java.util.Calendar;

import com.ceiba.adn.parqueadero.dominio.excepcion.ExcepcionParqueadero;
import com.ceiba.adn.parqueadero.dominio.modelo.Cobro;
import com.ceiba.adn.parqueadero.dominio.modelo.TipoVehiculo;
import com.ceiba.adn.parqueadero.dominio.puerto.PuertoRepositorioParqueadero;
import com.ceiba.adn.parqueadero.dominio.servicio.fabrica.CobroParqueadero;
import com.ceiba.adn.parqueadero.dominio.servicio.fabrica.ParqueaderoFabrica;

public class ServicioParqueadero {
	private static final String LETRA_DE_PLACA_CON_RESTRICCION = "A";
	private static final String MENSAJE_INGRESO_NO_AUTORIZADO = "El vehiculo no puede ingresar el dia de hoy";
	private static final String MENSAJE_NO_HAY_CUPO = "En el momento no hay cupo para el vehiculo";
	private static final String MENSAJE_VEHICULO_NO_EXISTE = "El vehiculo no se encuentra dentro del parqueadero";
	private static final int MAXIMO_MOTOS = 10;
	private static final int MAXIMO_CARROS = 20;

	private PuertoRepositorioParqueadero repositorioParqueadero;

	public ServicioParqueadero(PuertoRepositorioParqueadero repositorioParqueadero) {
		this.repositorioParqueadero = repositorioParqueadero;
	}

	public Cobro ingresarVehiculo(Cobro cobro) {
		validarIngresoPorPlacaYfecha(cobro.getPlaca(), cobro.getFechaIngreso());
		validarCantidadVehiculosPorTipo(cobro.getTipoVehiculo());
		return repositorioParqueadero.ingresarYactualizarVehiculo(cobro);
	}

	private void validarIngresoPorPlacaYfecha(String placa, Calendar fechaIngreso) {
		if (placa.startsWith(LETRA_DE_PLACA_CON_RESTRICCION)
				&& fechaIngreso.get(Calendar.DAY_OF_WEEK) > Calendar.MONDAY) {
			throw new ExcepcionParqueadero(MENSAJE_INGRESO_NO_AUTORIZADO);
		}
	}

	private void validarCantidadVehiculosPorTipo(String tipoVehiculo) {
		int cantidad = repositorioParqueadero.contarVehiculosPorTipo(tipoVehiculo);

		if ((tipoVehiculo.equals(TipoVehiculo.MOTO.getTipo()) && cantidad == MAXIMO_MOTOS)
				|| (tipoVehiculo.equals(TipoVehiculo.CARRO.getTipo()) && cantidad == MAXIMO_CARROS)) {
			throw new ExcepcionParqueadero(MENSAJE_NO_HAY_CUPO);
		}
	}

	public long retirarVehiculo(final String placa) {
		Cobro cobro = repositorioParqueadero.buscarVehiculoPorPlaca(placa);

		if (cobro == null) {
			throw new ExcepcionParqueadero(MENSAJE_VEHICULO_NO_EXISTE);
		}
		cobrar(cobro);
		repositorioParqueadero.ingresarYactualizarVehiculo(cobro);

		return cobro.getValor();
	}

	private void cobrar(Cobro cobro) {
		CobroParqueadero cobroParqueadero;
		cobro.setFechaSalida(Calendar.getInstance());
		cobroParqueadero = ParqueaderoFabrica.obtenerInstancia().obtenerTipoVehiculo(cobro.getTipoVehiculo());
		cobroParqueadero.cobrar(cobro);
	}
}

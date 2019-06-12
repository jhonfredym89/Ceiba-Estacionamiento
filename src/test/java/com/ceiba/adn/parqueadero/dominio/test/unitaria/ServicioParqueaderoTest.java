package com.ceiba.adn.parqueadero.dominio.test.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.ceiba.adn.parqueadero.dominio.excepcion.ExcepcionParqueadero;
import com.ceiba.adn.parqueadero.dominio.modelo.Cobro;
import com.ceiba.adn.parqueadero.dominio.puerto.PuertoRepositorioParqueadero;
import com.ceiba.adn.parqueadero.dominio.servicio.ServicioParqueadero;
import com.ceiba.adn.parqueadero.testdatabuilder.ParqueaderoTestDataBuilder;

public class ServicioParqueaderoTest {
	private static final String MENSAJE_INGRESO_NO_AUTORIZADO = "El vehiculo no puede ingresar el dia de hoy";
	private static final String MENSAJE_NO_HAY_CUPO = "En el momento no hay cupo para el vehiculo";

	private PuertoRepositorioParqueadero repositorioParqueadero;

	@Before
	public void iniciarMocks() {
		repositorioParqueadero = mock(PuertoRepositorioParqueadero.class);
	}

	@Test
	public void vehiculoNoPuedeIngresarPorPlacaTest() {
		// Arrange
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder().conPlaca("ABC-123");
		Cobro cobro = parqueaderoTestDataBuilder.build();
		ServicioParqueadero servicioParqueadero = new ServicioParqueadero(repositorioParqueadero);
		// Act
		try {
			servicioParqueadero.ingresarVehiculo(cobro);
			fail();
		} catch (ExcepcionParqueadero e) {
			// Assert
			assertEquals(MENSAJE_INGRESO_NO_AUTORIZADO, e.getMessage());
		}
	}

	@Test
	public void parqueaderoSinCupoParaMotoTest() {
		// Arrange
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder();
		Cobro cobro = parqueaderoTestDataBuilder.build();
		ServicioParqueadero servicioParqueadero = new ServicioParqueadero(repositorioParqueadero);
		when(repositorioParqueadero.contarVehiculosPorTipo("moto")).thenReturn(10);
		// Act
		try {
			servicioParqueadero.ingresarVehiculo(cobro);
			fail();
		} catch (ExcepcionParqueadero e) {
			// Assert
			assertEquals(MENSAJE_NO_HAY_CUPO, e.getMessage());
		}
	}

	@Test
	public void parqueaderoSinCupoParaCarroTest() {
		// Arrange
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder()
				.conTipoVehiculo("carro");
		Cobro cobro = parqueaderoTestDataBuilder.build();
		ServicioParqueadero servicioParqueadero = new ServicioParqueadero(repositorioParqueadero);
		when(repositorioParqueadero.contarVehiculosPorTipo("carro")).thenReturn(20);
		// Act
		try {
			servicioParqueadero.ingresarVehiculo(cobro);
			fail();
		} catch (ExcepcionParqueadero e) {
			// Assert
			assertEquals(MENSAJE_NO_HAY_CUPO, e.getMessage());
		}
	}

	@Test
	public void ingresoVehiculoTest() {
		// Arrange
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder();
		Cobro cobro = parqueaderoTestDataBuilder.build();
		ServicioParqueadero servicioParqueadero = new ServicioParqueadero(repositorioParqueadero);
		when(repositorioParqueadero.ingresarVehiculo(cobro)).thenReturn(cobro);
		// Act
		Cobro c = servicioParqueadero.ingresarVehiculo(cobro);
		// Assert
		assertEquals(cobro.getId(), c.getId());
	}
}

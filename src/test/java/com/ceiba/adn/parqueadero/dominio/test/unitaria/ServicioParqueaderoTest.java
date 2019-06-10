package com.ceiba.adn.parqueadero.dominio.test.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ceiba.adn.parqueadero.dominio.excepcion.ExcepcionParqueadero;
import com.ceiba.adn.parqueadero.dominio.modelo.Cobro;
import com.ceiba.adn.parqueadero.dominio.puerto.PuertoRepositorioParqueadero;
import com.ceiba.adn.parqueadero.dominio.servicio.ServicioParqueadero;
import com.ceiba.adn.parqueadero.testdatabuilder.ParqueaderoTestDataBuilder;

@RunWith(MockitoJUnitRunner.class)
public class ServicioParqueaderoTest {
	private static final String MENSAJE_INGRESO_NO_AUTORIZADO = "El vehiculo no puede ingresar el día de hoy";
	private static final String MENSAJE_NO_HAY_CUPO = "En el momento no hay cupo para el vehiculo";

	@InjectMocks
	private ServicioParqueadero servicioParqueadero;
	@Mock
	private PuertoRepositorioParqueadero repositorioParqueadero;

	@Test
	public void vehiculoNoPuedeIngresarPorPlacaTest() {
		// Arrange
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder().conPlaca("ABC-123");
		Cobro cobro = parqueaderoTestDataBuilder.build();
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
	public void ingresoVehiculoTest() {
		// Arrange
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder();
		Cobro cobro = parqueaderoTestDataBuilder.build();
		when(repositorioParqueadero.ingresarVehiculo(cobro)).thenReturn(cobro);
		// Act
		servicioParqueadero.ingresarVehiculo(cobro);
		// Assert
		assertEquals(1, cobro.getId());
	}
}

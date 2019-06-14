package com.ceiba.adn.parqueadero.dominio.test.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.ceiba.adn.parqueadero.dominio.excepcion.ExcepcionParqueadero;
import com.ceiba.adn.parqueadero.dominio.modelo.Cobro;
import com.ceiba.adn.parqueadero.dominio.modelo.TipoVehiculo;
import com.ceiba.adn.parqueadero.dominio.puerto.PuertoRepositorioParqueadero;
import com.ceiba.adn.parqueadero.dominio.servicio.ServicioParqueadero;
import com.ceiba.adn.parqueadero.testdatabuilder.ParqueaderoTestDataBuilder;

public class ServicioParqueaderoTest {
	private static final String MENSAJE_INGRESO_NO_AUTORIZADO = "El vehiculo no puede ingresar el dia de hoy";
	private static final String MENSAJE_NO_HAY_CUPO = "En el momento no hay cupo para el vehiculo";
	private static final String MENSAJE_VEHICULO_NO_EXISTE = "El vehiculo no se encuentra dentro del parqueadero";
	private static final int VALOR_HORA_CARRO = 1000;
	private static final int VALOR_HORA_MOTO = 500;
	private static final int VALOR_DIA_CARRO = 8000;
	private static final int VALOR_DIA_MOTO = 4000;
	private static final int VALOR_ADICIONAL_MOTO = 2000;

	private PuertoRepositorioParqueadero repositorioParqueadero;

	@Before
	public void iniciarMocks() {
		repositorioParqueadero = mock(PuertoRepositorioParqueadero.class);
	}

	@Test
	public void vehiculoNoPuedeIngresarPorPlacaTest() {
		// Arrange
		Calendar fechaIngreso = Calendar.getInstance();
		fechaIngreso.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder().conPlaca("ABC-123")
				.conFechaIngreso(fechaIngreso);
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
		Calendar fechaIngreso = Calendar.getInstance();
		fechaIngreso.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder().conPlaca("ABC-123")
				.conFechaIngreso(fechaIngreso);
		Cobro cobro = parqueaderoTestDataBuilder.build();
		ServicioParqueadero servicioParqueadero = new ServicioParqueadero(repositorioParqueadero);
		when(repositorioParqueadero.contarVehiculosPorTipo(TipoVehiculo.MOTO.getTipo())).thenReturn(10);
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
				.conTipoVehiculo(TipoVehiculo.CARRO.getTipo());
		Cobro cobro = parqueaderoTestDataBuilder.build();
		ServicioParqueadero servicioParqueadero = new ServicioParqueadero(repositorioParqueadero);
		when(repositorioParqueadero.contarVehiculosPorTipo(TipoVehiculo.CARRO.getTipo())).thenReturn(20);
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
	public void ingresarMotoTest() {
		// Arrange
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder();
		Cobro cobro = parqueaderoTestDataBuilder.build();
		ServicioParqueadero servicioParqueadero = new ServicioParqueadero(repositorioParqueadero);
		when(repositorioParqueadero.ingresarYactualizarVehiculo(cobro)).thenReturn(cobro);
		// Act
		Cobro c = servicioParqueadero.ingresarVehiculo(cobro);
		// Assert
		assertEquals(cobro.getId(), c.getId());
	}

	@Test
	public void ingresarCarroTest() {
		// Arrange
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder()
				.conTipoVehiculo(TipoVehiculo.CARRO.getTipo());
		Cobro cobro = parqueaderoTestDataBuilder.build();
		ServicioParqueadero servicioParqueadero = new ServicioParqueadero(repositorioParqueadero);
		when(repositorioParqueadero.ingresarYactualizarVehiculo(cobro)).thenReturn(cobro);
		// Act
		Cobro c = servicioParqueadero.ingresarVehiculo(cobro);
		// Assert
		assertEquals(cobro.getId(), c.getId());
	}

	@Test
	public void vehiculoNoExisteTest() {
		// Arrange
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder();
		Cobro cobro = parqueaderoTestDataBuilder.build();
		ServicioParqueadero servicioParqueadero = new ServicioParqueadero(repositorioParqueadero);
		when(repositorioParqueadero.buscarVehiculoPorPlaca(cobro.getPlaca())).thenReturn(null);
		// Act
		try {
			servicioParqueadero.retirarVehiculo(cobro.getPlaca());
			fail();
		} catch (ExcepcionParqueadero e) {
			// Assert
			assertEquals(MENSAJE_VEHICULO_NO_EXISTE, e.getMessage());
		}
	}

	@Test
	public void cobrarPorHorasMotoTest() {
		// Arrange
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder();
		Cobro cobro = parqueaderoTestDataBuilder.build();
		ServicioParqueadero servicioParqueadero = new ServicioParqueadero(repositorioParqueadero);
		when(repositorioParqueadero.buscarVehiculoPorPlaca(cobro.getPlaca())).thenReturn(cobro);
		// Act
		servicioParqueadero.retirarVehiculo(cobro.getPlaca());
		// Assert
		assertEquals((VALOR_HORA_MOTO * 6), cobro.getValor(), 0);
	}

	@Test
	public void cobrarPorHorasMotoMayorCilindrajeTest() {
		// Arrange
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder().conCilindraje(650);
		Cobro cobro = parqueaderoTestDataBuilder.build();
		ServicioParqueadero servicioParqueadero = new ServicioParqueadero(repositorioParqueadero);
		when(repositorioParqueadero.buscarVehiculoPorPlaca(cobro.getPlaca())).thenReturn(cobro);
		// Act
		servicioParqueadero.retirarVehiculo(cobro.getPlaca());
		// Assert
		assertEquals((VALOR_HORA_MOTO * 6 + VALOR_ADICIONAL_MOTO), cobro.getValor(), 0);
	}

	@Test
	public void cobrarPorHorasCarroTest() {
		// Arrange
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder()
				.conTipoVehiculo(TipoVehiculo.CARRO.getTipo());
		Cobro cobro = parqueaderoTestDataBuilder.build();
		ServicioParqueadero servicioParqueadero = new ServicioParqueadero(repositorioParqueadero);
		when(repositorioParqueadero.buscarVehiculoPorPlaca(cobro.getPlaca())).thenReturn(cobro);
		// Act
		servicioParqueadero.retirarVehiculo(cobro.getPlaca());
		// Assert
		assertEquals((VALOR_HORA_CARRO * 6), cobro.getValor(), 0);
	}

	@Test
	public void cobrarPorDiasMotoTest() {
		// Arrange
		Calendar fechaIngreso = Calendar.getInstance();
		fechaIngreso.add(Calendar.HOUR, -9);
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder()
				.conFechaIngreso(fechaIngreso);
		Cobro cobro = parqueaderoTestDataBuilder.build();
		ServicioParqueadero servicioParqueadero = new ServicioParqueadero(repositorioParqueadero);
		when(repositorioParqueadero.buscarVehiculoPorPlaca(cobro.getPlaca())).thenReturn(cobro);
		// Act
		servicioParqueadero.retirarVehiculo(cobro.getPlaca());
		// Assert
		assertEquals(VALOR_DIA_MOTO, cobro.getValor(), 0);
	}

	@Test
	public void cobrarPorDiasMotoMayorCilindrajeTest() {
		// Arrange
		Calendar fechaIngreso = Calendar.getInstance();
		fechaIngreso.add(Calendar.HOUR, -9);
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder()
				.conFechaIngreso(fechaIngreso).conCilindraje(650);
		Cobro cobro = parqueaderoTestDataBuilder.build();
		ServicioParqueadero servicioParqueadero = new ServicioParqueadero(repositorioParqueadero);
		when(repositorioParqueadero.buscarVehiculoPorPlaca(cobro.getPlaca())).thenReturn(cobro);
		// Act
		servicioParqueadero.retirarVehiculo(cobro.getPlaca());
		// Assert
		assertEquals((VALOR_DIA_MOTO + VALOR_ADICIONAL_MOTO), cobro.getValor(), 0);
	}

	@Test
	public void cobrarPorDiasCarroTest() {
		// Arrange
		Calendar fechaIngreso = Calendar.getInstance();
		fechaIngreso.add(Calendar.HOUR, -9);
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder()
				.conFechaIngreso(fechaIngreso).conTipoVehiculo(TipoVehiculo.CARRO.getTipo());
		Cobro cobro = parqueaderoTestDataBuilder.build();
		ServicioParqueadero servicioParqueadero = new ServicioParqueadero(repositorioParqueadero);
		when(repositorioParqueadero.buscarVehiculoPorPlaca(cobro.getPlaca())).thenReturn(cobro);
		// Act
		servicioParqueadero.retirarVehiculo(cobro.getPlaca());
		// Assert
		assertEquals(VALOR_DIA_CARRO, cobro.getValor(), 0);
	}

	@Test
	public void cobrarPorDiasYhorasMotoTest() {
		// Arrange
		Calendar fechaIngreso = Calendar.getInstance();
		fechaIngreso.add(Calendar.DAY_OF_MONTH, -2);
		fechaIngreso.add(Calendar.HOUR, -3);
		fechaIngreso.add(Calendar.MINUTE, 3);
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder()
				.conFechaIngreso(fechaIngreso);
		Cobro cobro = parqueaderoTestDataBuilder.build();
		ServicioParqueadero servicioParqueadero = new ServicioParqueadero(repositorioParqueadero);
		when(repositorioParqueadero.buscarVehiculoPorPlaca(cobro.getPlaca())).thenReturn(cobro);
		// Act
		servicioParqueadero.retirarVehiculo(cobro.getPlaca());
		// Assert
		assertEquals(((VALOR_DIA_MOTO * 2) + (VALOR_HORA_MOTO * 3)), cobro.getValor(), 0);
	}

	@Test
	public void cobrarPorDiasYhorasCarroTest() {
		// Arrange
		Calendar fechaIngreso = Calendar.getInstance();
		fechaIngreso.add(Calendar.DAY_OF_MONTH, -2);
		fechaIngreso.add(Calendar.HOUR, -3);
		fechaIngreso.add(Calendar.MINUTE, 3);
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder()
				.conFechaIngreso(fechaIngreso).conTipoVehiculo(TipoVehiculo.CARRO.getTipo());
		Cobro cobro = parqueaderoTestDataBuilder.build();
		ServicioParqueadero servicioParqueadero = new ServicioParqueadero(repositorioParqueadero);
		when(repositorioParqueadero.buscarVehiculoPorPlaca(cobro.getPlaca())).thenReturn(cobro);
		// Act
		servicioParqueadero.retirarVehiculo(cobro.getPlaca());
		// Assert
		assertEquals(((VALOR_DIA_CARRO * 2) + (VALOR_HORA_CARRO * 3)), cobro.getValor(), 0);
	}

	@Test
	public void cobrarPorDosDiasMotoTest() {
		// Arrange
		Calendar fechaIngreso = Calendar.getInstance();
		fechaIngreso.add(Calendar.DAY_OF_MONTH, -2);
		fechaIngreso.add(Calendar.MINUTE, 2);
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder()
				.conFechaIngreso(fechaIngreso);
		Cobro cobro = parqueaderoTestDataBuilder.build();
		ServicioParqueadero servicioParqueadero = new ServicioParqueadero(repositorioParqueadero);
		when(repositorioParqueadero.buscarVehiculoPorPlaca(cobro.getPlaca())).thenReturn(cobro);
		// Act
		servicioParqueadero.retirarVehiculo(cobro.getPlaca());
		// Assert
		assertEquals((VALOR_DIA_MOTO * 2), cobro.getValor(), 0);
	}

	@Test
	public void cobrarPorDosDiasCarroTest() {
		// Arrange
		Calendar fechaIngreso = Calendar.getInstance();
		fechaIngreso.add(Calendar.DAY_OF_MONTH, -2);
		fechaIngreso.add(Calendar.MINUTE, 2);
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder()
				.conFechaIngreso(fechaIngreso).conTipoVehiculo(TipoVehiculo.CARRO.getTipo());
		Cobro cobro = parqueaderoTestDataBuilder.build();
		ServicioParqueadero servicioParqueadero = new ServicioParqueadero(repositorioParqueadero);
		when(repositorioParqueadero.buscarVehiculoPorPlaca(cobro.getPlaca())).thenReturn(cobro);
		// Act
		servicioParqueadero.retirarVehiculo(cobro.getPlaca());
		// Assert
		assertEquals((VALOR_DIA_CARRO * 2), cobro.getValor(), 0);
	}
}

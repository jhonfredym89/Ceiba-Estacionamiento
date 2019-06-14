package com.ceiba.adn.parqueadero.infraestructura.test.integracion;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ceiba.adn.parqueadero.aplicacion.dto.CobroDto;
import com.ceiba.adn.parqueadero.infraestructura.controlador.ParqueaderoControlador;
import com.ceiba.adn.parqueadero.infraestructura.excepcion.ManejoExcepcionParqueadero;
import com.ceiba.adn.parqueadero.testdatabuilder.ParqueaderoControladorTestDataBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ParqueaderoControladorTest {
	@Autowired
	private ParqueaderoControlador parqueaderoControlador;
	@Autowired
	private ManejoExcepcionParqueadero manejoExcepcionParqueadero;
	private MockMvc mockMvc;

	@Before
	public void inicializarMock() {
		mockMvc = MockMvcBuilders.standaloneSetup(parqueaderoControlador, manejoExcepcionParqueadero).build();
	}

	@Test
	public void ingresarVehiculoTest() throws Exception {
		// Arrange
		ParqueaderoControladorTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoControladorTestDataBuilder();
		CobroDto cobroDto = parqueaderoTestDataBuilder.build();
		// Act y Assert
		mockMvc.perform(post("/parqueadero/ingreso").contentType(MediaType.APPLICATION_JSON)
				.content(pasarJsonAstring(cobroDto))).andExpect(status().isOk());
	}

	@Test
	public void ingresarVehiculoDiaNoHabilTest() throws Exception {
		// Arrange
		Calendar fechaIngreso = Calendar.getInstance();
		fechaIngreso.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		ParqueaderoControladorTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoControladorTestDataBuilder()
				.conPlaca("ABC-123").conFechaIngreso(fechaIngreso);
		CobroDto cobroDto = parqueaderoTestDataBuilder.build();
		// Act y Assert
		mockMvc.perform(post("/parqueadero/ingreso").contentType(MediaType.APPLICATION_JSON)
				.content(pasarJsonAstring(cobroDto))).andExpect(status().isBadRequest());
	}

	private String pasarJsonAstring(CobroDto json) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(json);
	}

	@Test
	public void retirarVehiculoNoExistenteTest() throws Exception {
		// Arrange
		String placa = "ZZZ-111";
		// Act y Assert
		mockMvc.perform(put("/parqueadero/retiro/" + placa).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void retirarVehiculoTest() throws Exception {
		// Arrange
		String placa = "ABC-123";
		// Act y Assert
		mockMvc.perform(put("/parqueadero/retiro/" + placa).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}

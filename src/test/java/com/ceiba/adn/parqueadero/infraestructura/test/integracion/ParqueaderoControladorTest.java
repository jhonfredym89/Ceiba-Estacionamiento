package com.ceiba.adn.parqueadero.infraestructura.test.integracion;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.ceiba.adn.parqueadero.testdatabuilder.ParqueaderoControladorTestDataBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ParqueaderoControladorTest {
	@Autowired
	private ParqueaderoControlador parqueaderoControlador;
	private MockMvc mockMvc;

	@Before
	public void inicializarMock() {
		mockMvc = MockMvcBuilders.standaloneSetup(parqueaderoControlador).build();
	}

	@Test
	public void ingresarVehiculoTest() throws Exception {
		ParqueaderoControladorTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoControladorTestDataBuilder();
		CobroDto cobroDto = parqueaderoTestDataBuilder.build();

		mockMvc.perform(post("/parqueadero/ingreso").contentType(MediaType.APPLICATION_JSON)
				.content(pasarJsonAstring(cobroDto))).andExpect(status().isOk());
	}

	private String pasarJsonAstring(CobroDto json) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(json);
	}
}

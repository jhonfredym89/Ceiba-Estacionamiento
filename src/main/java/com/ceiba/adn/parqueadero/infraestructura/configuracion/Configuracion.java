package com.ceiba.adn.parqueadero.infraestructura.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.adn.parqueadero.aplicacion.manejador.ParqueaderoManejador;
import com.ceiba.adn.parqueadero.dominio.puerto.PuertoRepositorioParqueadero;
import com.ceiba.adn.parqueadero.dominio.servicio.ServicioParqueadero;

@Configuration
public class Configuracion {
	
	@Bean
	public ServicioParqueadero servicioParqueadero(PuertoRepositorioParqueadero puertoRepositorioParqueadero) {
		return new ServicioParqueadero(puertoRepositorioParqueadero);
	}

	@Bean
	public ParqueaderoManejador parqueaderoManejador(ServicioParqueadero servicioParqueadero) {
		return new ParqueaderoManejador(servicioParqueadero);
	}
}

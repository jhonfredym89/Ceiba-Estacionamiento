package com.ceiba.adn.parqueadero.infraestructura.excepcion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Excepcion {
	private String nombre;
	private String mensaje;
}

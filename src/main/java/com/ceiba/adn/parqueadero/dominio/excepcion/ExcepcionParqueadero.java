package com.ceiba.adn.parqueadero.dominio.excepcion;

public class ExcepcionParqueadero extends RuntimeException {
	private static final long serialVersionUID = -4123236104879931034L;

	public ExcepcionParqueadero(String mensaje) {
		super(mensaje);
	}
}

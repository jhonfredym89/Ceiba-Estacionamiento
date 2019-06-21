package com.ceiba.adn.parqueadero.comun;

import com.ceiba.adn.parqueadero.dominio.excepcion.ExcepcionParqueadero;

public class ValidadorArgumento {
	private ValidadorArgumento() {
	}

	public static void validarObligatorio(Object valor, String mensaje) {
		if (valor == null) {
			throw new ExcepcionParqueadero(mensaje);
		}
	}
}

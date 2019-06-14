package com.ceiba.adn.parqueadero.infraestructura.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ceiba.adn.parqueadero.dominio.excepcion.ExcepcionParqueadero;

@ControllerAdvice
public class ManejoExcepcionParqueadero extends ResponseEntityExceptionHandler {
	private static final String MENSAJE_CONTACTAR_A_SOPORTE = "Estamos presentando fallas, por favor contactar al personal de soporte";

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Excepcion> manejarExcepcion(Exception excepcion) {
		final String nombreExcepcion = excepcion.getClass().getSimpleName();
		final ResponseEntity<Excepcion> respuesta;
		final Excepcion excepcionParqueadero;

		if (excepcion instanceof ExcepcionParqueadero) {
			excepcionParqueadero = new Excepcion(nombreExcepcion, excepcion.getMessage());
			respuesta = new ResponseEntity<>(excepcionParqueadero, HttpStatus.BAD_REQUEST);
		} else {
			excepcionParqueadero = new Excepcion(nombreExcepcion, MENSAJE_CONTACTAR_A_SOPORTE);
			respuesta = new ResponseEntity<>(excepcionParqueadero, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return respuesta;
	}
}

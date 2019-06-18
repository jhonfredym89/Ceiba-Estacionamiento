package com.ceiba.adn.parqueadero.aplicacion.dto;

public class Respuesta<T> {
	private T valor;

	public Respuesta(T valor) {
		this.valor = valor;
	}

	public T getValor() {
		return valor;
	}
}

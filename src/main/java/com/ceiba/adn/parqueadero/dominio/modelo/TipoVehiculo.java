package com.ceiba.adn.parqueadero.dominio.modelo;

public enum TipoVehiculo {
	MOTO("moto"), CARRO("carro");

	private String tipo;

	private TipoVehiculo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
}

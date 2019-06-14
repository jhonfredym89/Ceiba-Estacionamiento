package com.ceiba.adn.parqueadero.dominio.servicio.fabrica;

import com.ceiba.adn.parqueadero.dominio.modelo.Cobro;

public class CobroCarro implements CobroParqueadero {

	private static final int CANTIDAD_HORAS_PARA_COBRAR_DIA = 9;
	private static final int VALOR_HORA_CARRO = 1000;
	private static final int VALOR_DIA_CARRO = 8000;

	@Override
	public void cobrar(Cobro cobro) {
		double diferenciaMilisegundos = (cobro.getFechaSalida().getTimeInMillis()
				- cobro.getFechaIngreso().getTimeInMillis());
		double horas = (diferenciaMilisegundos / 3600000);
		long totalHoras = (long) Math.ceil(horas);
		int totalDias = (int) totalHoras / 24;
		int totalHorasNuevoDia = (int) totalHoras % 24;
		long valor;

		if (totalHoras < CANTIDAD_HORAS_PARA_COBRAR_DIA) {
			valor = totalHoras * VALOR_HORA_CARRO;
		} else if (totalHorasNuevoDia == 0
				|| (totalHorasNuevoDia >= CANTIDAD_HORAS_PARA_COBRAR_DIA && totalHorasNuevoDia < 24)) {
			valor = (VALOR_DIA_CARRO * (totalDias == 0 ? 1 : totalDias));
		} else {
			valor = ((totalDias * VALOR_DIA_CARRO) + (totalHorasNuevoDia * VALOR_HORA_CARRO));
		}

		cobro.setValor(valor);
	}

}

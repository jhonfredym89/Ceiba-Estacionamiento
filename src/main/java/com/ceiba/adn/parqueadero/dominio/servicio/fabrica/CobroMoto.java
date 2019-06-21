package com.ceiba.adn.parqueadero.dominio.servicio.fabrica;

import com.ceiba.adn.parqueadero.dominio.modelo.Cobro;

public class CobroMoto implements CobroParqueadero {

	private static final int CANTIDAD_HORAS_PARA_COBRAR_DIA = 9;
	private static final int VALOR_HORA_MOTO = 500;
	private static final int VALOR_DIA_MOTO = 4000;
	private static final int CILINDRAJE_MAYOR = 500;
	private static final int VALOR_ADICIONAL_MOTO = 2000;
	private static final int MILISEGUNDOS_DE_UNA_HORA = 3600000;
	private static final int HORAS_DEL_DIA = 24;

	@Override
	public void cobrar(Cobro cobro) {
		double diferenciaMilisegundos = (cobro.getFechaSalida().getTimeInMillis()
				- cobro.getFechaIngreso().getTimeInMillis());
		double horas = (diferenciaMilisegundos / MILISEGUNDOS_DE_UNA_HORA);
		long totalHoras = (long) Math.ceil(horas);
		int totalDias = (int) totalHoras / HORAS_DEL_DIA;
		int totalHorasNuevoDia = (int) totalHoras % HORAS_DEL_DIA;
		long valor;

		if (totalHoras < CANTIDAD_HORAS_PARA_COBRAR_DIA) {
			valor = totalHoras * VALOR_HORA_MOTO;
		} else if (totalHorasNuevoDia == 0
				|| (totalHorasNuevoDia >= CANTIDAD_HORAS_PARA_COBRAR_DIA && totalHorasNuevoDia < HORAS_DEL_DIA)) {
			valor = (VALOR_DIA_MOTO * (totalDias == 0 ? 1 : totalDias));
		} else {
			valor = ((totalDias * VALOR_DIA_MOTO) + (totalHorasNuevoDia * VALOR_HORA_MOTO));
		}

		if (cobro.getCilindraje() >= CILINDRAJE_MAYOR) {
			valor += VALOR_ADICIONAL_MOTO;
		}

		cobro.setValor(valor);
	}

}

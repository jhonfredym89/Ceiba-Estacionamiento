package com.ceiba.adn.parqueadero.infraestructura.mapeador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.adn.parqueadero.dominio.modelo.Cobro;
import com.ceiba.adn.parqueadero.infraestructura.entidad.CobroEntidad;

@Component
public class MapeadorParqueadero {
	public Cobro convertirAdominio(CobroEntidad cobroEntidad) {
		Cobro cobro;
		if (cobroEntidad == null) {
			cobro = null;
		} else {
			cobro = new Cobro(cobroEntidad.getId(), cobroEntidad.getTipoVehiculo(), cobroEntidad.getPlaca(),
					cobroEntidad.getCilindraje(), cobroEntidad.getFechaIngreso(), cobroEntidad.getFechaSalida(),
					cobroEntidad.getValor());
		}
		return cobro;
	}

	public CobroEntidad convertirAentidad(Cobro cobro) {
		return new CobroEntidad(cobro.getId(), cobro.getTipoVehiculo(), cobro.getPlaca(), cobro.getCilindraje(),
				cobro.getFechaIngreso(), cobro.getFechaSalida(), cobro.getValor());
	}

	public List<Cobro> convertirAlistaDominio(List<CobroEntidad> listaCobroEntidad) {
		final List<Cobro> listaCobro = new ArrayList<>();

		listaCobroEntidad.forEach(cobroEntidad -> listaCobro.add(new Cobro(cobroEntidad.getId(),
				cobroEntidad.getTipoVehiculo(), cobroEntidad.getPlaca(), cobroEntidad.getCilindraje(),
				cobroEntidad.getFechaIngreso(), cobroEntidad.getFechaSalida(), cobroEntidad.getValor())));

		return listaCobro;
	}
}

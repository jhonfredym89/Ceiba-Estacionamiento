package com.ceiba.adn.parqueadero.infraestructura.adaptador;

import org.springframework.stereotype.Component;

import com.ceiba.adn.parqueadero.dominio.modelo.Cobro;
import com.ceiba.adn.parqueadero.dominio.puerto.PuertoRepositorioParqueadero;
import com.ceiba.adn.parqueadero.infraestructura.entidad.CobroEntidad;
import com.ceiba.adn.parqueadero.infraestructura.mapeador.MapeadorParqueadero;
import com.ceiba.adn.parqueadero.infraestructura.repositorio.RepositorioParqueaderoJpa;

@Component
public class AdaptadorRepositorioParqueadero implements PuertoRepositorioParqueadero {

	private RepositorioParqueaderoJpa repositorioParqueaderoJpa;
	private MapeadorParqueadero mapeadorParqueadero;

	public AdaptadorRepositorioParqueadero(RepositorioParqueaderoJpa repositorioParqueaderoJpa,
			MapeadorParqueadero mapeadorParqueadero) {
		this.repositorioParqueaderoJpa = repositorioParqueaderoJpa;
		this.mapeadorParqueadero = mapeadorParqueadero;
	}

	@Override
	public int contarVehiculosPorTipo(String tipoVehiculo) {
		return repositorioParqueaderoJpa.contarVehiculosPorTipo(tipoVehiculo);
	}

	@Override
	public Cobro ingresarVehiculo(Cobro cobro) {
		CobroEntidad cobroEntidad = repositorioParqueaderoJpa.save(mapeadorParqueadero.convertirAentidad(cobro));
		return mapeadorParqueadero.convertirAdominio(cobroEntidad);
	}

}

package com.ceiba.adn.parqueadero.infraestructura.adaptador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.adn.parqueadero.dominio.modelo.Cobro;
import com.ceiba.adn.parqueadero.dominio.puerto.PuertoRepositorioParqueadero;
import com.ceiba.adn.parqueadero.infraestructura.entidad.CobroEntidad;
import com.ceiba.adn.parqueadero.infraestructura.mapeador.MapeadorParqueadero;
import com.ceiba.adn.parqueadero.infraestructura.repositorio.RepositorioParqueaderoJpa;

@Component
public class AdaptadorRepositorioParqueadero implements PuertoRepositorioParqueadero {

	@Autowired
	private RepositorioParqueaderoJpa repositorioParqueaderoJpa;
	@Autowired
	private MapeadorParqueadero mapeadorParqueadero;

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

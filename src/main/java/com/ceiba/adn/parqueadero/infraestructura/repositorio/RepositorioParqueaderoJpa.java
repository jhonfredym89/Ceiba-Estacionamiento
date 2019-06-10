package com.ceiba.adn.parqueadero.infraestructura.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ceiba.adn.parqueadero.infraestructura.entidad.CobroEntidad;

@Repository
public interface RepositorioParqueaderoJpa extends CrudRepository<CobroEntidad, Integer> {
	@Query("SELECT c FROM cobroEntidad c WHERE c.tipoVehiculo = :tipoVehiculo AND c.fechaSalida IS NOT NULL")
	int contarVehiculosPorTipo(@Param("tipoVehiculo") String tipoVehiculo);
}

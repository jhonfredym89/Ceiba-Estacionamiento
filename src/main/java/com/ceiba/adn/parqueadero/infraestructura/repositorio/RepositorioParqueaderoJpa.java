package com.ceiba.adn.parqueadero.infraestructura.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ceiba.adn.parqueadero.infraestructura.entidad.CobroEntidad;

public interface RepositorioParqueaderoJpa extends CrudRepository<CobroEntidad, Integer> {
	@Query(value = "SELECT count(*) FROM cobroEntidad c WHERE c.tipoVehiculo = :tipoVehiculo AND c.fechaSalida IS NOT NULL", nativeQuery = true)
	int contarVehiculosPorTipo(@Param("tipoVehiculo") String tipoVehiculo);
}

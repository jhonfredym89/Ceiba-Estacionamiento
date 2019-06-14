package com.ceiba.adn.parqueadero.infraestructura.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ceiba.adn.parqueadero.infraestructura.entidad.CobroEntidad;

public interface RepositorioParqueaderoJpa extends CrudRepository<CobroEntidad, Integer> {
	@Query("SELECT COUNT(*) FROM CobroEntidad c WHERE c.tipoVehiculo = :tipoVehiculo AND c.fechaSalida IS NULL")
	int contarVehiculosPorTipo(@Param("tipoVehiculo") String tipoVehiculo);

	@Query("SELECT c FROM CobroEntidad c WHERE c.placa = :placa")
	CobroEntidad buscarPorPlaca(@Param("placa") String placa);
}

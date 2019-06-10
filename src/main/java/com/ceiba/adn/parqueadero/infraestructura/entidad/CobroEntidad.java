package com.ceiba.adn.parqueadero.infraestructura.entidad;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Table(name = "cobro")
@Getter
@AllArgsConstructor
public class CobroEntidad {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String tipoVehiculo;
	private String placa;
	private int cilindraje;
	private LocalDate fechaIngreso;
	private LocalDate fechaSalida;
	private double valor;
}

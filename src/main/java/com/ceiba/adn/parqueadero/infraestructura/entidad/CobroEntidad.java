package com.ceiba.adn.parqueadero.infraestructura.entidad;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cobro")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CobroEntidad {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	@Column
	private String tipoVehiculo;
	@Column
	private String placa;
	@Column
	private int cilindraje;
	@Column
	private Calendar fechaIngreso;
	@Column
	private Calendar fechaSalida;
	@Column
	private long valor;
}

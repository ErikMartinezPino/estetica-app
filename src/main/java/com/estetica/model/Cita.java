package com.estetica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cita {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer anio;
	private Integer mes;
	private Integer dia;
	//Ponemos las referencias a otras tablas, no se si sera asi
	private Interger idCliente;
	private Integer idGabinete;
	private String horaInicio;
	private Integer duracion;

}

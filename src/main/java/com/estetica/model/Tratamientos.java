package com.estetica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tratamientos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombreTratamiento;
	private String descripcion;
	private Integer duracion;
	//No se si es asi, pero creo una variable int para referencialo al tipo de tratamiento, como en la BD
	private Integer idTipo;
	
	 

}

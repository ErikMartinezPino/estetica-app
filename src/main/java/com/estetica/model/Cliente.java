package com.estetica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String apellido;
	private String email;
	private String direccion;
	private String poblacion;
	private Integer codigoPostal;
	private String provincia;
	private String dni;
	private Integer telefono;
	private String alergias;
	private String observaciones;	
	

}

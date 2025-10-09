package com.estetica.dto;

import lombok.Data;

@Data
public class EmpleadosDTO {
	
	private Long id;
	private String nombre;
	private String apellidos;
	private Integer telefono;
	private String email;

}

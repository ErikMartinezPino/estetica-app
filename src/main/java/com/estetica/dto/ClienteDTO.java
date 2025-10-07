package com.estetica.dto;

import lombok.Data;

@Data
public class ClienteDTO {
	/*
	 * Los DTO (Data Transfer Object) sirven para:
	 * -No exponer las entidades directamente
	 * -Controlar que campos se envian al frontend
	 */
	
	private Long id;
	private String nombre;
	private String apellidos;
	private String movil;

}

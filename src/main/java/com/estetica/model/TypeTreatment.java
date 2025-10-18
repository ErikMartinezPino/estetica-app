package com.estetica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor  //Esta es para cargar el constructor vacio
@AllArgsConstructor  //Constructor con todos los argumentos
@ToString  //Para agregar el metodo toString
@EqualsAndHashCode  //Esta es opcional
@Table(name="typetreatment")
public class TypeTreatment {
	
	@Id // Anotacion para decirle que es un atributo id de la tabla
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Para el autoincremento y PK
	@Column(name="idTypeTreatment")//Nombre de la columna en la BD
	private Integer idTypeTreatment;
	
	@Column(name="typeTreatment")//Nombre de la columna en la BD
	private String typeTreatment;
	
}

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
@Table(name="appointment")
public class Appointment {

	@Id // Anotacion para decirle que es un atributo id de la tabla
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Para el autoincremento y PK
	@Column(name="idAppointement")//Nombre de la columna en la BD
	private Integer idAppointement;
	
	@Column(name="year")//Nombre de la columna en la BD
	private Integer year;
	
	@Column(name="month")//Nombre de la columna en la BD
	private Integer month;
	
	@Column(name="day")//Nombre de la columna en la BD
	private Integer day;
	
	@Column(name="startTime")//Nombre de la columna en la BD
	private String startTime;
	
	//Ponemos las referencias a otras tablas, no se si sera asi
	@Column(name="idCustomer")//Nombre de la columna en la BD
	private Integer idCustomer;
	
	@Column(name="idCabinet")//Nombre de la columna en la BD
	private Integer idCabinet;	

	@Column(name="duration")//Nombre de la columna en la BD
	private Integer duration;
	
	@Column(name="observations")//Nombre de la columna en la BD
	private String observations;

}

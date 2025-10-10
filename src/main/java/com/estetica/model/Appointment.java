package com.estetica.model;

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
@Table(name="citas")
public class Appointment {

	@Id // Anotacion para decirle que es un atributo id de la tabla
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Para el autoincremento y PK
	private Integer idAppointement;
	
	private Integer year;
	private Integer month;
	private Integer day;
	//Ponemos las referencias a otras tablas, no se si sera asi
	private Integer idCustomer;
	private Integer idCabinet;
	private String startTime;
	private Integer duration;

}

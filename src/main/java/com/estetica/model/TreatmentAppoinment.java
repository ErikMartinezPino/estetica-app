package com.estetica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="treatment-appointment")
public class TreatmentAppoinment {

	@Column(name="idAppointment")//Nombre de la columna en la BD
	private Integer idAppointment;
	
	@Column(name="idTreatment")//Nombre de la columna en la BD
	private Integer idTreatment;
}

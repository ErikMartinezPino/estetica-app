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
@Table(name="customer")
public class Customer {
	
	 @Id//Anotacion para decirle que es un atributo id de la tabla
	 @GeneratedValue(strategy = GenerationType.IDENTITY)//Para el autoincremento y PK
	 @Column(name="idCustomer")//Nombre de la columna en la BD
	 private Integer idCustomer;
	 
	 @Column(name="name")//Nombre de la columna en la BD
	 private String name;
	 
	 @Column(name="surname")//Nombre de la columna en la BD
	 private String surname;
	 
	 @Column(name="email")//Nombre de la columna en la BD
	 private String email;
	 
	 @Column(name="mobile")//Nombre de la columna en la BD
	 private Integer mobile;
	 
	 @Column(name="address")//Nombre de la columna en la BD
	 private String address;
	 
	 @Column(name="town")//Nombre de la columna en la BD
	 private String town;
	 
	 @Column(name="province")//Nombre de la columna en la BD
	 private String province;
	 
	 @Column(name="zipCode")//Nombre de la columna en la BD
	 private Integer zipCode;
	 
	 @Column(name="dni")//Nombre de la columna en la BD
	 private String dni;
	 
	 @Column(name="allergies")//Nombre de la columna en la BD
	 private String allergies;
	 
	 @Column(name="observations")//Nombre de la columna en la BD
	 private String observations;	 

}

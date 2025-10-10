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
@Table(name="clientes")
public class Customer {
	
	 @Id//Anotacion para decirle que es un atributo id de la tabla
	 @GeneratedValue(strategy = GenerationType.IDENTITY)//Para el autoincremento y PK
	 private Integer idCustomer;
	 
	 private String name;
	 private String surname;
	 private String email;
	 private Integer mobile;
	 private String address;
	 private String town;
	 private String province;
	 private String zipCode;
	 private String dni;
	 private String allergies;
	 private String observations;	 

}

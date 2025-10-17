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
@Table(name="clientes")
public class Customer {
	
	 @Id//Anotacion para decirle que es un atributo id de la tabla
	 @GeneratedValue(strategy = GenerationType.IDENTITY)//Para el autoincremento y PK
	 @Column(name="idCliente")//Nombre de la columna en la BD
	 private Integer idCustomer;
	 
	 @Column(name="nombre")//Nombre de la columna en la BD
	 private String name;
	 
	 @Column(name="apellidos")//Nombre de la columna en la BD
	 private String surname;
	 
	 @Column(name="email")//Nombre de la columna en la BD
	 private String email;
	 
	 @Column(name="telefono")//Nombre de la columna en la BD
	 private Integer mobile;
	 
	 @Column(name="direccion")//Nombre de la columna en la BD
	 private String address;
	 
	 @Column(name="poblacion")//Nombre de la columna en la BD
	 private String town;
	 
	 @Column(name="provincia")//Nombre de la columna en la BD
	 private String province;
	 
	 @Column(name="c.p.")//Nombre de la columna en la BD
	 private String zipCode;
	 
	 @Column(name="dni")//Nombre de la columna en la BD
	 private String dni;
	 
	 @Column(name="alergias")//Nombre de la columna en la BD
	 private String allergies;
	 
	 @Column(name="observaciones")//Nombre de la columna en la BD
	 private String observations;	 

}

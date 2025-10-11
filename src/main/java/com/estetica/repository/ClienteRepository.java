package com.estetica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.estetica.model.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	/*
	 * Aqui usamos <Cliente, Long> y esto son parametros genericos que le indican a JpaRepository la entidad
	 * que maneja y que tipo de dato es su id. Con estos genericos, lo que hacemos es tener acceso al
	 * CRUD sin necesidad de implementarlos
	 */
	
	//Gestionamos para buscar cliente por nombre
	List<Cliente> findByNombreContainingIgnoreCase(String nombre);
 
}

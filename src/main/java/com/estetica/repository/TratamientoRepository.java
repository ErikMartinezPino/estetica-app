package com.estetica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estetica.model.Tratamientos;

public interface TratamientoRepository extends JpaRepository<Tratamientos, Long>{
	
	//Aqui las busquedas se realizaran por nombre del tratamiento
	List<Tratamientos> findByNombreContainingIgnoreCase(String nombre);

}

package com.estetica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estetica.model.Empleados;

public interface EmpleadosRepository extends JpaRepository<Empleados, Long> {
	
	//Busqueda del empleado por el nombre
	List<Empleados> findByNombreContainingIgnoreCase(String nombre);

}

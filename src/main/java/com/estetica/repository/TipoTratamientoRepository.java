package com.estetica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estetica.model.TipoTratamiento;

public interface TipoTratamientoRepository extends JpaRepository<TipoTratamiento, Long>{
	
	//Las busquedas se podran realizar por nombre del tratamiento
	List<TipoTratamiento> findByNombreContainingIgnoreCase(String nombre);
 
}

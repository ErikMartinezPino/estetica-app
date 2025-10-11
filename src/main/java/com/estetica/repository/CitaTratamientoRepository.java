package com.estetica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estetica.model.CitaTratamiento;

public interface CitaTratamientoRepository extends JpaRepository<CitaTratamiento, Long> {
	
	//La busqueda aqui la podremos hacer por la cita o por tratamiento
	List<CitaTratamiento> findByClienteId(Long idCliente);
	List<CitaTratamiento> finByTratamientoId(Long idTratamiento);

} 

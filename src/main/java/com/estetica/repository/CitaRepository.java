package com.estetica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estetica.model.Cita;

public interface CitaRepository extends JpaRepository<Cita, Long>{
	
	//Gestionamos la busqueda de las citas por cliente o por dia
	List<Cita> findByClienteId(Long idCliente);
	List<Cita> findByFecha(String fecha);

}

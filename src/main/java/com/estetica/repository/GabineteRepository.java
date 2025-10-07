package com.estetica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estetica.model.Gabinete;

public interface GabineteRepository extends JpaRepository<Gabinete, Long>{
	
	//Busqueda del gabinete por el numero
	List<Gabinete> findByNumeroGabinete(Integer numeroGabinete);

}

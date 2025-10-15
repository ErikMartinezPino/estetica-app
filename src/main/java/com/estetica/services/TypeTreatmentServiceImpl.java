package com.estetica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estetica.model.TypeTreatment;
import com.estetica.repository.TypeTreatmentRepository;

@Service
public class TypeTreatmentServiceImpl {
	
	//Primero creamos un objeto tipo de tratamiento, que se autoinyecte
	@Autowired
	private TypeTreatmentRepository typeTreatmentRepository;
	
	//Creamos el listado de tipos de tratamientos
	public List<TypeTreatment> listTypeTreatment(){
		List<TypeTreatment> typeTreatment = typeTreatmentRepository.findAll();
		return typeTreatment;
	}
	
	//Metodo para buscar un tipo de tratamiento
	public TypeTreatment searchTypeTreatment(Integer idTypeTreatment) {
		TypeTreatment typeTreatment = typeTreatmentRepository.findById(idTypeTreatment).orElse(null);
		return typeTreatment;
	}
	
	//Metodo para guardar un tipo de tratamiento
	public void saveTypeTreatment(TypeTreatment typeTreatment) {
		typeTreatmentRepository.save(typeTreatment);
	}
	
	//Metodo para eliminar un tipo de tratamiento
	public void deleteTypeTreatmenr(TypeTreatment typeTreatment) {
		typeTreatmentRepository.delete(typeTreatment);
	}

}

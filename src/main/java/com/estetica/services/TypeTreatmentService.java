package com.estetica.services;

import java.util.List;

import com.estetica.model.TypeTreatment;

public interface TypeTreatmentService {
	
	//Metodo para que se nos devuelva una lista con los tipos de tratamiento
	public List<TypeTreatment> listTypeTreatment();
	
	//Metodo para buscar un tipo de tratamiento
	public TypeTreatment searchTypeTreatment(Integer idTypeTreatment);
	
	//Metodo para guardar un tipo de tratamiento
	public void saveTypeTreatment(TypeTreatment typeTreatment);
	
	//Metodo para borrar un tipo de tratamiento
	public void deleteTypeTreatment(TypeTreatment typeTreatment);

}

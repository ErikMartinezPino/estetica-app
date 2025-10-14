package com.estetica.services;

import java.util.List;

import com.estetica.model.TreatmentAppoinment;

public interface TreatmentAppoinmentService {
	
	//Creamos la lista que se devolvera con los tipos de tratamiento
	public List<TreatmentAppoinment> listTreatmentAppoinment();
	
	//Metodo para buscar un tipo de tratamiento
	public TreatmentAppoinment searchTreatmentAppoinment(Integer idTreatmentAppoinment);
	
	//Metodo para guardar un tipo de tratamiento
	public void saveTreatmentAppoinment(TreatmentAppoinment treatmentAppoinment);
	
	//Metodo para eliminar un tipo
	public void deleteTreatmentAppoinment(TreatmentAppoinment treatmentAppoinment);
	
}

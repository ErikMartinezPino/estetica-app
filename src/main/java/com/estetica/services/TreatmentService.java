package com.estetica.services;

import java.util.List;
import com.estetica.model.Treatment;

public interface TreatmentService {
	
	//Metodo para devolver la lista con los tratamientos disponibles
	public List<Treatment> listTreatment();
	
	//Metodo para buscar un tratamiento
	public Treatment searchTreatment(Integer idTreatment);
	
	//Metodo para guardar un tratamiento
	public void saveTreatment(Treatment treatment);
	
	//Metodo para eliminar un tratamiento
	public void deleteTreatment(Treatment treatment);

}

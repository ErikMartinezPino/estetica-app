package com.estetica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estetica.model.Treatment;
import com.estetica.repository.TreatmentRepository;

@Service
public class TreatmentServiceImpl implements TreatmentService {
	
	//Creamos un objeto de tipo Treatment
	@Autowired
	private TreatmentRepository treatmentRepository;
	
	//Lista con los tratamientos
	@Override
	public List<Treatment> listTreatment(){
		List<Treatment> treatment = treatmentRepository.findAll();
		return treatment;
	}
	
	//Busqueda de un tratamiento por su id
	@Override
	public Treatment searchTreatment(Integer idTreatment) {
		Treatment treatment = treatmentRepository.findById(idTreatment).orElse(null);
		return treatment;
	}
	
	//Metodo para guardar un tratamiento
	@Override
	public void saveTreatment(Treatment treatment) {
		treatmentRepository.save(treatment);
	}
	
	//Metodo para borrar un metodo
	@Override
	public void deleteTreatment(Treatment treatment) {
		treatmentRepository.delete(treatment);
	}

}

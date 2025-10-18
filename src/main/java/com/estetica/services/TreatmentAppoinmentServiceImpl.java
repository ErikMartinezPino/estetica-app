package com.estetica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.estetica.model.TreatmentAppoinment;
import com.estetica.repository.TreatmentAppoinmentRepository;

@Service
public class TreatmentAppoinmentServiceImpl implements TreatmentAppoinmentService{
	
	//Creamos el objeto del tipo de la clase
	@Autowired
	private TreatmentAppoinmentRepository treatmentAppoinmentRepository;
	
	//Lista con los tipos de tratamiento
	@Override
	public List<TreatmentAppoinment> listTreatmentAppoinment(){
		List<TreatmentAppoinment> treatmentAppoinment = treatmentAppoinmentRepository.findAll();
		return treatmentAppoinment;
	}
	
	//Busqueda de un tratamiento/cita por id
	@Override
	public TreatmentAppoinment searchTreatmentAppoinment(Integer idTreatmentAppoinment) {
		TreatmentAppoinment treatmentAppoinment = treatmentAppoinmentRepository.findById(idTreatmentAppoinment).orElse(null);
		return treatmentAppoinment;
	}
	
	
	//Metodo para guardar tratamiento/cita
	@Override
	public void saveTreatmentAppoinment(TreatmentAppoinment treatmentAppoinment) {
		treatmentAppoinmentRepository.save(treatmentAppoinment);
	}
	
	//Metodo para eliminar un tratamiento/cita
	@Override
	public void deleteTreatmentAppoinment(Integer idTreatmentAppoinment) {
		try {
			treatmentAppoinmentRepository.deleteById(idTreatmentAppoinment);
			System.out.println("Se ha eliminado correctamente la cita/tratamiento con id: " + idTreatmentAppoinment);
		} catch (EmptyResultDataAccessException e) {
			System.out.println("No se ha encontrado una cita/tratamiento con ese id: " + idTreatmentAppoinment);
		}catch (Exception e) {
			System.out.println("Error al eliminar la cita/tratamiento con el id: " + e.getMessage());
		}
	}

}

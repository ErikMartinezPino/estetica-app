package com.estetica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estetica.exceptions.DeletionNotAllowedException;
import com.estetica.exceptions.FailedSaveException;
import com.estetica.exceptions.ResourceNotFoundException;
import com.estetica.model.TreatmentAppoinment;
import com.estetica.repository.TreatmentAppoinmentRepository;

@Service
public class TreatmentAppointmentServiceImpl implements TreatmentAppointmentService{
	
	//Creamos el objeto del tipo de la clase
	@Autowired
	private TreatmentAppoinmentRepository treatmentAppoinmentRepository;
	
	//Lista con los tipos de tratamientos
	@Override
	public List<TreatmentAppoinment> listTreatmentAppoinment(){
		List<TreatmentAppoinment> treatmentAppoinment = treatmentAppoinmentRepository.findAll();
		return treatmentAppoinment;
	}
	
	//Busqueda de un tratamiento/cita por id
	@Override
	public TreatmentAppoinment searchTreatmentAppoinment(Integer idTreatmentAppoinment) {
		TreatmentAppoinment treatmentAppoinment = treatmentAppoinmentRepository.findById(idTreatmentAppoinment).orElseThrow(() -> new ResourceNotFoundException("Tratamiento"
				+ " de cita con id " + idTreatmentAppoinment + " no encontrado"));
		return treatmentAppoinment;
	}
	
	
	//Metodo para guardar tratamiento/cita
	@Override
	public void saveTreatmentAppoinment(TreatmentAppoinment treatmentAppoinment) {
		try {
			treatmentAppoinmentRepository.save(treatmentAppoinment);
			System.out.println("Tratamientos de citas no guardados correctamente");
		} catch (FailedSaveException e) {
			System.err.println("Error al guardar el tratamiento de la cita: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Ocurrio un error inesperado al intentar guardar el tratamiento de la cita." + e.getMessage());
			throw new FailedSaveException("Error interno del servidor al guardar el tratamiento de la cita." + e);
		}
	}
	
	//Metodo para eliminar un tratamiento/cita
	@Override
	public void deleteTreatmentAppoinment(Integer idTreatmentAppoinment) {
		try {
			TreatmentAppoinment treatmentAppoinment = treatmentAppoinmentRepository.findById(idTreatmentAppoinment).orElseThrow(() -> new ResourceNotFoundException("Tratamiento de cita"
					+ "no encontrado con ese id: " + idTreatmentAppoinment));
			treatmentAppoinmentRepository.delete(treatmentAppoinment);
			System.out.println("Tratamiento de cita eliminado correctamente, con id: " + idTreatmentAppoinment);
		} catch (ResourceNotFoundException e) {
			System.err.println("Error al eliminar: " + e.getMessage());
			throw e;
		} catch (DeletionNotAllowedException e) {
			System.err.println("Error al eliminar: " + e.getMessage());
			throw e;
		} catch (Exception e) {
			System.err.println("Ocurrio un error inesperado al intentar eliminar el tratamiento de cita con id " + idTreatmentAppoinment + ": " + e.getMessage());
			throw new RuntimeException("Error interno del servidor al eliminar el tratamiento de la cita.", e);
		}
	}

}

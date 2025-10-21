package com.estetica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estetica.exceptions.DeletionNotAllowedException;
import com.estetica.exceptions.ResourceNotFoundException;
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
		Treatment treatment = treatmentRepository.findById(idTreatment).orElseThrow(() -> new ResourceNotFoundException("Tratamiento"
				+ "con id " + idTreatment + " no encontrado."));
		return treatment;
	}
	
	//Metodo para guardar un tratamiento
	@Override
	public void saveTreatment(Treatment treatment) {
		treatmentRepository.save(treatment);
	}
	
	//Metodo para borrar un metodo
	@Override
	public void deleteTreatment(Integer idTreatment) {
		try {
			Treatment treatment = treatmentRepository.findById(idTreatment).orElseThrow(() -> new ResourceNotFoundException("Tratamiento"
					+ "no encontrado con ese id: " + idTreatment));
			treatmentRepository.delete(treatment);
			System.out.println("Tratamiento eliminado correctamente, con id: " + idTreatment);
		} catch (ResourceNotFoundException e) {
			System.err.println("Error al eliminar: " + e.getMessage());
			throw e;
		} catch (DeletionNotAllowedException e) {
			System.err.println("Error al eliminar: " + e.getMessage());
			throw e;
		} catch (Exception e) {
			System.err.println("Ocurrio un error inesperado al intentar eliminar el tratamiento con id " + idTreatment + ": " + e.getMessage());
			throw new RuntimeException("Error interno del servidor al eliminar el tratamiento.", e);
		}
	}

}

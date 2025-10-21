package com.estetica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estetica.exceptions.DeletionNotAllowedException;
import com.estetica.exceptions.ResourceNotFoundException;
import com.estetica.model.TypeTreatment;
import com.estetica.repository.TypeTreatmentRepository;

@Service
public class TypeTreatmentServiceImpl implements TypeTreatmentService{
	
	//Primero creamos un objeto tipo de tratamiento, que se autoinyecte
	@Autowired
	private TypeTreatmentRepository typeTreatmentRepository;
	
	//Creamos el listado de tipos de tratamientos
	@Override
	public List<TypeTreatment> listTypeTreatment(){
		List<TypeTreatment> typeTreatment = typeTreatmentRepository.findAll();
		return typeTreatment;
	}
	
	//Metodo para buscar un tipo de tratamiento
	@Override
	public TypeTreatment searchTypeTreatment(Integer idTypeTreatment) {
		TypeTreatment typeTreatment = typeTreatmentRepository.findById(idTypeTreatment).orElseThrow(() -> new ResourceNotFoundException("Tipo de tratamiento"
				+ "con id " + idTypeTreatment + " no encontrado."));
		return typeTreatment;
	}
	
	//Metodo para guardar un tipo de tratamiento
	@Override
	public void saveTypeTreatment(TypeTreatment typeTreatment) {
		typeTreatmentRepository.save(typeTreatment);
	}
	
	//Metodo para eliminar un tipo de tratamiento
	@Override
	public void deleteTypeTreatment(Integer idTypeTreatment) {
		try {
			TypeTreatment typeTreatment = typeTreatmentRepository.findById(idTypeTreatment).orElseThrow(() -> new ResourceNotFoundException("Tipo de tratamiento"
					+ "no encontrado con ese id: " + idTypeTreatment));
			typeTreatmentRepository.delete(typeTreatment);
			System.out.println("Producto eliminado correctamente, con id: " + idTypeTreatment);
		} catch (ResourceNotFoundException e) {
			System.err.println("Error al eliminar: " + e.getMessage());
			throw e;
		} catch (DeletionNotAllowedException e) {
			System.err.println("Error al eliminar: " + e.getMessage());
			throw e;
		} catch (Exception e) {
			System.err.println("Ocurrio un error inesperado al intentar eliminar el tipo de tratamiento con id " + idTypeTreatment + ": " + e.getMessage());
			throw new RuntimeException("Error interno del servidor al eliminar el tipo de tratamiento.", e);
		}
	}

}

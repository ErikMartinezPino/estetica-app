package com.estetica.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estetica.exceptions.DeletionNotAllowedException;
import com.estetica.exceptions.FailedSaveException;
import com.estetica.exceptions.ResourceNotFoundException;
import com.estetica.model.Cabinet;
import com.estetica.repository.CabinetRepository;

@Service
public class CabinetServiceImpl implements CabinetService{
	
	//Vamos a crear un atributo privado donde el tipo de dato es CabinetRepository
	@Autowired
	private CabinetRepository cabinetRepository;
	
	//En primer lugar, creamos la lista de los gabinetes
	@Override
	public List<Cabinet> listCabinet(){
		List<Cabinet> cabinets = cabinetRepository.findAll();
		return cabinets;
	}
	
	//Vamos con la busqueda de un gabinete por su id
	@Override
	public Cabinet searchCabinetId(Integer idCabinet) {
		Cabinet cabinet = cabinetRepository.findById(idCabinet).orElseThrow(() -> new ResourceNotFoundException("Gabinete con id"
				+ idCabinet + "no encontrado."));
		return cabinet;
	}
	
	//Metodo para guardar los gabinetes
	@Override
	public void saveCabinet(Cabinet cabinet) {
		//Metodo para guardar una cita con captura de excepcion
		try {
			cabinetRepository.save(cabinet);
			System.out.println("Gabinete guardado correctamente");
		} catch (FailedSaveException e) {
			System.err.println("Error al guardar el gabinete: " + e.getMessage());
			throw e;
		} catch (Exception e) {
			System.err.println("Ocurrio un error inesperado al intentar guardar el gabinete." + e.getMessage());
			throw new FailedSaveException("Error interno del servidor al guardar el gabinete.", e);
		}
	}
	
	//Metodo para eliminar un gabinete
	@Override
	public void deleteCabinet(Integer idCabinet){
		//Metodo para borrar un gabinete por id
		try {
			Cabinet cabinet = cabinetRepository.findById(idCabinet).orElseThrow(() -> new ResourceNotFoundException("Gabinete"
					+ "no encontrado con ese id: " + idCabinet));
			cabinetRepository.delete(cabinet);
			System.out.println("Gabinete eliminado correctamente, con id: " + idCabinet);
		} catch (ResourceNotFoundException e) {
			System.err.println("Error al eliminar: " + e.getMessage());
			throw e;
		} catch (DeletionNotAllowedException e) {
			System.err.println("Error al eliminar: " + e.getMessage());
			throw e;
		} catch (Exception e) {
			System.err.println("Ocurrio un error inesperado al intentar eliminar el gabinete con id " + idCabinet + ": " + e.getMessage());
			throw new RuntimeException("Error interno del servidor al eliminar el gabinete.", e);
		}
	}
}

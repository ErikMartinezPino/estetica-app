package com.estetica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Cabinet searchCabinet(Integer idCabinet) {
		Cabinet cabinet = cabinetRepository.findById(idCabinet).orElse(null);
		return cabinet;
	}
	
	//Metodo para guardar los gabinetes
	@Override
	public void saveCabinet(Cabinet cabinet) {
		cabinetRepository.save(cabinet);
	}
	
	//Metodo para eliminar una cita
	@Override
	public void deleteCabinet(Cabinet cabinet){
		cabinetRepository.delete(cabinet);
	}
}

package com.estetica.services;

import java.util.List;

import com.estetica.model.Cabinet;

public interface CabinetService {
	
	//Primero creamos un metodo que nos devolvera una lista de objetos de tipo Caginete
	public List<Cabinet> listCabinet();
	
	//Metodo para buscar un gabinete
	public Cabinet searchCabinet(Integer idCabinet);
	
	//Metodo para guardar un gabinete
	public void saveCabinet(Cabinet cabinet);
	
	//Metodo para eliminar un gabinete
	public void deleteCabinet(Integer idCabinet);
}

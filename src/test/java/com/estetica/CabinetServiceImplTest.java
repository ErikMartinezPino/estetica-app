package com.estetica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.estetica.exceptions.DeletionNotAllowedException;
import com.estetica.exceptions.FailedSaveException;
import com.estetica.exceptions.ResourceNotFoundException;
import com.estetica.model.Cabinet;
import com.estetica.repository.CabinetRepository;
import com.estetica.services.CabinetServiceImpl;

class CabinetServiceImplTest {
	
	//Vamos a crear un objeto simulado de la clase CabinetRepository
	@Mock
	private CabinetRepository cabinetRepository;
	
	/*
	 * Vamos a crear la instancia real de CabinetService y le vamos a inyectar automaticamente
	 * los mocks que haya para su dependencia.
	 */
	@InjectMocks
	private CabinetServiceImpl cabinetServiceImpl;
	
	//Preparamos las anotaciones
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	//Preparamos el test para que devuelva la lista
	@Test
	void testListCabinetReturnsAllCabinets() {
		Cabinet cabinet1 = new Cabinet();
		Cabinet cabinet2 = new Cabinet();
		Cabinet cabinet3 = new Cabinet();
		
		//Le decimos a mockito que queremos que devuelva
		when(cabinetRepository.findAll()).thenReturn(Arrays.asList(cabinet1, cabinet2, cabinet3));
		
		//Creamos la lista de tipo Cabinet con los resultados
		List<Cabinet> resultCabinets = cabinetServiceImpl.listCabinet();
		
		//Verificamos que no sea nulo
		assertNotNull(resultCabinets);
		//Verificamos que el tamaño de la lista es el correcto
		assertEquals(3, resultCabinets.size());
		//Verificamos que el metodo sea llamado solo una vez
		verify(cabinetRepository).findAll();
	}
	
	 //Test para metodo de buscar por id
	@Test
	void testSearchCabinetIdReturnCabinet() {
		//Creamos un gabinete vacio
		Cabinet cabinet = new Cabinet();
		
		when(cabinetRepository.findById(1)).thenReturn(Optional.of(cabinet));
		
		//Creamos el objeto cabinet con los resultados
		Cabinet resultCabinet = cabinetServiceImpl.searchCabinetId(1);
		
		//Verificamos que no sea nulo
		assertNotNull(resultCabinet);
		//Verificamos que el resultado obtenido es el mismo objeto
		assertEquals(cabinet, resultCabinet);
		//Verifiacmos que la llamada sea una vez
		verify(cabinetRepository).findById(1);
	}

	//Test para probar error de la busqueda	
	@Test
	void testSearchCabinetIdThrowsResourceNotFoundException() {
		//Vamos a crear un error
		when(cabinetRepository.findById(99)).thenReturn(Optional.empty());
		
		//Verificamos que al llamar al metodo con id 99 se llama a la excepcion
		assertThrows(ResourceNotFoundException.class, () ->{
			cabinetServiceImpl.searchCabinetId(99);
		});
		
	}
	
	//Test para probar metodo de guardar
	@Test
	void testSaveCabinet(){
		//Creamos el objeto
		Cabinet cabinet = new Cabinet();
		
		//Llamada real al metodo del servicio
		cabinetServiceImpl.saveCabinet(cabinet);
		
		//Verificamos la llamada al metodo
		verify(cabinetRepository).save(cabinet);
	}
	
	//Test para probar fallo al guardar
	@Test
	void testSaveCabinetThrowsFailedSaveException() {
		//Creamos el objeto
		Cabinet cabinet = new Cabinet();
		
		//Simulamos el error
		when(cabinetRepository.save(cabinet)).thenThrow(new RuntimeException("Error en la base de datos."));
		
		//Verificamos que se lance nuestra excepcion
		assertThrows(FailedSaveException.class, () -> {
		    cabinetServiceImpl.saveCabinet(cabinet);
		});
	}

	
	//Test para probar el borrado de datos
	@Test
	void testDeleteCabinetDeletesSuccessfully() {
		//Creamos el objeto
		Cabinet cabinet = new Cabinet();
		
		//Simulamos el caso
		when(cabinetRepository.findById(1)).thenReturn(Optional.of(cabinet));
		
		//Llamada al metodo real
		cabinetServiceImpl.deleteCabinet(1);
	}
	
	//Comprobamos el lanzamiento de la excepcion
	@Test
	void testDeleteCabinetThrowsResourceNotFoundException() {
		//Simulamos que el repositorio no encuentra el gabinete
		when(cabinetRepository.findById(99)).thenReturn(Optional.empty());
		
		//Verificamos que al intentar borrar se lanza la excepcion
		assertThrows(ResourceNotFoundException.class, () -> {
			cabinetServiceImpl.deleteCabinet(99);
		});
	}
	
	//No puede borrar el gabinete aun existiendo
	@Test
	void testDeleteCabinetThrowsDeleteNotAllowedException() {
		Cabinet cabinet = new Cabinet();		
		when(cabinetRepository.findById(2)).thenReturn(Optional.of(cabinet));
		
		doThrow(new DeletionNotAllowedException("No se puede eliminar el gabinete")).when(cabinetRepository).delete(cabinet);
		
		assertThrows(DeletionNotAllowedException.class, () -> {
			cabinetServiceImpl.deleteCabinet(2);
		});
		
	}

}

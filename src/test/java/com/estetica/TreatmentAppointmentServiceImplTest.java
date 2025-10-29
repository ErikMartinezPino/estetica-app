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
import com.estetica.model.TreatmentAppoinment;
import com.estetica.repository.TreatmentAppoinmentRepository;
import com.estetica.services.TreatmentAppointmentServiceImpl;

public class TreatmentAppointmentServiceImplTest {
	
	//Vamos a crear el objeto simulado de la clase
	@Mock
	private TreatmentAppoinmentRepository treatmentAppoinmentRepository;
	
	//Creamos la instancia real del servicio para inyectarla
	@InjectMocks
	private TreatmentAppointmentServiceImpl treatmentAppointmentServiceImpl;
	
	//Preparamos las anotaciones
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	//Test para que devuelva la lista de objetos
	@Test
	void testListTreatmentAppointmentReturnsAllTreatmentAppointment() {
		TreatmentAppoinment treatmentAppoinment1 = new TreatmentAppoinment();
		TreatmentAppoinment treatmentAppoinment2 = new TreatmentAppoinment();
		
		//Definimos el resultado
		when(treatmentAppoinmentRepository.findAll()).thenReturn(Arrays.asList(treatmentAppoinment1, treatmentAppoinment2));
		
		//Creamos la lista de tipo Customer
		List<TreatmentAppoinment> resultTreatmentAppoinments = treatmentAppointmentServiceImpl.listTreatmentAppoinment();
		
		//Verificamos que no sea nulo
		assertNotNull(resultTreatmentAppoinments);
		//Verificamos que el tamaño de la lista es el correcto
		assertEquals(2, resultTreatmentAppoinments.size());
		//Verificamos que el metodo sea llamado solo una vez
		verify(treatmentAppoinmentRepository).findAll();
	}
	
	//Test para metodo de buscar por id
	@Test
	void testSearchTreatmentAppointmentIdReturnTreatmentAppointment() {
		//Creamos un cliente vacio
		TreatmentAppoinment treatmentAppoinment = new TreatmentAppoinment();
		
		//Definimos el resultado
		when(treatmentAppoinmentRepository.findById(1)).thenReturn(Optional.of(treatmentAppoinment));
		
		//Creamos el objeto para los resultados
		TreatmentAppoinment resultTreatmentAppoinment = treatmentAppointmentServiceImpl.searchTreatmentAppoinment(1);
		
		//Verificamos que no sea nulo
		assertNotNull(resultTreatmentAppoinment);
		//Verificamos que el resultado obtenido es el mismo objeto
		assertEquals(treatmentAppoinment, resultTreatmentAppoinment);
		//Verificamos que la llamada se haga solo una vez
		verify(treatmentAppoinmentRepository).findById(1);
	}
	
	//Test para probar error en la busqueda
	@Test
	void testSearchTreatmentAppointmentIdThrowResourceNotFoundException() {
		//Vamos a crear el error
		when(treatmentAppoinmentRepository.findById(99)).thenReturn(Optional.empty());
		
		//Verificamos que al llamar al metodo con id 99 se llama a la excepcion
		assertThrows(ResourceNotFoundException.class, () ->{
			treatmentAppointmentServiceImpl.searchTreatmentAppoinment(99);
		});
	}
	
	//Test para probar el metodo guardar
	@Test
	void testSaveTreatmentAppointment() {
		//Creamos el objeto
		TreatmentAppoinment treatmentAppoinment = new TreatmentAppoinment();
		
		//Llamada real al metodo del servicio
		treatmentAppointmentServiceImpl.saveTreatmentAppoinment(treatmentAppoinment);
		
		//Verificamos la llamada al metodo
		verify(treatmentAppoinmentRepository).save(treatmentAppoinment);
	}
	
	//Test para probar fallo al guardar
	@Test
	void testSaveTreatmentAppointmentThrowsFailedSaveException() {
		//Creamos el objeto
		TreatmentAppoinment treatmentAppoinment = new TreatmentAppoinment();
		
		//Simulamos el error
		when(treatmentAppoinmentRepository.save(treatmentAppoinment)).thenThrow(new RuntimeException("Error en la base de datos."));
		
		//Verificamos que se lance nuestra excepcion
		assertThrows(FailedSaveException.class, () -> {
			treatmentAppointmentServiceImpl.saveTreatmentAppoinment(treatmentAppoinment);
		});
	}
	
	//Test para probar el borrado de datos
	@Test
	void testDeleteTreatmentAppointmentDeleteSuccessfull() {
		//Creamos el objeto
		TreatmentAppoinment treatmentAppoinment = new TreatmentAppoinment();
		
		//Simulamos el caso a probar
		when(treatmentAppoinmentRepository.findById(1)).thenReturn(Optional.of(treatmentAppoinment));
		
		//Llamada al metodo real
		treatmentAppointmentServiceImpl.deleteTreatmentAppoinment(1);
	}
	
	//Test para probar el lanzamiento de la excepcion
	@Test
	void testDeleteTreatmentAppointmentThrowResourceNotFoundExceptio() {
		//Simulamos que el repositorio no encuentra el tratamiento de la cita
		when(treatmentAppoinmentRepository.findById(99)).thenReturn(Optional.empty());
		
		//Verificamos que al intentar borrar se lanza la excepcion
		assertThrows(ResourceNotFoundException.class, () -> {
			treatmentAppointmentServiceImpl.deleteTreatmentAppoinment(99);
		});
	}
	
	//Test para probar error si no se puede borrar el tipo de tratamiento aun existiendo
	@Test
	void testDeleteTreatmentAppointmentThrowsDeleteNotAllowedException() {
		//Creamos el objeto
		TreatmentAppoinment treatmentAppoinment = new TreatmentAppoinment();
		
		//Creamos el error
		when(treatmentAppoinmentRepository.findById(2)).thenReturn(Optional.of(treatmentAppoinment));
		
		doThrow(new DeletionNotAllowedException("No se puede borrar el tipo de tratamiento.")).when(treatmentAppoinmentRepository).delete(treatmentAppoinment);
		
		assertThrows(DeletionNotAllowedException.class, () ->{
			treatmentAppointmentServiceImpl.deleteTreatmentAppoinment(2);
		});
	}
}

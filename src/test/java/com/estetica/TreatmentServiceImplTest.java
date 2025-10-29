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
import com.estetica.model.Treatment;
import com.estetica.repository.TreatmentRepository;
import com.estetica.services.TreatmentServiceImpl;

public class TreatmentServiceImplTest {

	//Vamos a crear el objeto simulado de la clase
		@Mock
		private TreatmentRepository treatmentRepository;
		
		//Creamos la instancia real del servicio para inyectarla
		@InjectMocks
		private TreatmentServiceImpl treatmentServiceImpl;
		
		//Preparamos las anotaciones
		@BeforeEach
		void setUp() {
			MockitoAnnotations.openMocks(this);
		}
		
		//Test para que devuelva la lista de objetos
		@Test
		void testListTreatmentReturnsAllTreatment() {
			Treatment treatment1 = new Treatment();
			Treatment treatment2 = new Treatment();
			
			//Definimos el resultado
			when(treatmentRepository.findAll()).thenReturn(Arrays.asList(treatment1, treatment2));
			
			//Creamos la lista de tipo Treatment
			List<Treatment> resultTreatment = treatmentServiceImpl.listTreatment();
			
			//Verificamos que no sea nulo
			assertNotNull(resultTreatment);
			//Verificamos que el tamaño de la lista es el correcto
			assertEquals(2, resultTreatment.size());
			//Verificamos que el metodo sea llamado solo una vez
			verify(treatmentRepository).findAll();
		}
		
		//Test para metodo de buscar por id
		@Test
		void testSearchTreatmentIdReturnTreatment() {
			//Creamos un tratamiento vacio
			Treatment treatment = new Treatment();
			
			//Definimos el resultado
			when(treatmentRepository.findById(1)).thenReturn(Optional.of(treatment));
			
			//Creamos el objeto para los resultados
			Treatment resultTreatment = treatmentServiceImpl.searchTreatment(1);
			
			//Verificamos que no sea nulo
			assertNotNull(resultTreatment);
			//Verificamos que el resultado obtenido es el mismo objeto
			assertEquals(treatment, resultTreatment);
			//Verificamos que la llamada se haga solo una vez
			verify(treatmentRepository).findById(1);
		}
		
		//Test para probar error en la busqueda
		@Test
		void testSearchTreatmentIdThrowResourceNotFoundException() {
			//Vamos a crear el error
			when(treatmentRepository.findById(99)).thenReturn(Optional.empty());
			
			//Verificamos que al llamar al metodo con id 99 se llama a la excepcion
			assertThrows(ResourceNotFoundException.class, () ->{
				treatmentServiceImpl.searchTreatment(99);
			});
		}
		
		//Test para probar el metodo guardar
		@Test
		void testSaveTreatment() {
			//Creamos el objeto
			Treatment treatment = new Treatment();
			
			//Llamada real al metodo del servicio
			treatmentServiceImpl.saveTreatment(treatment);
			
			//Verificamos la llamada al metodo
			verify(treatmentRepository).save(treatment);
		}
		
		//Test para probar fallo al guardar
		@Test
		void testSaveTreatmentThrowsFailedSaveException() {
			//Creamos el objeto
			Treatment treatment = new Treatment();
			
			//Simulamos el error
			when(treatmentRepository.save(treatment)).thenThrow(new RuntimeException("Error en la base de datos."));
			
			//Verificamos que se lance nuestra excepcion
			assertThrows(FailedSaveException.class, () -> {
				treatmentServiceImpl.saveTreatment(treatment);
			});
		}
		
		//Test para probar el borrado de datos
		@Test
		void testDeleteTreatmentDeleteSuccessfull() {
			//Creamos el objeto
			Treatment treatment = new Treatment();
			
			//Simulamos el caso a probar
			when(treatmentRepository.findById(1)).thenReturn(Optional.of(treatment));
			
			//Llamada al metodo real
			treatmentServiceImpl.deleteTreatment(1);
		}
		
		//Test para probar el lanzamiento de la excepcion
		@Test
		void testDeleteTreatmentThrowResourceNotFoundExceptio() {
			//Simulamos que el repositorio no encuentra el tratamiento
			when(treatmentRepository.findById(99)).thenReturn(Optional.empty());
			
			//Verificamos que al intentar borrar se lanza la excepcion
			assertThrows(ResourceNotFoundException.class, () -> {
				treatmentServiceImpl.deleteTreatment(99);
			});
		}
		
		//Test para probar error si no se puede borrar el tratamiento aun existiendo
		@Test
		void testDeleteTreatmentThrowsDeleteNotAllowedException() {
			//Creamos el objeto
			Treatment treatment = new Treatment();
			
			//Creamos el error
			when(treatmentRepository.findById(2)).thenReturn(Optional.of(treatment));
			
			doThrow(new DeletionNotAllowedException("No se puede borrar el tratamiento.")).when(treatmentRepository).delete(treatment);
			
			assertThrows(DeletionNotAllowedException.class, () ->{
				treatmentServiceImpl.deleteTreatment(2);
			});
		}
}

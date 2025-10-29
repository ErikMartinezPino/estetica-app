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
import com.estetica.model.TypeTreatment;
import com.estetica.repository.TypeTreatmentRepository;
import com.estetica.services.TypeTreatmentServiceImpl;

public class TypeTreatmentServiceImplTest {
	
		//Vamos a crear el objeto simulado de la clase
		@Mock
		private TypeTreatmentRepository typeTreatmentRepository;
		
		//Creamos la instancia real del servicio para inyectarla
		@InjectMocks
		private TypeTreatmentServiceImpl typeTreatmentServiceImpl;
		
		//Preparamos las anotaciones
		@BeforeEach
		void setUp() {
			MockitoAnnotations.openMocks(this);
		}
		
		//Test para que devuelva la lista de objetos
		@Test
		void testListTypeTreatmentReturnsAllTypeTreatment() {
			TypeTreatment typeTreatment1 = new TypeTreatment();
			TypeTreatment typeTreatment2 = new TypeTreatment();
			
			//Definimos el resultado
			when(typeTreatmentRepository.findAll()).thenReturn(Arrays.asList(typeTreatment1, typeTreatment2));
			
			//Creamos la lista de tipo Customer
			List<TypeTreatment> resultTypeTreatment = typeTreatmentServiceImpl.listTypeTreatment();
			
			//Verificamos que no sea nulo
			assertNotNull(resultTypeTreatment);
			//Verificamos que el tamaño de la lista es el correcto
			assertEquals(2, resultTypeTreatment.size());
			//Verificamos que el metodo sea llamado solo una vez
			verify(typeTreatmentRepository).findAll();
		}
		
		//Test para metodo de buscar por id
		@Test
		void testSearchTypeTreatmentIdReturnTypeTreatment() {
			//Creamos un cliente vacio
			TypeTreatment typeTreatment = new TypeTreatment();
			
			//Definimos el resultado
			when(typeTreatmentRepository.findById(1)).thenReturn(Optional.of(typeTreatment));
			
			//Creamos el objeto para los resultados
			TypeTreatment resultTypeTreatment = typeTreatmentServiceImpl.searchTypeTreatment(1);
			
			//Verificamos que no sea nulo
			assertNotNull(resultTypeTreatment);
			//Verificamos que el resultado obtenido es el mismo objeto
			assertEquals(typeTreatment, resultTypeTreatment);
			//Verificamos que la llamada se haga solo una vez
			verify(typeTreatmentRepository).findById(1);
		}
		
		//Test para probar error en la busqueda
		@Test
		void testSearchTypeTreatmentIdThrowResourceNotFoundException() {
			//Vamos a crear el error
			when(typeTreatmentRepository.findById(99)).thenReturn(Optional.empty());
			
			//Verificamos que al llamar al metodo con id 99 se llama a la excepcion
			assertThrows(ResourceNotFoundException.class, () ->{
				typeTreatmentServiceImpl.searchTypeTreatment(99);
			});
		}
		
		//Test para probar el metodo guardar
		@Test
		void testSaveTypeTreatment() {
			//Creamos el objeto
			TypeTreatment typeTreatment = new TypeTreatment();
			
			//Llamada real al metodo del servicio
			typeTreatmentServiceImpl.saveTypeTreatment(typeTreatment);
			
			//Verificamos la llamada al metodo
			verify(typeTreatmentRepository).save(typeTreatment);
		}
		
		//Test para probar fallo al guardar
		@Test
		void testSaveTypeTreatmentThrowsFailedSaveException() {
			//Creamos el objeto
			TypeTreatment typeTreatment = new TypeTreatment();
			
			//Simulamos el error
			when(typeTreatmentRepository.save(typeTreatment)).thenThrow(new RuntimeException("Error en la base de datos."));
			
			//Verificamos que se lance nuestra excepcion
			assertThrows(FailedSaveException.class, () -> {
				typeTreatmentServiceImpl.saveTypeTreatment(typeTreatment);
			});
		}
		
		//Test para probar el borrado de datos
		@Test
		void testDeleteTypeTreatmentDeleteSuccessfull() {
			//Creamos el objeto
			TypeTreatment typeTreatment = new TypeTreatment();
			
			//Simulamos el caso a probar
			when(typeTreatmentRepository.findById(1)).thenReturn(Optional.of(typeTreatment));
			
			//Llamada al metodo real
			typeTreatmentServiceImpl.deleteTypeTreatment(1);
		}
		
		//Test para probar el lanzamiento de la excepcion
		@Test
		void testDeleteTypeTreatmentThrowResourceNotFoundExceptio() {
			//Simulamos que el repositorio no encuentra el tipo de tratamiento
			when(typeTreatmentRepository.findById(99)).thenReturn(Optional.empty());
			
			//Verificamos que al intentar borrar se lanza la excepcion
			assertThrows(ResourceNotFoundException.class, () -> {
				typeTreatmentServiceImpl.deleteTypeTreatment(99);
			});
		}
		
		//Test para probar error si no se puede borrar el tipo de tratamiento aun existiendo
		@Test
		void testDeleteTypeTreatmentThrowsDeleteNotAllowedException() {
			//Creamos el objeto
			TypeTreatment typeTreatment = new TypeTreatment();
			
			//Creamos el error
			when(typeTreatmentRepository.findById(2)).thenReturn(Optional.of(typeTreatment));
			
			doThrow(new DeletionNotAllowedException("No se puede borrar el tipo de tratamiento.")).when(typeTreatmentRepository).delete(typeTreatment);
			
			assertThrows(DeletionNotAllowedException.class, () ->{
				typeTreatmentServiceImpl.deleteTypeTreatment(2);
			});
		}

}

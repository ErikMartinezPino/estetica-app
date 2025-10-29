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
import com.estetica.model.Employee;
import com.estetica.repository.EmployeeRepository;
import com.estetica.services.EmployeeServiceImpl;

public class EmployeeServiceImplTest {
	
	//Creamos el objeto simulado
	@Mock
	private EmployeeRepository employeeRepository;
	
	//Creamos la instancia real para inyectarla
	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;
	
	//Preparamos las anotaciones
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	//Test para que devuelva la lista de objetos
	@Test
	void testListEmployeeReturnAllEmployee() {
		//Creamos dos empleados para el test
		Employee employee1 = new Employee();
		Employee employee2 = new Employee();
		
		//Definimos el resultado
		when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));
		
		//Creamos la lista
		List<Employee> resultEmployees = employeeServiceImpl.listEmployee();
		
		//Verificamos que no sea nulo
		assertNotNull(resultEmployees);
		//Verificamos que el tamaño de la lista es el correcto
		assertEquals(2, resultEmployees.size());
		//Verificamos que el metodo sea llamado solo una vez
		verify(employeeRepository).findAll();
	}
	
	//Test para buscar por id
	@Test
	void testSearchEmployeeIdReturnEmployee() {
		//Creamos el empleado
		Employee employee = new Employee();
		
		//Definimos el resultado
		when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
		
		//Creamos el objeto para los resultados
		Employee resultEmployee = employeeServiceImpl.searchEmployee(1);
		
		//Verificamos que no sea nulo
		assertNotNull(resultEmployee);
		//Verificamos que el resultado es el mismo objeto
		assertEquals(employee, resultEmployee);
		//Verificamos que la llamada la hace solo una vez
		verify(employeeRepository).findById(1);
	}
	
	//Test para probar errores en la busqueda
	@Test
	void testSearchEmployeeThrowResourceNotFoundException() {
		//Vamos a crear el error
		when(employeeRepository.findById(99)).thenReturn(Optional.empty());
		
		//Verificamos que al llamar al metodo salta la excepcion
		assertThrows(ResourceNotFoundException.class, () ->{
			employeeServiceImpl.searchEmployee(99);
		});
	}
	
	//Test para probar el metodo de guardar
	@Test
	void testSaveEmployee() {
		//Creamos el objeto empleado
		Employee employee = new Employee();
		
		//Llamada al metodo real del servicio
		employeeServiceImpl.saveEmployee(employee);
		
		//Verificamos la llamada al metodo
		verify(employeeRepository).save(employee);
	}
	
	//Test para probar fallo al guardar
	@Test
	void testSaveEmployeeThrowsFailedSaveException() {
		//Creamos el  objeto
		Employee employee = new Employee();
		
		//Simulamos el error
		when(employeeRepository.save(employee)).thenThrow(new RuntimeException("Error en la base de datos."));
		
		//Verificamos que se lance nuestra excepcion
		assertThrows(FailedSaveException.class, () ->{
			employeeServiceImpl.saveEmployee(employee);
		});
	}
	
	//Test para probar el borrado de datos
		@Test
		void testDeleteEmployeeDeleteSuccessfull() {
			//Creamos el objeto
			Employee employee = new Employee();
			
			//Simulamos el caso a probar
			when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
			
			//Llamada al metodo real
			employeeServiceImpl.deleteEmployee(1);
		}
		
		//Test para probar el lanzamiento de la excepcion
		@Test
		void testDeleteEmployeeThrowResourceNotFoundExceptio() {
			//Simulamos que el repositorio no encuentra el empleado
			when(employeeRepository.findById(99)).thenReturn(Optional.empty());
			
			//Verificamos que al intentar borrar se lanza la excepcion
			assertThrows(ResourceNotFoundException.class, () -> {
				employeeServiceImpl.deleteEmployee(99);
			});
		}
		
		//Test para probar error si no se puede borrar el empleado aun existiendo
		@Test
		void testDeleteEmployeeThrowsDeleteNotAllowedException() {
			//Creamos el objeto
			Employee employee = new Employee();
			
			//Creamos el error
			when(employeeRepository.findById(2)).thenReturn(Optional.of(employee));
			
			doThrow(new DeletionNotAllowedException("No se puede borrar el empleado.")).when(employeeRepository).delete(employee);
			
			assertThrows(DeletionNotAllowedException.class, () ->{
				employeeServiceImpl.deleteEmployee(2);
			});
		}	
}

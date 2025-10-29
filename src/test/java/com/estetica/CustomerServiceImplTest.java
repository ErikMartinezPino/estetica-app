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
import com.estetica.model.Customer;
import com.estetica.repository.CustomerRepository;
import com.estetica.services.CustomerServiceImpl;

public class CustomerServiceImplTest {
	
	//Vamos a crear el objeto simulado
	@Mock
	private CustomerRepository customerRepository;
	
	//Creamos la instancia real del servicio para inyectarla
	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;
	
	//Preparamos las anotaciones
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	//Test para que devuelva la lista de objetos
	@Test
	void testListCustomerReturnsAllCustomer() {
		Customer customer1 = new Customer();
		Customer customer2 = new Customer();
		
		//Definimos el resultado
		when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));
		
		//Creamos la lista de tipo Customer
		List<Customer> resultCustomers = customerServiceImpl.listCustomer();
		
		//Verificamos que no sea nulo
		assertNotNull(resultCustomers);
		//Verificamos que el tamaño de la lista es el correcto
		assertEquals(2, resultCustomers.size());
		//Verificamos que el metodo sea llamado solo una vez
		verify(customerRepository).findAll();
	}
	
	//Test para metodo de buscar por id
	@Test
	void testSearchCustomerIdReturnCustomer() {
		//Creamos un cliente vacio
		Customer customer = new Customer();
		
		//Definimos el resultado
		when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
		
		//Creamos el objeto para los resultados
		Customer resultCustomer = customerServiceImpl.searchCustomerId(1);
		
		//Verificamos que no sea nulo
		assertNotNull(resultCustomer);
		//Verificamos que el resultado obtenido es el mismo objeto
		assertEquals(customer, resultCustomer);
		//Verificamos que la llamada se haga solo una vez
		verify(customerRepository).findById(1);
	}
	
	//Test para probar error en la busqueda
	@Test
	void testSearchCustomerIdThrowResourceNotFoundException() {
		//Vamos a crear el error
		when(customerRepository.findById(99)).thenReturn(Optional.empty());
		
		//Verificamos que al llamar al metodo con id 99 se llama a la excepcion
		assertThrows(ResourceNotFoundException.class, () ->{
			customerServiceImpl.searchCustomerId(99);
		});
	}
	
	//Test para probar el metodo guardar
	@Test
	void testSaveCustomer() {
		//Creamos el objeto
		Customer customer = new Customer();
		
		//Llamada real al metodo del servicio
		customerServiceImpl.saveCustomer(customer);
		
		//Verificamos la llamada al metodo
		verify(customerRepository).save(customer);
	}
	
	//Test para probar fallo al guardar
	@Test
	void testSaveCustomerThrowsFailedSaveException() {
		//Creamos el objeto
		Customer customer = new Customer();
		
		//Simulamos el error
		when(customerRepository.save(customer)).thenThrow(new RuntimeException("Error en la base de datos."));
		
		//Verificamos que se lance nuestra excepcion
		assertThrows(FailedSaveException.class, () -> {
			customerServiceImpl.saveCustomer(customer);
		});
	}
	
	//Test para probar el borrado de datos
	@Test
	void testDeleteCustomerDeleteSuccessfull() {
		//Creamos el objeto
		Customer customer = new Customer();
		
		//Simulamos el caso a probar
		when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
		
		//Llamada al metodo real
		customerServiceImpl.deleteCustomer(1);
	}
	
	//Test para probar el lanzamiento de la excepcion
	@Test
	void testDeleteCustomerThrowResourceNotFoundExceptio() {
		//Simulamos que el repositorio no encuentra el cliente
		when(customerRepository.findById(99)).thenReturn(Optional.empty());
		
		//Verificamos que al intentar borrar se lanza la excepcion
		assertThrows(ResourceNotFoundException.class, () -> {
			customerServiceImpl.deleteCustomer(99);
		});
	}
	
	//Test para probar error si no se puede borrar el cliente aun existiendo
	@Test
	void testDeleteCustomerThrowsDeleteNotAllowedException() {
		//Creamos el objeto
		Customer customer = new Customer();
		
		//Creamos el error
		when(customerRepository.findById(2)).thenReturn(Optional.of(customer));
		
		doThrow(new DeletionNotAllowedException("No se puede borrar el cliente.")).when(customerRepository).delete(customer);
		
		assertThrows(DeletionNotAllowedException.class, () ->{
			customerServiceImpl.deleteCustomer(2);
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}

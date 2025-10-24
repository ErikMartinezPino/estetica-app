package com.estetica;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
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
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import com.estetica.exceptions.DeletionNotAllowedException;
import com.estetica.model.Appointment;
import com.estetica.repository.AppointmentRepository;
import com.estetica.services.AppointmentServiceImpl;

class AppointmentServiceImplTest {

	//Vamos a crear un objeto simulado(mock) de la clase AppointmentRepository
	@Mock
	private AppointmentRepository appointmentRepository;
	
	/*
	 * Vamos a crear una instancia real de AppointmentService y se le va a inyectar automaticamente
	 * los mocks que haya para su dependencia.
	 * Mockito primero crea el objeto simulado, luego crea la instancia real e inyecta el mock
	 * appointmentRepository dentro de appointmentService. Esto hace que AppointmentServiceImpl pueda
	 * probar la logica sin tener que conectarse a la BD
	 */
	@InjectMocks
	private AppointmentServiceImpl appointmentServiceImpl;
	
	/*
	 * Esta anotacion indica que se tiene que ejecutar antes de cada test. Lo que hace openMocks
	 * es decirle a Mockito que coja todas las anotaciones Mock y Injec y las prepare para usarlas
	 */
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	/*
	 * Este test va a verificar que el metodo listAppointment devuelva una lista con las citas
	 * cuando el repositorio tenga datos. Importante la anotacion @Test. El nombre que tiene este
	 * test seria algo asi como test de listAppointment que devuelve todas las citas.
	 */
	@Test
	void testListAppointmentReturnsAllAppointments() {
		//Creamos dos objetos de tipo Appointment. Sin datos pero son validos para la prueba
		Appointment a1 = new Appointment();
		Appointment a2 = new Appointment();
		/*
		 * Aqui le estamos diciendo a Mockito, que es quien simula el comportamiento, que cuando
		 * llamemos al metodo findAll, de la clase repository, nos tiene que devolver una lista
		 * con las dos citas que creamos anteriormente. Al simularlo, evitamos que se conecte a
		 * la BD real.
		 */
		when(appointmentRepository.findAll()).thenReturn(Arrays.asList(a1,a2));
		
		/*
		 * Aqui creamos una lista de objetos de tipo Appointmen, le ponemos de nombre result y le
		 * metemos dentro los resultados de la llamada al metodo real listAppointment. Realmente,
		 * lo que se va a usar es appointmentRepository.findAll, que ya lo hemos definido antes.
		 * el resultado deberia de ser la lista con los dos elementos.
		 */
		List<Appointment> result = appointmentServiceImpl.listAppointment();
		
		//Este verifica que el resultado no sea nulo
		assertNotNull(result);
		//Aqui se comprueba que el metodo findAll fue llamado solo una vez
		verify(appointmentRepository).findAll();
	}
	
	/*
	 * Vamos ahora con el metodo search. El nombre igual que antes.
	 */
	@Test
	void testSearchAppointmentIdReturnsAppointment() {
		//Creamos una cita, aunque sea vacia, para probar el metodo
		Appointment appointment = new Appointment();
		/*
		 * Aqui le decimos que CUANDO se llame al metodo findById(1), este devuelva un optional
		 * con la cita que creamos antes.
		 */
		when(appointmentRepository.findById(1)).thenReturn(Optional.of(appointment));
		
		/*
		 * Aqui creamos un objeto de tipo Appointment, de nombre result, y llamamos al metodo
		 * real search de la clase AppointmentServiceImpl. De nuevo como antes, internamente
		 * va a usar el metodo de la clase repopsitory.
		 */
		Appointment result = appointmentServiceImpl.searchAppoinmentId(1);
		
		//Verifica que el resultado no sea nulo
		assertNotNull(result);
		//Verifica que el metodo fue llamado con el id(1)
		verify(appointmentRepository).findById(1);
	}
	 /*
	  * Con este metodo vamos a probar que se lanza bien la exception que hicimos, cuando el
	  * repositorio no encuentra una cita con ese id
	  */
	@Test
	void testSearchAppointmentIdThrowsResourceNotFoundException() {
		//Aqui le dice que cuando llame a findById y le pase el 99, devuelva un optional
		//es decir, que no hay cita con ese id
		when(appointmentRepository.findById(99)).thenReturn(Optional.empty());
		
		//Aqui se verifica que al llamar al metodo con el id 99, se llama a la excepcion
		assertThrows(ResourceNotFoundException.class, () ->{
			appointmentServiceImpl.searchAppoinmentId(99);
		});
	}
	
	/*
	 * Verificamos que el metodo save se ejecuta correctamente
	 */
	@Test
	void testSaveAppointmentCallsRepositorySave() {
		//Creacion del objeto
		Appointment appointment = new Appointment();
		//Llamada real al metodo del servicio
		appointmentServiceImpl.saveAppointment(appointment);
		
		//Verifica la llamada al metodo
		verify(appointmentRepository).save(appointment);
	}
	
	/*
	 * Test para comprobar que el metodo delete borra correctamente una cita
	 * con el id que se le proporciona. Aqui comprobamos que el servicio busca
	 * la cita por el id. Que si la encuentra, la elimina usando el repositorio,
	 * y que el repositorio recibe la cita correcta para eliminar
	 */
	@Test
	void testDeleteAppointmentDeletesSuccessfully() {
		Appointment appointment = new Appointment();
		when(appointmentRepository.findById(1)).thenReturn(Optional.of(appointment));
		
		appointmentServiceImpl.deleteAppointment(1);
		
		verify(appointmentRepository).delete(appointment);
	}
	
	/*
	 * Comprobamos el lanzamiento de una de las excepciones personalizadas que
	 * hemos creado. En este caso, por no encontrar la cita por ese id
	 */
	@Test
	void testDeleteAppointmentThrowsResourceNotFoundException() {
		//Simula que el repositorio no encuentra la cita, devuelve un empty, vacio.
		when(appointmentRepository.findById(99)).thenReturn(Optional.empty());
		
		//Verifica que al intentar borrar, el sistema manda la excepcion
		assertThrows(ResourceNotFoundException.class, () -> {
			appointmentServiceImpl.deleteAppointment(99);
		});
	}
	
	/*
	 * Test para lanzar una excepcion cuando quiere borrar una cita y no puede aunque
	 * la cita exista.
	 */
	@Test
	void testDeleteAppointmentThrowsDeleteNotAllowedException() {
		Appointment appointment = new Appointment();
		when(appointmentRepository.findById(2)).thenReturn(Optional.of(appointment));
		
		doThrow(new DeletionNotAllowedException("No se puede eliminar")).when(appointmentRepository).delete(appointment);
		
		assertThrows(DeletionNotAllowedException.class, () -> {
			appointmentServiceImpl.deleteAppointment(2);
		});
	}
}

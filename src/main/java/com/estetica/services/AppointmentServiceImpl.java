package com.estetica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estetica.model.Appointment;
import com.estetica.model.Customer;
import com.estetica.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService{
	
	@Autowired
    //Vamos a crear un atributo privado donde el tipo de dato es AppointmentRepository
	private AppointmentRepository appointmentRepository;
	
	@Override
	public List<Appointment> listAppoinment(){
		//Primero creamos la lista de citas
		List<Appointment> appointments = appointmentRepository.findAll();
		return appointments;
	}
	
	@Override
    public Appointment searchAppoinmentId(Integer idAppointment) {
        /*
        Como no necesitamos recibir todos los datos del cliente, vamos a modificar el
        metodo en la clase CustomerServiceImpl para que ese metodo solo necesite un dato
        de tipo Integer y que sea el idCliente. Definimos una variable de cliente, y usando
        ña clase cleinteRepositorio vamos a llamar al metodo findById dandole el id
        del cliente que estamos buscando. Pero con este metodo vamos a recibir un dato
        de tipo Optional y entonces, para poder regresar un valor, mandamos llamar el
        metodo orElseo para indicar que en caso de que el cliente que estamos buscando
        no tenga registro en la BD, regrese null y sino, pues regresa el objeto cliente
        que haya encontrado
        */
        Appointment appointment = appointmentRepository.findById(idAppointment).orElse(null);
        return appointment;
    }
	
	public void saveAppointment(Appointment appointment) {
		/*
        Este metodo tb es muy sencillo con Spring. Vamos a usar la clase clienteRepositorio
        y usamos el metodo save
        */
		appointmentRepository.save(appointment);		
	}
	
	public void deleteAppointment(Appointment appointment) {
		/*
		 * Metodo con el que vamos a poder borrar una cita.
		 */
		appointmentRepository.delete(Appointment appointment);
	}

}

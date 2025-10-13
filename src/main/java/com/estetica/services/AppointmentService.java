package com.estetica.services;

import java.util.List;

import com.estetica.model.Appointment;


public interface AppointmentService {
	
	//Primero, metodo que devolvera una lista de objetos de tipo cita
    public List<Appointment> listAppoinment();
    
    //Metodo que va a devolver un objeto tipo Cita, para buscarlos que recibe
    //objeto de tipo Cita
    public Appointment searchAppoinmentId(Integer idCita);
    
    /*
    Metodo para guardar cita que recibe objeto tipo Cita. Vamos a usar el mismo
    metodo tanto para guardar una cita nueva como para actualizar una cita. La
    diferencia se realiza de manera interna ya que si el valor de la PK del objeto 
    cita que esta recibiendo el metodo es igual a null, entonces se va a hacer una
    inserccion, pero si el valor es diferente de nulo entonces se va a realizar una
    actualizacion del registro. Esto es ventaja de usar JPA
    */
    public void saveAppointment(Appointment appointment);
    
    //Metodo para eliminar una cita
    public void deleteAppointment(Appointment appointment);

}

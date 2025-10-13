package com.estetica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estetica.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

}

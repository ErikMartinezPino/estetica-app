package com.estetica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estetica.model.Customer;

public interface AppointmentRepository extends JpaRepository<Customer, Integer>{

}

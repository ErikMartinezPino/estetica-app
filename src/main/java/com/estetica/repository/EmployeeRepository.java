package com.estetica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estetica.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}

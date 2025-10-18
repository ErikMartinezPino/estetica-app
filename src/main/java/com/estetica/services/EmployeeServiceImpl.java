package com.estetica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.estetica.model.Employee;
import com.estetica.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	//Creamos un objeto de tipo Employee
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//Lista con los empleados
	@Override
	public List<Employee> listEmployee(){
		List<Employee> employee = employeeRepository.findAll();
		return employee;
	}
	
	//Busqueda de un empleado por el id
	@Override
	public Employee searchEmployee(Integer idEmployee) {
		Employee employee = employeeRepository.findById(idEmployee).orElse(null);
		return employee;
	}
	
	//Metodo para guardar un empleado
	@Override
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	//Metodo para eliminar un empleado
	@Override
	public void deleteEmployee(Integer idEmployee) {
		try {
			employeeRepository.deleteById(idEmployee);
			System.out.println("Se ha eliminado correctamente el empleado con id: " + idEmployee);
		} catch (EmptyResultDataAccessException e) {
			System.out.println("No se ha encontrado un empleado con ese id: " + idEmployee);
		}catch (Exception e) {
			System.out.println("Error al eliminar el empleado con el id: " + e.getMessage());
		}
	}

}

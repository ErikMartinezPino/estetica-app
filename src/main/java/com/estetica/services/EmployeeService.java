package com.estetica.services;

import java.util.List;

import com.estetica.model.Employee;

public interface EmployeeService {
	
	//Primero creamos el metodo que nos devolvera una lista de objetos de tipo Employee
	public List<Employee> listEmployee();
	
	//Creamos el metodo para buscar un empleado
	public Employee searchEmployee(Integer idEmployee);
	
	//Metodo para guardar un empleado
	public void saveEmployee(Employee employee);
	
	//Metodo para eliminar un empleado
	public void deleteEmployee(Employee employee);

}

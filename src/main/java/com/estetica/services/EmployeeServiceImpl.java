package com.estetica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estetica.exceptions.DeletionNotAllowedException;
import com.estetica.exceptions.FailedSaveException;
import com.estetica.exceptions.ResourceNotFoundException;
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
		Employee employee = employeeRepository.findById(idEmployee).orElseThrow(() -> new ResourceNotFoundException("Empleado"
				+ "con id " + idEmployee + " no encontrado."));
		return employee;
	}
	
	//Metodo para guardar un empleado
	@Override
	public void saveEmployee(Employee employee) {
		try {
			employeeRepository.save(employee);
			System.out.println("Empleado guardado correctamente");
		} catch (FailedSaveException e) {
			System.err.println("Error al guardar el empleado: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Ocurrio un error inesperado al intentar guardar el empleado." + e.getMessage());
			throw new FailedSaveException("Error interno del servidor al guardar el empleado." + e);
		}
		
	}
	
	//Metodo para eliminar un empleado
	@Override
	public void deleteEmployee(Integer idEmployee) {
		try {
			Employee employee = employeeRepository.findById(idEmployee).orElseThrow(() -> new ResourceNotFoundException("Empleado"
					+ "no encontrado con ese id: " + idEmployee));
			employeeRepository.delete(employee);
			System.out.println("Empleado eliminado correctamente, con id: " + idEmployee);
		} catch (ResourceNotFoundException e) {
			System.err.println("Error al eliminar: " + e.getMessage());
			throw e;
		} catch (DeletionNotAllowedException e) {
			System.err.println("Error al eliminar: " + e.getMessage());
			throw e;
		} catch (Exception e) {
			System.err.println("Ocurrio un error inesperado al intentar eliminar el empleado con id " + idEmployee + ": " + e.getMessage());
			throw new RuntimeException("Error interno del servidor al eliminar el empleado.", e);
		}
	}

}

package com.estetica.services;

import java.util.List;
import com.estetica.model.Empleados;

public interface EmpleadosService {
	List<Empleados> listarTodos();
	Empleados obtenerPorId(Long id);
	Empleados guardar(Empleados empleados);
	void eliminar(Long id);

}

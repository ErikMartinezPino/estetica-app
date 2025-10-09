package com.estetica.mapper;

import org.springframework.stereotype.Component;

import com.estetica.dto.EmpleadosDTO;
import com.estetica.model.Empleados;

@Component
public class EmpleadosMapper {
	
	public EmpleadosDTO toDto(Empleados empleados) {
        EmpleadosDTO dto = new EmpleadosDTO();
        dto.setId(empleados.getId());
        dto.setNombre(empleados.getNombre());
        dto.setApellidos(empleados.getApellidos());
        dto.setTelefono(empleados.getTelefono());
        dto.setEmail(empleados.getEmail());
        return dto;
    }

	/*
	 * Este metodo convierte un objeto ClienteDTO recibido desde el frontend en un objeto
	 * Cliente que se pueda guardar en la BD
	 */
    public Empleados toEntity(EmpleadosDTO dto) {
        Empleados empleados = new Empleados();
        empleados.setId(dto.getId());
        empleados.setNombre(dto.getNombre());
        empleados.setApellidos(dto.getApellidos());
        empleados.setTelefono(dto.getTelefono());
        empleados.setEmail(dto.getEmail());
        return empleados;
    }

}

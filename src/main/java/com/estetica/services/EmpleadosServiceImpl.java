package com.estetica.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.estetica.model.Empleados;
import com.estetica.repository.EmpleadosRepository;

@Service
public class EmpleadosServiceImpl implements EmpleadosService{
	
	private final EmpleadosRepository repo;

    //Constructor con inyección de dependencias
    public EmpleadosServiceImpl(EmpleadosRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Empleados> listarTodos() {
        return repo.findAll();
    }

    @Override
    public Empleados obtenerPorId(Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    }

    @Override
    public Empleados guardar(Empleados empleados) {
        return repo.save(empleados);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }

}

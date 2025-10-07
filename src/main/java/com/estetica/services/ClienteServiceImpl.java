package com.estetica.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.estetica.model.Cliente;
import com.estetica.repository.ClienteRepository;

/*
 * Esta es la clase con la implementacion real. La interfaz solo define que se puede hacer,
 * es la clase *ServiceImpl la que dice como se hace.
 */

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repo;

    //Constructor con inyección de dependencias
    public ClienteServiceImpl(ClienteRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Cliente> listarTodos() {
        return repo.findAll();
    }

    @Override
    public Cliente obtenerPorId(Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return repo.save(cliente);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}

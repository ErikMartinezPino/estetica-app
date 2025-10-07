package com.estetica.mapper;

import org.springframework.stereotype.Component;

import com.estetica.dto.ClienteDTO;
import com.estetica.model.Cliente;

/*
 * Esta es la clase que convierte de Entity a DTO y viceversa. Y eso es mapear
 */

@Component
public class ClienteMapper {
	
	/*
	 * Este metodo convierte un objeto Cliente de la BD en un ClienteDTO, el cual es el
	 * objeto que se va a enviar la frontend
	 */
	public ClienteDTO toDto(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setApellidos(cliente.getApellidos());
        dto.setMovil(cliente.getMovil());
        dto.setEmail(cliente.getEmail());
        return dto;
    }

	/*
	 * Este metodo convierte un objeto ClienteDTO recibido desde el frontend en un objeto
	 * Cliente que se pueda guardar en la BD
	 */
    public Cliente toEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNombre(dto.getNombre());
        cliente.setApellidos(dto.getApellidos());
        cliente.setMovil(dto.getMovil());
        cliente.setEmail(dto.getEmail());
        return cliente;
    }

}

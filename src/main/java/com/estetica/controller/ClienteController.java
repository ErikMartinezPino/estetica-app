package com.estetica.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estetica.dto.ClienteDTO;
import com.estetica.mapper.ClienteMapper;
import com.estetica.services.ClienteService;

/*
 * RestController le dice a Spring que esta clase es un controlador Rest, es decir, que recibe y responde
 * peticiones HTTP (GET, POST, PUT, DELETE), entra en juego nuestro querido postman. Los datos son devueltos
 * en formato JSON.
 * @RequestMapping("/api/clientes") Define la URL base de todos los endpoints de este controlador.Así, todas
 * las rutas comenzarán con /api/clientes, por ejemplo:
 * GET /api/clientes
 * GET /api/clientes/1
 * POST /api/clientes
 * etc.
 * @CrossOrigin(origins = "http://localhost:4200") Permite que el frontend Angular (que corre en localhost:4200)
 * pueda hacer peticiones al backend (que normalmente está en localhost:8080). Sin esto, el navegador bloquearía
 * las peticiones por política CORS.
 */
@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:4200") //Anotacion para Angular
public class ClienteController {

	/*
	 * Vamos a inyectar automaticamente las dependencias. Por un lado a ClienteService que tiene la logica del negocio,
	 * y por otro lado, a ClienteMapper que es la que transforma entre cliente y dto. Asi separamos la logica. El
	 * controlador solo se encarga de recibir y devolver datos.
	 */
	private final ClienteService clienteService;
	private final ClienteMapper clienteMapper;

	public ClienteController(ClienteService clienteService, ClienteMapper clienteMapper) {
		this.clienteService = clienteService;
		this.clienteMapper = clienteMapper;
	}

	/*
	 * Devuelve la lista de todos los clientes.
	 * Llama a clienteService.listarTodos() y devuelve List<Cliente>.
	 * Luego usa el mapper para convertir cada Cliente a ClienteDTO.
	 * .stream().map(clienteMapper::toDto).toList() es una forma de transformar listas en Java.
	 * Resultado: JSON con todos los clientes.
	 */
	@GetMapping
	public List<ClienteDTO> listar() {
		return clienteService.listarTodos().stream().map(clienteMapper::toDto).toList();
	}

	/*
	 * Busca un cliente por su ID (el valor que va en la URL).
	 * @PathVariable indica que el valor del id viene de la URL.
	 * Convierte el Cliente en un ClienteDTO antes de devolverlo.
	 */
	@GetMapping("/{id}")
	public ClienteDTO obtener(@PathVariable Long id) {
		return clienteMapper.toDto(clienteService.obtenerPorId(id));
	}

	/*
	 * Crea un nuevo cliente.
	 * @RequestBody indica que los datos vienen en el cuerpo de la petición (JSON).
	 * Se convierte el DTO a entidad, se guarda y luego se devuelve como DTO.
	 */
	@PostMapping
	public ClienteDTO crear(@RequestBody ClienteDTO dto) {
		return clienteMapper.toDto(clienteService.guardar(clienteMapper.toEntity(dto)));
	}

	/*
	 * Actualiza un cliente existente.
	 * Igual que el POST, pero aquí se pasa también el id de la URL.
	 * Se usa el mismo guardar() porque si existe el ID, JPA hace un update.
	 */
	@PutMapping("/{id}")
	public ClienteDTO actualizar(@PathVariable Long id, @RequestBody ClienteDTO dto) {
		dto.setId(id);
		return clienteMapper.toDto(clienteService.guardar(clienteMapper.toEntity(dto)));
	}

	/*
	 * Elimina un cliente por el id
	 */
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		clienteService.eliminar(id);
	}
}

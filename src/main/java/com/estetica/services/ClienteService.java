package com.estetica.services;

import java.util.List;

import com.estetica.model.Cliente;

/*
 * Esto es una interfaz en la que vamos a definir las operaciones que se podran hacer, en este caso,
 * con los clientes. Es como una lista de funciones disponibles para trabajar, pero sin decir que es
 * lo que va a hacer o como lo va a hacer.
 * Esta capa se situa entre el controller y el repositorio. Controller recibe la peticion HTTP, por
 * ejemplo, dame todos los clientes. Service contiene la logica del negocio, por ejemplo validar los
 * datos o procesar algo. Y por ultimo, Repository, lo que hace es hablar con la BD para hacer las querys
 */

public interface ClienteService {
	List<Cliente> listarTodos();
	Cliente obtenerPorId(Long id);
	Cliente guardar(Cliente cliente);
	void eliminar(Long id);

}

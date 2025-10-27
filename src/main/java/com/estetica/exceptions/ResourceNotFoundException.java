package com.estetica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Excepcion que se lanzara cuando se intente acceder o manipular un recurso que
 * no existe en la BD. Es una excepcion no comprobada, por lo que no se necesita
 * declararla con throws ni capturarla con try catch.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)//Esto indica  que dede de devolver
public class ResourceNotFoundException extends RuntimeException {
	
	/*
	 * Este constructor nos va a permitir lanzar la excepcion con un mensaje
	 * personalizado
	 */
	public ResourceNotFoundException(String message) {
		/*
		 * La palabra super va a llamar al constructor de la clase padre para que
		 * cree ese String que es el mensaje y lo muestre.
		 */
		super(message);
	}
	
	/*
	 * Este constructor permite lanzar la excepcion con un mensaje personalizado
	 * y la causa que la genero.
	 */
	public ResourceNotFoundException(String message, Throwable cause) {
		/*
		 * La palabra super va a llamar al constructor de la clase padre para que
		 * cree ese String que es el mensaje y lo muestre.
		 */
		super(message, cause);
	}

}

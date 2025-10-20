package com.estetica.exceptions;

/*
 * Esta excepcion saltara cuando un recurso exista en la BD pero no pueda
 * ser eliminado
 */
public class DeletionNotAllowedException extends RuntimeException{
	
	public DeletionNotAllowedException(String message) {
		super(message);
	}
	
	public DeletionNotAllowedException(String message, Throwable cause) {
		super(message, cause);
	}

}

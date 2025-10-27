package com.estetica.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FailedSaveException extends RuntimeException {
	
	public FailedSaveException(String message) {
		super(message);
	}
	
	public FailedSaveException(String message, Throwable cause) {
		super(message, cause);
	}

}

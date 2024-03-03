package com.spring.user.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada para representar un error cuando un usuario ya existe.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ExistingUserException extends RuntimeException {

    /**
     * Constructor de la excepción.
     *
     * @param message El mensaje de error asociado a la excepción.
     */
    public ExistingUserException(String message) {
        super(message);
    }
}

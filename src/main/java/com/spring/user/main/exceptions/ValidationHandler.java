package com.spring.user.main.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase para manejar excepciones de validación en los controladores REST.
 */
@RestControllerAdvice
public class ValidationHandler {

    /**
     * Maneja las excepciones de validación de argumentos del método.
     *
     * @param ex La excepción MethodArgumentNotValidException lanzada.
     * @return ResponseEntity con la respuesta de error.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.put("mensaje", errorMessage);
        });

        return ResponseEntity.badRequest().body(errors);
    }
}

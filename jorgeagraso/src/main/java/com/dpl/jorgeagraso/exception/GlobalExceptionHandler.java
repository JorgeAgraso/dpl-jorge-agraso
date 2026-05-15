package com.dpl.jorgeagraso.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EstudianteNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleEstudianteNoEncontrado(EstudianteNoEncontradoException ex) {
        ErrorResponse body = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

        ErrorResponse body = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                "Error de validación en los datos enviados",
                fieldErrors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}

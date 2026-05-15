package com.dpl.jorgeagraso.exception;

public class EstudianteNoEncontradoException extends RuntimeException {

    public EstudianteNoEncontradoException(Long id) {
        super("Estudiante no encontrado con id: " + id);
    }
}

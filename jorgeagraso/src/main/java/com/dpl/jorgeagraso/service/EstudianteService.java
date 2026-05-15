package com.dpl.jorgeagraso.service;

import com.dpl.jorgeagraso.exception.EstudianteNoEncontradoException;
import com.dpl.jorgeagraso.model.Estudiante;
import com.dpl.jorgeagraso.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository repository;

    public Estudiante guardarEstudiante(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    public List<Estudiante> obtenerTodos() {
        return repository.findAll();
    }

    public Estudiante obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EstudianteNoEncontradoException(id));
    }

    public Estudiante actualizar(Long id, Estudiante datos) {
        Estudiante existente = obtenerPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setEmail(datos.getEmail());
        existente.setEdad(datos.getEdad());
        return repository.save(existente);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new EstudianteNoEncontradoException(id);
        }
        repository.deleteById(id);
    }

}

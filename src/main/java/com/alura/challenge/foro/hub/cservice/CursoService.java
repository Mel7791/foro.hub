package com.alura.challenge.foro.hub.cservice;

import com.alura.challenge.foro.hub.domain.curso.Curso;
import com.alura.challenge.foro.hub.domain.curso.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Curso crearCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }
}

package com.alura.challenge.foro.hub.controller;

import com.alura.challenge.foro.hub.domain.curso.Curso;
import com.alura.challenge.foro.hub.cservice.CursoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curso")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // GET: List all courses
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<Curso> listarCursos() {
        return cursoService.listarCursos();
    }

    // POST: Create a new course
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public Curso crearCurso(@RequestBody Curso curso) {
        return cursoService.crearCurso(curso);
    }

    // GET: Get a course by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public Curso obtenerCursoPorId(@PathVariable Long id) {
        return cursoService.obtenerCursoPorId(id);
    }

    // DELETE: Delete a course by ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public void eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
    }
}

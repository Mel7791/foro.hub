package com.alura.challenge.foro.hub.domain.curso;

public record CursoDTO(

        Long id,
        String nombre,
        Categoria categoria) {

    public CursoDTO(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
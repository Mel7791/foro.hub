package com.alura.challenge.foro.hub.domain.curso;

import jakarta.validation.constraints.NotNull;

public record DatosRegistrarCurso(
        @NotNull
        String nombre,
        @NotNull
        Categoria categoria
) {
}

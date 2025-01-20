package com.alura.challenge.foro.hub.domain.topico;

import com.alura.challenge.foro.hub.domain.curso.CursoDTO;
import com.alura.challenge.foro.hub.domain.usuario.UsuarioDTO;
import jakarta.validation.constraints.NotNull;

public record TopicoDTO(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        String fechaCreacion,
        String status,
        UsuarioDTO autor,
        CursoDTO curso
) {
}

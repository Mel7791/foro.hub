package com.alura.challenge.foro.hub.cservice;

import com.alura.challenge.foro.hub.domain.curso.Curso;
import com.alura.challenge.foro.hub.domain.topico.Topico;
import com.alura.challenge.foro.hub.domain.usuario.Usuario;
import com.alura.challenge.foro.hub.domain.topico.TopicoRequest;
import com.alura.challenge.foro.hub.domain.curso.CursoRepository;
import com.alura.challenge.foro.hub.domain.topico.TopicoRepository;
import com.alura.challenge.foro.hub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Topico createTopico(TopicoRequest topicoRequest) {
        Usuario autor = usuarioRepository.findById(topicoRequest.getAutorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Curso curso = cursoRepository.findById(topicoRequest.getCursoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));

        if (topicoRepository.existsByTituloAndMensaje(topicoRequest.getTitulo(), topicoRequest.getMensaje())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tópico duplicado");
        }

        Topico topico = new Topico();
        topico.setTitulo(topicoRequest.getTitulo());
        topico.setMensaje(topicoRequest.getMensaje());
        topico.setStatus("ABIERTO"); // Estado inicial del tópico
        topico.setAutor(autor);
        topico.setCurso(curso);

        return topicoRepository.save(topico);
    }

    public Page<Topico> getTopicos(int page, int size) {
        return topicoRepository.findAll(PageRequest.of(page, size, Sort.by("fechaCreacion").ascending()));
    }

    public Topico getTopicoById(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));
    }

    public Topico updateTopico(Long id, TopicoRequest topicoRequest) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));

        if (topicoRepository.existsByTituloAndMensaje(topicoRequest.getTitulo(), topicoRequest.getMensaje()) &&
                (!topico.getTitulo().equals(topicoRequest.getTitulo()) || !topico.getMensaje().equals(topicoRequest.getMensaje()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tópico duplicado");
        }

        Usuario autor = usuarioRepository.findById(topicoRequest.getAutorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Curso curso = cursoRepository.findById(topicoRequest.getCursoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));

        topico.setTitulo(topicoRequest.getTitulo());
        topico.setMensaje(topicoRequest.getMensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);

        return topicoRepository.save(topico);
    }

    public void deleteTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));

        topicoRepository.deleteById(topico.getId());
    }
}

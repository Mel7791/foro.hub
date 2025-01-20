package com.alura.challenge.foro.hub.controller;

import com.alura.challenge.foro.hub.domain.topico.Topico;
import com.alura.challenge.foro.hub.domain.topico.TopicoRequest;
import com.alura.challenge.foro.hub.cservice.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topico")
@Validated
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public Topico createTopico(@Valid @RequestBody TopicoRequest topicoRequest) {
        return topicoService.createTopico(topicoRequest);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public Page<Topico> getTopicos(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return topicoService.getTopicos(page, size);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public Topico getTopicoById(@PathVariable Long id) {
        return topicoService.getTopicoById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public Topico updateTopico(@PathVariable Long id, @Valid @RequestBody TopicoRequest topicoRequest) {
        return topicoService.updateTopico(id, topicoRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    public void deleteTopico(@PathVariable Long id) {
        topicoService.deleteTopico(id);

    }
}

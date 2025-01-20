package com.alura.challenge.foro.hub.controller;

import com.alura.challenge.foro.hub.domain.usuario.Usuario;
import com.alura.challenge.foro.hub.cservice.UsuarioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Endpoint para listar todos los usuarios
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    // Endpoint para obtener un usuario por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public Usuario obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    // Endpoint para crear un nuevo usuario
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    // Endpoint para actualizar un usuario existente
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(id, usuario);
    }

    // Endpoint para eliminar un usuario por ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }
}

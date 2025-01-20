package com.alura.challenge.foro.hub.cservice;

import com.alura.challenge.foro.hub.domain.usuario.Usuario;
import com.alura.challenge.foro.hub.domain.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + id));
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = obtenerUsuarioPorId(id);
        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setEmail(usuarioActualizado.getEmail());
        usuarioExistente.setContrasena(usuarioActualizado.getContrasena());
        return usuarioRepository.save(usuarioExistente);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}

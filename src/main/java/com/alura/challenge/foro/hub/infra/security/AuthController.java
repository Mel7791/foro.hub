package com.alura.challenge.foro.hub.infra.security;

import com.alura.challenge.foro.hub.domain.usuario.DatosAutenticacion;
import com.alura.challenge.foro.hub.domain.usuario.Usuario;
import com.alura.challenge.foro.hub.domain.usuario.UsuarioRepository;
import com.alura.challenge.foro.hub.infra.token.DatosJWTToken;
import com.alura.challenge.foro.hub.infra.token.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<DatosJWTToken> login(@RequestBody @Valid DatosAutenticacion datos) {
        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(datos.nombre(), datos.contrasena());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generación del token JWT
            Usuario usuario = (Usuario) authentication.getPrincipal(); // Obtener el usuario autenticado
            String jwt = tokenService.generarToken(usuario); // Generar el token

            // Devolvemos el token en el formato adecuado
            return ResponseEntity.ok(new DatosJWTToken(jwt)); // Retorna el token envuelto en el record DatosJWTToken

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new DatosJWTToken("Error: " + e.getMessage()));
        }
    }
}

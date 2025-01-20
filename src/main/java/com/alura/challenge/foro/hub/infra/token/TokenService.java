package com.alura.challenge.foro.hub.infra.token;

import com.alura.challenge.foro.hub.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String apiSecret;

    // Método para generar el token JWT
    public String generarToken(Usuario usuario) {
        try {
            // Definimos el algoritmo HMAC256 usando la clave secreta
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            // Generamos el token con la información del usuario
            return JWT.create()
                    .withIssuer("foro hub") // Emisor del token
                    .withSubject(usuario.getUsername()) // Usamos el nombre de usuario (ya que `getUsername` devuelve el nombre de usuario)
                    .withClaim("id", usuario.getId()) // Usamos el id del usuario como un claim adicional
                    .withClaim("role", "USER") //ESO FUE LO UNICO QUE AGREGUE PARA ABILITAR TOPICO Y CURSO
                    .withExpiresAt(generarFechaExpiracion()) // Fecha de expiración
                    .sign(algorithm); // Firmamos el token
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error creating JWT token", exception); // Excepción si algo falla en la creación
        }
    }

    // Método para obtener el 'subject' (nombre de usuario) desde un token JWT
    public String getSubject(String token) {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token is null or empty");
        }

        DecodedJWT decodedJWT = null;
        try {
            // Definimos el algoritmo HMAC256 usando la misma clave secreta
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            // Verificamos el token
            decodedJWT = JWT.require(algorithm)
                    .withIssuer("foro hub") // El emisor debe coincidir con el que usamos en la creación del token
                    .build()
                    .verify(token); // Verificamos el token

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token verification failed", exception); // Si la verificación falla
        }

        // Obtenemos el 'subject' del token (nombre de usuario)
        if (decodedJWT.getSubject() == null) {
            throw new RuntimeException("Invalid token, no subject found");
        }

        return decodedJWT.getSubject(); // Retornamos el nombre de usuario
    }

    private Instant generarFechaExpiracion() {
        // El token expira en 24 horas desde su creación
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-05:00"));
    }


}

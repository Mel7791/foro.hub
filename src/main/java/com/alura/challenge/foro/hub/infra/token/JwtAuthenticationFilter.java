package com.alura.challenge.foro.hub.infra.token;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.ServletException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final TokenService tokenService;

    public JwtAuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String token = extractToken(request);
        if (token != null) {
            try {
                String username = tokenService.getSubject(token);
                if (username != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(username, null, null);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (JWTVerificationException ex) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
                return;
            }
        }
        filterChain.doFilter(request, response); // eso es la unica forma de llamar al filtro!
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization"); //con eso estamos autorizando el token
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}

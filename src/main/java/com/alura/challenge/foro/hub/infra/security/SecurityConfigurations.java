package com.alura.challenge.foro.hub.infra.security;

import com.alura.challenge.foro.hub.domain.usuario.UsuarioRepository;
import com.alura.challenge.foro.hub.infra.token.JwtAuthenticationFilter;
import com.alura.challenge.foro.hub.infra.token.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    private final TokenService tokenService;

    private final UsuarioRepository usuarioRepository;

    public SecurityConfigurations(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    // Bean para AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());  // Asegúrate de usar el PasswordEncoder correcto
        return authenticationManagerBuilder.build();
    }

    // Configuración de seguridad usando SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Deshabilita CSRF para simplificar el proceso de autenticación
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/login").permitAll() // Permite acceso sin autenticación al endpoint de login
                    .requestMatchers("/topico", "/api/curso", "/api/usuario").authenticated()
                    .anyRequest().authenticated()
                )// Requiere autenticación para cualquier otra solicitud
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(new JwtAuthenticationFilter(tokenService), UsernamePasswordAuthenticationFilter.class) // Añadir el filtro de JWT
                .formLogin().disable();


        return http.build(); // Construye el filtro de seguridad
    }

    // Bean para el PasswordEncoder (en este caso BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean para el UserDetailsService, que carga un usuario por su nombre
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> usuarioRepository.findByNombre(username); // Usa el repositorio para buscar el usuario
    }
}
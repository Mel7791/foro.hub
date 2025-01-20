package com.alura.challenge.foro.hub.domain.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Builder
@Table(name="usuario")
@Entity
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    //constructores
    public Usuario() {}

    public Usuario(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        encriptarContrasena();  // Encriptamos la contraseña cuando la creamos
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.contrasena;
    }

    public void encriptarContrasena() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        this.contrasena = encoder.encode(this.contrasena);  // Encripta la contraseña
    }

    @Override
    public String getUsername() {
        return this.nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Agrega lógica si es necesario
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Agrega lógica si es necesario
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Agrega lógica si es necesario
    }

    @Override
    public boolean isEnabled() {
        return true;  // Agrega lógica si es necesario
    }

    //Getters and Setters:
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}



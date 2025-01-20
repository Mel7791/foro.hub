package com.alura.challenge.foro.hub.domain.topico;

import com.alura.challenge.foro.hub.domain.curso.Curso;
import com.alura.challenge.foro.hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name="topico")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(nullable = false, length = 50)
    private String status;

    // Use @ManyToOne with JoinColumn to specify the foreign key column
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id", referencedColumnName = "id", nullable = false)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id", referencedColumnName = "id", nullable = false)
    private Curso curso;

    //methods
    public Long getAutorId() {
        return autor.getId();
    }

    public Long getCursoId() {
        return curso.getId();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}



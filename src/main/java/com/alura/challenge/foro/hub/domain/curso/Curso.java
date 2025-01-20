package com.alura.challenge.foro.hub.domain.curso;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="curso")
@Entity
@Getter
@Setter
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @JsonCreator
    public Curso(
            @JsonProperty("id") Long id,
            @JsonProperty("nombre") String nombre,
            @JsonProperty("categoria") Categoria categoria
    ) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public Curso(DatosRegistrarCurso datosRegistrarCurso){
        this.nombre = datosRegistrarCurso.nombre();
        this.categoria = datosRegistrarCurso.categoria();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Curso(){}
}


package com.vida.azul.Domain;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "recursos")
public class RecursoCrud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recurso")
    private Long idRecurso;

    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(name = "nombre_recurso")
    private String nombreRecurso;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "imagen")
    private String imagen;

    // Constructor sin parámetros
    public RecursoCrud() {
    }

    // Constructor con parámetros
    public RecursoCrud(Long idCategoria, String nombreRecurso, String descripcion, String imagen) {
        this.idCategoria = idCategoria;
        this.nombreRecurso = nombreRecurso;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    // Getters y Setters

    public Long getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(Long idRecurso) {
        this.idRecurso = idRecurso;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreRecurso() {
        return nombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}

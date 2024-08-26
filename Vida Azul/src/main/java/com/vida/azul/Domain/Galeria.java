package com.vida.azul.Domain;

import jakarta.persistence.*;

/**
 *
 * @author Me
 */
@Entity
@Table(name = "GALERIA", schema = "USRVIDA_AZUL")
public class Galeria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_IMAGEN")
    private Long id_imagen;

    @Column(name = "ID_USUARIO")
    private Long id_usuario;

    @Column(name = "IMAGEN")
    private String imagen;

    @Column(name = "TITULO")
    private String titulo;

    public Galeria() {
    }

    public Galeria(Long id_imagen, Long id_usuario, String imagen, String titulo) {
        this.id_imagen = id_imagen;
        this.id_usuario = id_usuario;
        this.imagen = imagen;
        this.titulo = titulo;
    }

    public Galeria(Long id_usuario, String imagen, String titulo) {
        this.id_usuario = id_usuario;
        this.imagen = imagen;
        this.titulo = titulo;
    }

    public Long getId_imagen() {
        return id_imagen;
    }

    public void setId_imagen(Long id_imagen) {
        this.id_imagen = id_imagen;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Galeria{" + 
               "id_imagen=" + id_imagen + 
               ", id_usuario=" + id_usuario + 
               ", imagen='" + imagen + '\'' + 
               ", titulo='" + titulo + '\'' + 
               '}';
    }
}
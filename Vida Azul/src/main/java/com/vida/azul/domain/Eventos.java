package com.vida.azul.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "EVENTOS", schema = "USRVIDA_AZUL")
public class Eventos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EVENTO")
    private Long id_evento;

    @Column(name = "ID_CATEGORIA")
    private Long id_categoria;

    @Column(name = "NOMBRE_EVENTO")
    private String nombre_evento;

    @Column(name = "FECHA_EVENTO")
    @Temporal(TemporalType.DATE)
    private Date fecha_evento;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "IMAGEN")
    private String imagen;

    public Eventos() {
    }

    public Eventos(Long id_evento, Long id_categoria, String nombre_evento, Date fecha_evento, String descripcion, String imagen) {
        this.id_evento = id_evento;
        this.id_categoria = id_categoria;
        this.nombre_evento = nombre_evento;
        this.fecha_evento = fecha_evento;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Eventos(Long id_categoria, String nombre_evento, Date fecha_evento, String descripcion, String imagen) {
        this.id_categoria = id_categoria;
        this.nombre_evento = nombre_evento;
        this.fecha_evento = fecha_evento;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Long getId_evento() {
        return id_evento;
    }

    public void setId_evento(Long id_evento) {
        this.id_evento = id_evento;
    }

    public Long getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Long id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_evento() {
        return nombre_evento;
    }

    public void setNombre_evento(String nombre_evento) {
        this.nombre_evento = nombre_evento;
    }

    public Date getFecha_evento() {
        return fecha_evento;
    }

    public void setFecha_evento(Date fecha_evento) {
        this.fecha_evento = fecha_evento;
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

    @Override
    public String toString() {
        return "Eventos{" + 
               "id_evento=" + id_evento + 
               ", id_categoria=" + id_categoria + 
               ", nombre_evento='" + nombre_evento + '\'' + 
               ", fecha_evento=" + fecha_evento + 
               ", descripcion='" + descripcion + '\'' + 
               ", imagen='" + imagen + '\'' + 
               '}';
    }
}
package com.vida.azul.Domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "eventos")
public class EventoCrud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long idEvento;

    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(name = "nombre_evento")
    private String nombreEvento;

    @Column(name = "fecha_evento")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date fechaEvento;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "imagen")
    private String imagen;

    // Constructor con parámetros
    public EventoCrud(Long idCategoria, String nombreEvento, Date fechaEvento, String descripcion, String imagen) {
        this.idCategoria = idCategoria;
        this.nombreEvento = nombreEvento;
        this.fechaEvento = fechaEvento;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    // Constructor vacío
    public EventoCrud() {
    }

    // Getters y Setters
    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
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

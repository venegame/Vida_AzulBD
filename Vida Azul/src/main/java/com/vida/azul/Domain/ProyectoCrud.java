/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vida.azul.Domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "vida.azul")
public class ProyectoCrud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private Long idProyecto;

    @Column(name = "id_usuario")
    private Long idUsuario;  // Se almacena el ID del usuario directamente

    @Column(name = "id_categoria")
    private Long idCategoria;  // Se almacena el ID de la categoría directamente

    @Column(name = "nombre_proyecto")
    private String nombreProyecto;

    @Column(name = "detalle_proyecto")
    private String detalleProyecto;

    @Column(name = "estado_proyecto")
    private String estadoProyecto;

    @Column(name = "ruta_imagen")
    private String rutaImagen;

    public ProyectoCrud(Long idUsuario, Long idCategoria, String nombreProyecto, String detalleProyecto, String estadoProyecto, String rutaImagen) {
        this.idUsuario = idUsuario;
        this.idCategoria = idCategoria;
        this.nombreProyecto = nombreProyecto;
        this.detalleProyecto = detalleProyecto;
        this.estadoProyecto = estadoProyecto;
        this.rutaImagen = rutaImagen;
    }

    public ProyectoCrud() {
    }

    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDetalleProyecto() {
        return detalleProyecto;
    }

    public void setDetalleProyecto(String detalleProyecto) {
        this.detalleProyecto = detalleProyecto;
    }

    public String getEstadoProyecto() {
        return estadoProyecto;
    }

    public void setEstadoProyecto(String estadoProyecto) {
        this.estadoProyecto = estadoProyecto;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

}

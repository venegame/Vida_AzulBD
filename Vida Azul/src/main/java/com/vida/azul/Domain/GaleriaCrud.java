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
@Table(name = "galeria")
public class GaleriaCrud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Long idImagen;

    @Column(name = "id_usuario")
    private Long idUsuario;  // Se almacena el ID del usuario directamente

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "titulo")
    private String titulo;

    public GaleriaCrud(Long idUsuario, String imagen, String titulo) {
        this.idUsuario = idUsuario;
        this.imagen = imagen;
        this.titulo = titulo;
    }

    public GaleriaCrud() {
    }
    
    
     public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
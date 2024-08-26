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
@Table(name = "expertos")
public class ExpertoCrud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_experto")
    private Long idExperto;

    @Column(name = "id_categoria")
    private Long idCategoria;  // Se almacena el ID de la categoría directamente

    @Column(name = "nombre_experto")
    private String nombreExperto;

    @Column(name = "quienes_somos")
    private String quienesSomos;

    @Column(name = "historia_expertos")
    private String historiaExpertos;

    @Column(name = "url_instagram")
    private String urlInstagram;

    @Column(name = "url_x")
    private String urlX;

    @Column(name = "url_youtube")
    private String urlYoutube;

    @Column(name = "url_facebook")
    private String urlFacebook;
    
     public ExpertoCrud(Long idCategoria, String nombreExperto, String quienesSomos, 
                   String historiaExpertos, String urlInstagram, String urlX, 
                   String urlYoutube, String urlFacebook) {
        this.idCategoria = idCategoria;
        this.nombreExperto = nombreExperto;
        this.quienesSomos = quienesSomos;
        this.historiaExpertos = historiaExpertos;
        this.urlInstagram = urlInstagram;
        this.urlX = urlX;
        this.urlYoutube = urlYoutube;
        this.urlFacebook = urlFacebook;
    }

    // Constructor vacío
    public ExpertoCrud() {
    }
    
     public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }
}
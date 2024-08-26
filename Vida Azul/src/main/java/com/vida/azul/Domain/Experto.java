/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vida.azul.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Me
 */
@Entity
@Table(name = "EXPERTOS", schema = "USRVIDA_AZUL")
public class Experto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EXPERTO")
    private int id_experto;
    
    @Column(name = "ID_CATEGORIA")
    private int id_categoria;
    
    @Column(name = "NOMBRE_EXPERTO")
    private String nombre_experto;
    
    @Column(name = "QUIENES_SOMOS")
    private String quienes_somos;
    
    @Column(name = "HISTORIA_EXPERTOS")
    private String historia_expertos;
    
    @Column(name = "URL_INSTAGRAM")
    private String url_instagram;
    
    @Column(name = "URL_X")
    private String url_x;
    
    @Column(name = "URL_YOUTUBE")
    private String url_youtube;
    
    @Column(name = "URL_FACEBOOK")
    private String url_facebook;
    
    public Experto() {
    }

    public Experto(int id_experto, int id_categoria, String nombre_experto, String quienes_somos, String historia_expertos, String url_instagram, String url_x, String url_youtube, String url_facebook) {
        this.id_experto = id_experto;
        this.id_categoria = id_categoria;
        this.nombre_experto = nombre_experto;
        this.quienes_somos = quienes_somos;
        this.historia_expertos = historia_expertos;
        this.url_instagram = url_instagram;
        this.url_x = url_x;
        this.url_youtube = url_youtube;
        this.url_facebook = url_facebook;
    }

    public Experto(int id_categoria, String nombre_experto, String quienes_somos, String historia_expertos, String url_instagram, String url_x, String url_youtube, String url_facebook) {
        this.id_categoria = id_categoria;
        this.nombre_experto = nombre_experto;
        this.quienes_somos = quienes_somos;
        this.historia_expertos = historia_expertos;
        this.url_instagram = url_instagram;
        this.url_x = url_x;
        this.url_youtube = url_youtube;
        this.url_facebook = url_facebook;
    }

    public int getId_experto() {
        return id_experto;
    }

    public void setId_experto(int id_experto) {
        this.id_experto = id_experto;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_experto() {
        return nombre_experto;
    }

    public void setNombre_experto(String nombre_experto) {
        this.nombre_experto = nombre_experto;
    }

    public String getQuienes_somos() {
        return quienes_somos;
    }

    public void setQuienes_somos(String quienes_somos) {
        this.quienes_somos = quienes_somos;
    }

    public String getHistoria_expertos() {
        return historia_expertos;
    }

    public void setHistoria_expertos(String historia_expertos) {
        this.historia_expertos = historia_expertos;
    }

    public String getUrl_instagram() {
        return url_instagram;
    }

    public void setUrl_instagram(String url_instagram) {
        this.url_instagram = url_instagram;
    }

    public String getUrl_x() {
        return url_x;
    }

    public void setUrl_x(String url_x) {
        this.url_x = url_x;
    }

    public String getUrl_youtube() {
        return url_youtube;
    }

    public void setUrl_youtube(String url_youtube) {
        this.url_youtube = url_youtube;
    }

    public String getUrl_facebook() {
        return url_facebook;
    }

    public void setUrl_facebook(String url_facebook) {
        this.url_facebook = url_facebook;
    }

    @Override
    public String toString() {
        return "Expertos{" + "id_experto=" + id_experto + ", id_categoria=" + id_categoria + ", nombre_experto=" + nombre_experto + ", quienes_somos=" + quienes_somos + ", historia_expertos=" + historia_expertos + ", url_instagram=" + url_instagram + ", url_x=" + url_x + ", url_youtube=" + url_youtube + ", url_facebook=" + url_facebook + '}';
    }
    
}

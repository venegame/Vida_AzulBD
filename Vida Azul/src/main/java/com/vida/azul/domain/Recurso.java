/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vida.azul.domain;

import jakarta.persistence.*;

/**
 *
 * @author Me
 */
@Entity
@Table(name = "RECURSOS", schema = "USRVIDA_AZUL")
public class Recurso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RECURSO")
    private int id_recurso;
    
    @Column(name = "ID_CATEGORIA")
    private int id_Categoria;
    
    @Column(name = "NOMBRE_RECURSO")
    private String nombre_recurso;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "IMAGEN")
    private String imagen;
    
    public Recurso() {
    }

    public Recurso(int id_recurso, int id_categoria, String nombre_recurso, String descripcion, String imagen) {
        this.id_recurso = id_recurso;
        this.id_Categoria = id_categoria;
        this.nombre_recurso = nombre_recurso;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Recurso(int id_categoria, String nombre_recurso, String descripcion, String imagen) {
        this.id_Categoria = id_categoria;
        this.nombre_recurso = nombre_recurso;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public int getId_recurso() {
        return id_recurso;
    }

    public void setId_recurso(int id_recurso) {
        this.id_recurso = id_recurso;
    }

    public int getId_categoria() {
        return id_Categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_Categoria = id_categoria;
    }

    public String getNombre_recurso() {
        return nombre_recurso;
    }

    public void setNombre_recurso(String nombre_recurso) {
        this.nombre_recurso = nombre_recurso;
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
        return "Recurso{" + "id_recurso=" + id_recurso + ", id_categoria=" + id_Categoria + ", nombre_recurso=" + nombre_recurso + ", descripcion=" + descripcion + ", imagen=" + imagen + '}';
    }
    
}

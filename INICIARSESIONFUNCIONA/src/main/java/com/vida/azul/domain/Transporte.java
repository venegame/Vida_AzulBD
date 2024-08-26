/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vida.azul.domain;

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
@Table(name = "TRANSPORTES", schema = "USRVIDA_AZUL")
public class Transporte {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRANSPORTE")
    private int id_transporte;
    
    @Column(name = "ID_USUARIO")
    private int id_usuario;
    
    @Column(name = "NOMBRE_TRANSPORTE")
    private String nombre_transporte;
    
    @Column(name = "RUTA_TRANSPORTE")
    private String ruta_transporte;
    
    @Column(name = "HORARIO_TRANSPORTE")
    private String horario_transporte;
    
    @Column(name = "PRECIO_TRANSPORTE")
    private int precio_transporte;
    
    public Transporte() {
    }

    public Transporte(int id_transporte, int id_usuario, String nombre_transporte, String ruta_transporte, String horario_transporte, int precio_transporte) {
        this.id_transporte = id_transporte;
        this.id_usuario = id_usuario;
        this.nombre_transporte = nombre_transporte;
        this.ruta_transporte = ruta_transporte;
        this.horario_transporte = horario_transporte;
        this.precio_transporte = precio_transporte;
    }

    public Transporte(int id_usuario, String nombre_transporte, String ruta_transporte, String horario_transporte, int precio_transporte) {
        this.id_usuario = id_usuario;
        this.nombre_transporte = nombre_transporte;
        this.ruta_transporte = ruta_transporte;
        this.horario_transporte = horario_transporte;
        this.precio_transporte = precio_transporte;
    }

    public int getId_transporte() {
        return id_transporte;
    }

    public void setId_transporte(int id_transporte) {
        this.id_transporte = id_transporte;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_transporte() {
        return nombre_transporte;
    }

    public void setNombre_transporte(String nombre_transporte) {
        this.nombre_transporte = nombre_transporte;
    }

    public String getRuta_transporte() {
        return ruta_transporte;
    }

    public void setRuta_transporte(String ruta_transporte) {
        this.ruta_transporte = ruta_transporte;
    }

    public String getHorario_transporte() {
        return horario_transporte;
    }

    public void setHorario_transporte(String horario_transporte) {
        this.horario_transporte = horario_transporte;
    }

    public int getPrecio_transporte() {
        return precio_transporte;
    }

    public void setPrecio_transporte(int precio_transporte) {
        this.precio_transporte = precio_transporte;
    }

    @Override
    public String toString() {
        return "Transporte{" + "id_transporte=" + id_transporte + ", id_usuario=" + id_usuario + ", nombre_transporte=" + nombre_transporte + ", ruta_transporte=" + ruta_transporte + ", horario_transporte=" + horario_transporte + ", precio_transporte=" + precio_transporte + '}';
    }
    
}

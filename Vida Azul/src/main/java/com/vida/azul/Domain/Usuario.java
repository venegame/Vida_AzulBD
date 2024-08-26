/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.vida.azul.Domain;

import jakarta.persistence.*;

/**
 *
 * @author Me
 */
@Entity
@Table(name = "USUARIO", schema = "USRVIDA_AZUL")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long id_usuario;
    
    @Column(name = "ID_ROL")
    private Long id_rol;
    
    @Column(name = "NOMBRE_USUARIO")
    private String nombre_usuario;
    
    @Column(name = "APELLIDO_USUARIO")
    private String apellido_usuario;
    
    @Column(name = "CORREO")
    private String correo;
    
    @Column(name = "CONTRASENIA")
    private String contrasenia;
    
    public Usuario() {
    }

    public Usuario(Long id_usuario, Long id_rol, String nombre_usuario, String apellido_usuario, String correo, String contrasenia) {
        this.id_usuario = id_usuario;
        this.id_rol = id_rol;
        this.nombre_usuario = nombre_usuario;
        this.apellido_usuario = apellido_usuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public Usuario(Long id_rol, String nombre_usuario, String apellido_usuario, String correo, String contrasenia) {
        this.id_rol = id_rol;
        this.nombre_usuario = nombre_usuario;
        this.apellido_usuario = apellido_usuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Long getId_rol() {
        return id_rol;
    }

    public void setId_rol(Long id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getApellido_usuario() {
        return apellido_usuario;
    }

    public void setApellido_usuario(String apellido_usuario) {
        this.apellido_usuario = apellido_usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return 
                "Usuario{" + 
                "id_usuario=" + id_usuario + 
                ", id_rol=" + id_rol + 
                ", nombre_usuario=" + nombre_usuario + 
                ", apellido_usuario=" + apellido_usuario + 
                ", correo=" + correo + 
                ", contrasenia=" + contrasenia + '}';
    }
    
}

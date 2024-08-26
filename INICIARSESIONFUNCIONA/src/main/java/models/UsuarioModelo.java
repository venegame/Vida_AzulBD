/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author cadam
 */
public class UsuarioModelo {
    private Long id_usuario;
    private Long id_rol;
    private String nombre_usuario;
    private String apellido_usuario;
    private String correo;
    private String contrasenia;
    
    public UsuarioModelo() {
    }

    public UsuarioModelo(Long id_usuario, String nombre_usuario) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
    }
    
    public UsuarioModelo(Long id_usuario, Long id_rol, String nombre_usuario, String apellido_usuario, String correo, String contrasenia) {
        this.id_usuario = id_usuario;
        this.id_rol = id_rol;
        this.nombre_usuario = nombre_usuario;
        this.apellido_usuario = apellido_usuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public UsuarioModelo(Long id_rol, String nombre_usuario, String apellido_usuario, String correo, String contrasenia) {
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
}

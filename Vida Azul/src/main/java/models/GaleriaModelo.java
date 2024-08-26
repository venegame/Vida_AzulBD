/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author cadam
 */
public class GaleriaModelo {
    private Long id_imagen;

    private Long id_usuario;

    private String imagen;

    private String titulo;
    
   private UsuarioModelo usuario;

    public GaleriaModelo() {
    }

    public GaleriaModelo(Long id_imagen, Long id_usuario, String imagen, String titulo) {
        this.id_imagen = id_imagen;
        this.id_usuario = id_usuario;
        this.imagen = imagen;
        this.titulo = titulo;
    }
    
    public GaleriaModelo (Long id_imagen, String imagen, String titulo, UsuarioModelo usuario) {
        this.id_imagen = id_imagen;
        this.imagen = imagen;
        this.titulo = titulo;
        this.usuario = usuario;
    }

    public GaleriaModelo(Long id_usuario, String imagen, String titulo) {
        this.id_usuario = id_usuario;
        this.imagen = imagen;
        this.titulo = titulo;
    }

    public Long getId_imagen() {
        return id_imagen;
    }

    public void setId_imagen(Long id_imagen) {
        this.id_imagen = id_imagen;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public UsuarioModelo getUsuario() {
        return usuario;
    }
    
    public void setUsuario(UsuarioModelo usuario) {
        this.usuario = usuario;
    }
}
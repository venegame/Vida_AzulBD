package models;

import java.util.Date;

/**
 *
 * @author cadam
 */
public class EventoModelo {
    private Long id_evento;
    private Long id_categoria;
    private String nombre_evento;
    private Date fecha_evento;
    private String descripcion;
    private String imagen;

    private UsuarioModelo usuario;  // Este campo se mantiene si aún necesitas el objeto UsuarioModelo

    public EventoModelo() {
    }

    public EventoModelo(Long id_evento, Long id_categoria, String nombre_evento, Date fecha_evento, String descripcion, String imagen) {
        this.id_evento = id_evento;
        this.id_categoria = id_categoria;
        this.nombre_evento = nombre_evento;
        this.fecha_evento = fecha_evento;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public EventoModelo(Long id_evento, String nombre_evento, Date fecha_evento, String descripcion, String imagen, UsuarioModelo usuario) {
        this.id_evento = id_evento;
        this.nombre_evento = nombre_evento;
        this.fecha_evento = fecha_evento;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.usuario = usuario;
    }

    public Long getId_evento() {
        return id_evento;
    }

    public void setId_evento(Long id_evento) {
        this.id_evento = id_evento;
    }

    public Long getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Long id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_evento() {
        return nombre_evento;
    }

    public void setNombre_evento(String nombre_evento) {
        this.nombre_evento = nombre_evento;
    }

    public Date getFecha_evento() {
        return fecha_evento;
    }

    public void setFecha_evento(Date fecha_evento) {
        this.fecha_evento = fecha_evento;
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

    public UsuarioModelo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModelo usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "EventoModelo{" +
               "id_evento=" + id_evento +
               ", id_categoria=" + id_categoria +
               ", nombre_evento='" + nombre_evento + '\'' +
               ", fecha_evento=" + fecha_evento +
               ", descripcion='" + descripcion + '\'' +
               ", imagen='" + imagen + '\'' +
               ", usuario=" + usuario +
               '}';
    }
}
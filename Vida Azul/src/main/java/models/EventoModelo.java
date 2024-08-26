package models;

import java.util.Date;

public class EventoModelo {
    private Long idEvento;
    private Long idCategoria;
    private String nombreEvento;
    private Date fechaEvento;
    private String descripcion;
    private String imagen;

    public EventoModelo(Long idEvento, Long idCategoria, String nombreEvento, Date fechaEvento,
                        String descripcion, String imagen) {
        this.idEvento = idEvento;
        this.idCategoria = idCategoria;
        this.nombreEvento = nombreEvento;
        this.fechaEvento = fechaEvento;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
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
}

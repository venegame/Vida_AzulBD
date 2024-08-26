package com.vida.azul.Service;

import com.vida.azul.Domain.EventoCrud;
import java.util.List;

public interface EventoServiceCrud {
    List<EventoCrud> obtenerEventos();
    void agregarEvento(EventoCrud evento);
    void actualizarEvento(EventoCrud evento);
    void eliminarEvento(Long idEvento);
    EventoCrud buscarPorId(Long idEvento);
}

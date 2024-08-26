package com.vida.azul.Service.impl;

import com.vida.azul.Dao.EventoDaoCrud;
import com.vida.azul.Domain.EventoCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.vida.azul.Service.EventoServiceCrud;

@Service
public class EventoServiceImplCrud implements EventoServiceCrud {

    @Autowired
    private EventoDaoCrud eventoDao;

    @Override
    public List<EventoCrud> obtenerEventos() {
        return eventoDao.obtenerEventos();
    }

    @Override
    public void agregarEvento(EventoCrud evento) {
        eventoDao.agregarEvento(evento);
    }

    @Override
    public void actualizarEvento(EventoCrud evento) {
        eventoDao.actualizarEvento(evento);
    }

    @Override
    public void eliminarEvento(Long idEvento) {
        eventoDao.eliminarEvento(idEvento);
    }

    @Override
    public EventoCrud buscarPorId(Long idEvento) {
        return eventoDao.buscarPorId(idEvento);
    }
}

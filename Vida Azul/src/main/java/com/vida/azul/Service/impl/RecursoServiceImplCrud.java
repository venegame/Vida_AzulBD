package com.vida.azul.Service.impl;

import com.vida.azul.Dao.RecursoDaoCrud;
import com.vida.azul.Domain.RecursoCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.vida.azul.Service.RecursoServiceCrud;

@Service
public class RecursoServiceImplCrud implements RecursoServiceCrud {

    @Autowired
    private RecursoDaoCrud recursoDao;

    @Override
    public List<RecursoCrud> obtenerRecursos() {
        return recursoDao.obtenerRecursos();
    }

    @Override
    public void agregarRecurso(RecursoCrud recurso) {
        recursoDao.agregarRecurso(recurso);
    }

    @Override
    public void actualizarRecurso(RecursoCrud recurso) {
        recursoDao.actualizarRecurso(recurso);
    }

    @Override
    public void eliminarRecurso(Long idRecurso) {
        recursoDao.eliminarRecurso(idRecurso);
    }

    @Override
    public RecursoCrud buscarPorId(Long idRecurso) {
        return recursoDao.buscarPorId(idRecurso);
    }
}

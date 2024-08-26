package com.vida.azul.Service.impl;

import com.vida.azul.Dao.ExpertoDaoCrud;
import com.vida.azul.Domain.ExpertoCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.vida.azul.Service.ExpertoServiceCrud;

@Service
public class ExpertoServiceImplCrud implements ExpertoServiceCrud {

    @Autowired
    private ExpertoDaoCrud expertoDao;

    @Override
    public List<ExpertoCrud> obtenerExpertos() {
        return expertoDao.obtenerExpertos();
    }

    @Override
    public void agregarExperto(ExpertoCrud experto) {
        expertoDao.agregarExperto(experto);
    }

    @Override
    public void actualizarExperto(ExpertoCrud experto) {
        expertoDao.actualizarExperto(experto);
    }

    @Override
    public void eliminarExperto(Long idExperto) {
        expertoDao.eliminarExperto(idExperto);
    }

    @Override
    public ExpertoCrud buscarPorId(Long idExperto) {
        return expertoDao.buscarPorId(idExperto);
    }
}

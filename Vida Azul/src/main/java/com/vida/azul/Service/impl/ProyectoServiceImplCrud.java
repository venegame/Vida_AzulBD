package com.vida.azul.Service.impl;

import com.vida.azul.Dao.ProyectoDaoCrud;
import com.vida.azul.Domain.ProyectoCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.vida.azul.Service.ProyectoServiceCrud;

@Service
public class ProyectoServiceImplCrud implements ProyectoServiceCrud {

    @Autowired
    private ProyectoDaoCrud proyectoDao;

    @Override
    public List<ProyectoCrud> obtenerProyectos() {
        return proyectoDao.obtenerProyectos();
    }

    @Override
    public void agregarProyecto(ProyectoCrud proyecto) {
        proyectoDao.agregarProyecto(proyecto);
    }

    @Override
    public void actualizarProyecto(ProyectoCrud proyecto) {
        proyectoDao.actualizarProyecto(proyecto);
    }

    @Override
    public void eliminarProyecto(Long idProyecto) {
        proyectoDao.eliminarProyecto(idProyecto);
    }

    @Override
    public ProyectoCrud buscarPorId(Long idProyecto) {
        return proyectoDao.buscarPorId(idProyecto);
    }
}

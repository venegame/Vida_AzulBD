package com.vida.azul.Service.impl;

import com.vida.azul.Dao.RolDaoCrud;
import com.vida.azul.Domain.RolCrud;
import com.vida.azul.Service.RolServiceCrud;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImplCrud implements RolServiceCrud {

    private final RolDaoCrud rolDao;

    public RolServiceImplCrud(RolDaoCrud rolDao) {
        this.rolDao = rolDao;
    }

    @Override
    public List<RolCrud> obtenerRoles() {
        return rolDao.obtenerRoles();
    }

    @Override
    public void agregarRol(RolCrud rol) {
        rolDao.agregarRol(rol);
    }

    @Override
    public void actualizarRol(RolCrud rol) {
        rolDao.actualizarRol(rol);
    }

    @Override
    public void eliminarRol(Long idRol) {
        rolDao.eliminarRol(idRol);
    }

    @Override
    public RolCrud buscarPorId(Long idRol) {
        return rolDao.buscarPorId(idRol);
    }
}

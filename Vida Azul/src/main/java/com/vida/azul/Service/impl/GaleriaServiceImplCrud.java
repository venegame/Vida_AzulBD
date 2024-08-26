package com.vida.azul.Service.impl;

import com.vida.azul.Dao.GaleriaDaoCrud;
import com.vida.azul.Domain.GaleriaCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.vida.azul.Service.GaleriaServiceCrud;

@Service
public class GaleriaServiceImplCrud implements GaleriaServiceCrud {

    @Autowired
    private GaleriaDaoCrud galeriaDao;

    @Override
    public List<GaleriaCrud> obtenerGalerias() {
        return galeriaDao.obtenerGalerias();
    }

    @Override
    public void agregarGaleria(GaleriaCrud galeria) {
        galeriaDao.agregarGaleria(galeria);
    }

    @Override
    public void actualizarGaleria(GaleriaCrud galeria) {
        galeriaDao.actualizarGaleria(galeria);
    }

    @Override
    public void eliminarGaleria(Long idImagen) {
        galeriaDao.eliminarGaleria(idImagen);
    }

    @Override
    public GaleriaCrud buscarPorId(Long idImagen) {
        return galeriaDao.buscarPorId(idImagen);
    }
}

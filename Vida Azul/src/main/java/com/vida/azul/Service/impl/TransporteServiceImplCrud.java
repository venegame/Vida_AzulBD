package com.vida.azul.Service.impl;

import com.vida.azul.Dao.TransporteDaoCrud;
import com.vida.azul.Domain.TransporteCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.vida.azul.Service.TransporteServiceCrud;

@Service
public class TransporteServiceImplCrud implements TransporteServiceCrud {

    @Autowired
    private TransporteDaoCrud transporteDao;

    @Override
    public List<TransporteCrud> obtenerTransportes() {
        return transporteDao.obtenerTransportes();
    }

    @Override
    public void agregarTransporte(TransporteCrud transporte) {
        transporteDao.agregarTransporte(transporte);
    }

    @Override
    public void actualizarTransporte(TransporteCrud transporte) {
        transporteDao.actualizarTransporte(transporte);
    }

    @Override
    public void eliminarTransporte(Long idTransporte) {
        transporteDao.eliminarTransporte(idTransporte);
    }

    @Override
    public TransporteCrud buscarPorId(Long idTransporte) {
        return transporteDao.buscarPorId(idTransporte);
    }
}

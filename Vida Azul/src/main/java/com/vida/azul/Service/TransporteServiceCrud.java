package com.vida.azul.Service;

import com.vida.azul.Domain.TransporteCrud;
import java.util.List;

public interface TransporteServiceCrud {
    List<TransporteCrud> obtenerTransportes();
    void agregarTransporte(TransporteCrud transporte);
    void actualizarTransporte(TransporteCrud transporte);
    void eliminarTransporte(Long idTransporte);
    TransporteCrud buscarPorId(Long idTransporte);
}

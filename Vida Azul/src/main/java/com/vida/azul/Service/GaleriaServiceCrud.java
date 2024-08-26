package com.vida.azul.Service;

import com.vida.azul.Domain.GaleriaCrud;
import java.util.List;

public interface GaleriaServiceCrud {
    List<GaleriaCrud> obtenerGalerias();
    void agregarGaleria(GaleriaCrud galeria);
    void actualizarGaleria(GaleriaCrud galeria);
    void eliminarGaleria(Long idImagen);
    GaleriaCrud buscarPorId(Long idImagen);
}

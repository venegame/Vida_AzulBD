package com.vida.azul.Service;

import com.vida.azul.Domain.ExpertoCrud;
import java.util.List;

public interface ExpertoServiceCrud {
    List<ExpertoCrud> obtenerExpertos();
    void agregarExperto(ExpertoCrud experto);
    void actualizarExperto(ExpertoCrud experto);
    void eliminarExperto(Long idExperto);
    ExpertoCrud buscarPorId(Long idExperto);
}

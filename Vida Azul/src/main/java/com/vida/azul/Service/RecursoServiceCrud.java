package com.vida.azul.Service;

import com.vida.azul.Domain.RecursoCrud;
import java.util.List;

public interface RecursoServiceCrud {
    List<RecursoCrud> obtenerRecursos();
    void agregarRecurso(RecursoCrud recurso);
    void actualizarRecurso(RecursoCrud recurso);
    void eliminarRecurso(Long idRecurso);
    RecursoCrud buscarPorId(Long idRecurso);
}

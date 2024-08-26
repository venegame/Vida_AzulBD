package com.vida.azul.Service;

import com.vida.azul.Domain.ProyectoCrud;
import java.util.List;

public interface ProyectoServiceCrud {
    List<ProyectoCrud> obtenerProyectos();
    void agregarProyecto(ProyectoCrud proyecto);
    void actualizarProyecto(ProyectoCrud proyecto);
    void eliminarProyecto(Long idProyecto);
    ProyectoCrud buscarPorId(Long idProyecto);
}

package com.vida.azul.Service;

import com.vida.azul.Domain.RolCrud;

import java.util.List;

public interface RolServiceCrud {

    List<RolCrud> obtenerRoles();
    void agregarRol(RolCrud rol);
    void actualizarRol(RolCrud rol);
    void eliminarRol(Long idRol);
    RolCrud buscarPorId(Long idRol);
}

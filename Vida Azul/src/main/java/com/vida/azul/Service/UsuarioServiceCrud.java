package com.vida.azul.Service;

import com.vida.azul.Domain.UsuarioCrud;
import java.util.List;

public interface UsuarioServiceCrud {
    List<UsuarioCrud> obtenerUsuarios();
    void agregarUsuario(UsuarioCrud usuario);
    void actualizarUsuario(UsuarioCrud usuario);
    void eliminarUsuario(Long idUsuario);
    UsuarioCrud buscarPorId(Long idUsuario);
}

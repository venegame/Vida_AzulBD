package com.vida.azul.Service;

import com.vida.azul.Domain.CategoriaCrud;
import java.util.List;

public interface CategoriaServiceCrud {
    List<CategoriaCrud> obtenerCategorias();
    void agregarCategoria(CategoriaCrud categoria);
    void actualizarCategoria(CategoriaCrud categoria);
    void eliminarCategoria(Long idCategoria);
    CategoriaCrud buscarPorId(Long idCategoria);
}

package com.vida.azul.Service.impl;

import com.vida.azul.Dao.CategoriaDaoCrud;
import com.vida.azul.Domain.CategoriaCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.vida.azul.Service.CategoriaServiceCrud;

@Service
public class CategoriaServiceImplCrud implements CategoriaServiceCrud {

    @Autowired
    private CategoriaDaoCrud categoriaDao;

    @Override
    public List<CategoriaCrud> obtenerCategorias() {
        return categoriaDao.obtenerCategorias();
    }

    @Override
    public void agregarCategoria(CategoriaCrud categoria) {
        categoriaDao.agregarCategoria(categoria);
    }

    @Override
    public void actualizarCategoria(CategoriaCrud categoria) {
        categoriaDao.actualizarCategoria(categoria);
    }

    @Override
    public void eliminarCategoria(Long idCategoria) {
        categoriaDao.eliminarCategoria(idCategoria);
    }

    @Override
    public CategoriaCrud buscarPorId(Long idCategoria) {
        return categoriaDao.buscarPorId(idCategoria);
    }
}

package com.vida.azul.Service.impl;

import com.vida.azul.Dao.UsuarioDaoCrud;
import com.vida.azul.Domain.UsuarioCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.vida.azul.Service.UsuarioServiceCrud;

@Service
public class UsuarioServiceImplCrud implements UsuarioServiceCrud {

    @Autowired
    private UsuarioDaoCrud usuarioDao;

    @Override
    public List<UsuarioCrud> obtenerUsuarios() {
        return usuarioDao.obtenerUsuarios();
    }

    @Override
    public void agregarUsuario(UsuarioCrud usuario) {
        usuarioDao.agregarUsuario(usuario);
    }

    @Override
    public void actualizarUsuario(UsuarioCrud usuario) {
        usuarioDao.actualizarUsuario(usuario);
    }

    @Override
    public void eliminarUsuario(Long idUsuario) {
        usuarioDao.eliminarUsuario(idUsuario);
    }

    @Override
    public UsuarioCrud buscarPorId(Long idUsuario) {
        return usuarioDao.buscarPorId(idUsuario);
    }
}

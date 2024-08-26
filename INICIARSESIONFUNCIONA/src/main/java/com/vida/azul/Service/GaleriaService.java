package com.vida.azul.Service;

import com.vida.azul.domain.Galeria;
import com.vida.azul.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import models.GaleriaModelo;
import models.UsuarioModelo;

@Service
public class GaleriaService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GaleriaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<GaleriaModelo> obtenerImagenes() {
    String sql = "SELECT g.id_imagen, g.imagen, g.titulo, u.id_usuario, u.nombre_usuario " +
                 "FROM USRVIDA_AZUL.galeria g " +
                 "INNER JOIN USRVIDA_AZUL.usuario u ON g.id_usuario = u.id_usuario";
    return jdbcTemplate.query(sql, (rs, rowNum) -> new GaleriaModelo(
        rs.getLong("id_imagen"),
        rs.getString("imagen"),
        rs.getString("titulo"),
            new UsuarioModelo(
                rs.getLong("id_usuario"),
                rs.getString("nombre_usuario")
            )
        ));
    }
    
    public List<GaleriaModelo> obtenerImagen(Long id) {
    String sql = "SELECT g.id_imagen, g.imagen, g.titulo, u.id_usuario, u.nombre_usuario " +
                 "FROM USRVIDA_AZUL.galeria g " +
                 "INNER JOIN USRVIDA_AZUL.usuario u ON g.id_usuario = u.id_usuario" + 
                 "WHERE g.id_imagen = " + id;
    return jdbcTemplate.query(sql, (rs, rowNum) -> new GaleriaModelo(
        rs.getLong("id_imagen"),
        rs.getString("imagen"),
        rs.getString("titulo"),
            new UsuarioModelo(
                rs.getLong("id_usuario"),
                rs.getString("nombre_usuario")
            )
        ));
    }
}
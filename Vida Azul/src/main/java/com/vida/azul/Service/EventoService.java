package com.vida.azul.Service;

import com.vida.azul.domain.Eventos;
import com.vida.azul.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import models.EventoModelo;
import models.UsuarioModelo;

@Service
public class EventoService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EventoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<EventoModelo> obtenerEventos() {
        String sql = "SELECT e.id_evento, e.nombre_evento, e.fecha_evento, e.descripcion, e.imagen, u.id_usuario, u.nombre_usuario " +
                     "FROM USRVIDA_AZUL.eventos e " +
                     "INNER JOIN USRVIDA_AZUL.usuario u ON e.id_categoria = u.id_usuario"; // Suponiendo relación entre eventos y usuario, ajusta si es incorrecto.
        return jdbcTemplate.query(sql, (rs, rowNum) -> new EventoModelo(
            rs.getLong("id_evento"),
            rs.getString("nombre_evento"),
            rs.getDate("fecha_evento"),
            rs.getString("descripcion"),
            rs.getString("imagen"),
            new UsuarioModelo(
                rs.getLong("id_usuario"),
                rs.getString("nombre_usuario")
            )
        ));
    }
    
    public List<EventoModelo> obtenerEventos(Long id) {
        String sql = "SELECT e.id_evento, e.nombre_evento, e.fecha_evento, e.descripcion, e.imagen, u.id_usuario, u.nombre_usuario " +
                     "FROM USRVIDA_AZUL.eventos e " +
                     "INNER JOIN USRVIDA_AZUL.usuario u ON e.id_categoria = u.id_usuario " + // Suponiendo relación entre eventos y usuario, ajusta si es incorrecto.
                     "WHERE e.id_evento = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> new EventoModelo(
            rs.getLong("id_evento"),
            rs.getString("nombre_evento"),
            rs.getDate("fecha_evento"),
            rs.getString("descripcion"),
            rs.getString("imagen"),
            new UsuarioModelo(
                rs.getLong("id_usuario"),
                rs.getString("nombre_usuario")
            )
        ));
    }
}
package com.vida.azul.Dao;

import com.vida.azul.Domain.EventoCrud;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

@Repository
public class EventoDaoCrud {

    private final JdbcTemplate jdbcTemplate;

    public EventoDaoCrud(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<EventoCrud> obtenerEventos() {
        List<EventoCrud> eventos = new ArrayList<>();
        String sql = "{call sp_leer_eventos(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection(); CallableStatement cstmt = con.prepareCall(sql)) {
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(1)) {
                while (rs.next()) {
                    EventoCrud evento = new EventoCrud();
                    evento.setIdEvento(rs.getLong("id_evento"));
                    evento.setNombreEvento(rs.getString("nombre_evento"));
                    evento.setDescripcion(rs.getString("descripcion"));
                    evento.setImagen(rs.getString("imagen"));
                    evento.setFechaEvento(rs.getDate("fecha_evento"));
                    evento.setIdCategoria(rs.getLong("id_categoria"));
                    eventos.add(evento);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventos;
    }

    public EventoCrud buscarPorId(Long idEvento) {
        EventoCrud evento = null;
        String sql = "{call sp_obtener_evento_por_id(?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection(); CallableStatement cstmt = con.prepareCall(sql)) {
            cstmt.setLong(1, idEvento);
            cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(2)) {
                if (rs.next()) {
                    evento = new EventoCrud();
                    evento.setIdEvento(rs.getLong("id_evento"));
                    evento.setNombreEvento(rs.getString("nombre_evento"));
                    evento.setDescripcion(rs.getString("descripcion"));
                    evento.setImagen(rs.getString("imagen"));
                    evento.setFechaEvento(rs.getDate("fecha_evento"));
                    evento.setIdCategoria(rs.getLong("id_categoria"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return evento;
    }

    public String actualizarEvento(EventoCrud evento) {
        String mensaje = "";
        String sql = "{call sp_actualizar_evento(?, ?, ?, ?, ?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection(); CallableStatement cstmt = con.prepareCall(sql)) {
            cstmt.setLong(1, evento.getIdEvento());
            cstmt.setLong(2, evento.getIdCategoria());
            cstmt.setString(3, evento.getNombreEvento());
            cstmt.setDate(4, new java.sql.Date(evento.getFechaEvento().getTime()));
            cstmt.setString(5, evento.getDescripcion());
            cstmt.setString(6, evento.getImagen());
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al actualizar evento: " + e.getMessage();
        }

        return mensaje;
    }

    public String agregarEvento(EventoCrud evento) {
        String mensaje = "";
        String sql = "{call sp_agregar_evento(?, ?, ?, ?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection(); CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, evento.getIdCategoria());
            cstmt.setString(2, evento.getNombreEvento());
            cstmt.setDate(3, new java.sql.Date(evento.getFechaEvento().getTime())); // Convertir a java.sql.Date
            cstmt.setString(4, evento.getDescripcion());
            cstmt.setString(5, evento.getImagen());

            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al agregar evento: " + e.getMessage();
        }

        return mensaje;
    }

    public String eliminarEvento(Long idEvento) {
        String mensaje = "";
        String sql = "{call sp_eliminar_evento(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection(); CallableStatement cstmt = con.prepareCall(sql)) {
            cstmt.setLong(1, idEvento);
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al eliminar evento: " + e.getMessage();
        }

        return mensaje;
    }
}

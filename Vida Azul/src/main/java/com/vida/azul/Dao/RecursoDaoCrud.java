package com.vida.azul.Dao;

import com.vida.azul.Domain.RecursoCrud;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecursoDaoCrud {

    private final JdbcTemplate jdbcTemplate;

    public RecursoDaoCrud(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<RecursoCrud> obtenerRecursos() {
        List<RecursoCrud> recursos = new ArrayList<>();
        String sql = "{call sp_leer_recursos(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(1)) {
                while (rs.next()) {
                    RecursoCrud recurso = new RecursoCrud();
                    recurso.setIdRecurso(rs.getLong("id_recurso"));
                    recurso.setIdCategoria(rs.getLong("id_categoria"));
                    recurso.setNombreRecurso(rs.getString("nombre_recurso"));
                    recurso.setDescripcion(rs.getString("descripcion"));
                    recurso.setImagen(rs.getString("imagen"));
                    recursos.add(recurso);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recursos;
    }

    public String agregarRecurso(RecursoCrud recurso) {
        String mensaje = "";
        String sql = "{call sp_agregar_recurso(?, ?, ?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, recurso.getIdCategoria());
            cstmt.setString(2, recurso.getNombreRecurso());
            cstmt.setString(3, recurso.getDescripcion());
            cstmt.setString(4, recurso.getImagen());
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al agregar recurso: " + e.getMessage();
        }

        return mensaje;
    }

    public String actualizarRecurso(RecursoCrud recurso) {
        String mensaje = "";
        String sql = "{call sp_actualizar_recurso(?, ?, ?, ?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, recurso.getIdRecurso());
            cstmt.setLong(2, recurso.getIdCategoria());
            cstmt.setString(3, recurso.getNombreRecurso());
            cstmt.setString(4, recurso.getDescripcion());
            cstmt.setString(5, recurso.getImagen());
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al actualizar recurso: " + e.getMessage();
        }

        return mensaje;
    }

    public String eliminarRecurso(Long idRecurso) {
        String mensaje = "";
        String sql = "{call sp_eliminar_recurso(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, idRecurso);
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al eliminar recurso: " + e.getMessage();
        }

        return mensaje;
    }

    public RecursoCrud buscarPorId(Long idRecurso) {
        RecursoCrud recurso = null;
        String sql = "{call sp_obtener_recurso_por_id(?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, idRecurso);
            cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(2)) {
                if (rs.next()) {
                    recurso = new RecursoCrud();
                    recurso.setIdRecurso(rs.getLong("id_recurso"));
                    recurso.setIdCategoria(rs.getLong("id_categoria"));
                    recurso.setNombreRecurso(rs.getString("nombre_recurso"));
                    recurso.setDescripcion(rs.getString("descripcion"));
                    recurso.setImagen(rs.getString("imagen"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recurso;
    }
}

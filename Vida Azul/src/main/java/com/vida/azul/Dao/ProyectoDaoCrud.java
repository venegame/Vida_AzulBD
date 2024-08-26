package com.vida.azul.Dao;

import com.vida.azul.Domain.ProyectoCrud;
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
public class ProyectoDaoCrud {

    private final JdbcTemplate jdbcTemplate;

    public ProyectoDaoCrud(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<ProyectoCrud> obtenerProyectos() {
        List<ProyectoCrud> proyectos = new ArrayList<>();
        String sql = "{call sp_leer_proyectos(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(1)) {
                while (rs.next()) {
                    ProyectoCrud proyecto = new ProyectoCrud();
                    proyecto.setIdProyecto(rs.getLong("id_proyecto"));
                    proyecto.setIdUsuario(rs.getLong("id_usuario"));
                    proyecto.setIdCategoria(rs.getLong("id_categoria"));
                    proyecto.setNombreProyecto(rs.getString("nombre_proyecto"));
                    proyecto.setDetalleProyecto(rs.getString("detalle_proyecto"));
                    proyecto.setEstadoProyecto(rs.getString("estado_proyecto"));
                    proyecto.setRutaImagen(rs.getString("ruta_imagen"));
                    proyectos.add(proyecto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proyectos;
    }

    public String agregarProyecto(ProyectoCrud proyecto) {
        String mensaje = "";
        String sql = "{call sp_agregar_proyecto(?, ?, ?, ?, ?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, proyecto.getIdUsuario());
            cstmt.setLong(2, proyecto.getIdCategoria());
            cstmt.setString(3, proyecto.getNombreProyecto());
            cstmt.setString(4, proyecto.getDetalleProyecto());
            cstmt.setString(5, proyecto.getEstadoProyecto());
            cstmt.setString(6, proyecto.getRutaImagen());
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al agregar proyecto: " + e.getMessage();
        }

        return mensaje;
    }

    public String actualizarProyecto(ProyectoCrud proyecto) {
        String mensaje = "";
        String sql = "{call sp_actualizar_proyecto(?, ?, ?, ?, ?, ?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, proyecto.getIdProyecto());
            cstmt.setLong(2, proyecto.getIdUsuario());
            cstmt.setLong(3, proyecto.getIdCategoria());
            cstmt.setString(4, proyecto.getNombreProyecto());
            cstmt.setString(5, proyecto.getDetalleProyecto());
            cstmt.setString(6, proyecto.getEstadoProyecto());
            cstmt.setString(7, proyecto.getRutaImagen());
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al actualizar proyecto: " + e.getMessage();
        }

        return mensaje;
    }

    public String eliminarProyecto(Long idProyecto) {
        String mensaje = "";
        String sql = "{call sp_eliminar_proyecto(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, idProyecto);
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al eliminar proyecto: " + e.getMessage();
        }

        return mensaje;
    }

    public ProyectoCrud buscarPorId(Long idProyecto) {
        ProyectoCrud proyecto = null;
        String sql = "{call sp_obtener_proyecto_por_id(?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, idProyecto);
            cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(2)) {
                if (rs.next()) {
                    proyecto = new ProyectoCrud();
                    proyecto.setIdProyecto(rs.getLong("id_proyecto"));
                    proyecto.setIdUsuario(rs.getLong("id_usuario"));
                    proyecto.setIdCategoria(rs.getLong("id_categoria"));
                    proyecto.setNombreProyecto(rs.getString("nombre_proyecto"));
                    proyecto.setDetalleProyecto(rs.getString("detalle_proyecto"));
                    proyecto.setEstadoProyecto(rs.getString("estado_proyecto"));
                    proyecto.setRutaImagen(rs.getString("ruta_imagen"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proyecto;
    }
}

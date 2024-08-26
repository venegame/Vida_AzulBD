package com.vida.azul.Dao;

import com.vida.azul.Domain.RolCrud;
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
public class RolDaoCrud {

    private final JdbcTemplate jdbcTemplate;

    public RolDaoCrud(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<RolCrud> obtenerRoles() {
        List<RolCrud> roles = new ArrayList<>();
        String sql = "{call sp_leer_roles(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection(); CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(1)) {
                while (rs.next()) {
                    RolCrud rol = new RolCrud();
                    rol.setIdRol(rs.getLong("id_rol"));
                    rol.setNombreRol(rs.getString("nombre_rol"));
                    roles.add(rol);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar el error adecuadamente o lanzar una excepción personalizada
        }

        return roles;
    }

    public String agregarRol(RolCrud rol) {
        String mensaje = "";
        String sql = "{call sp_agregar_rol(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection(); CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setString(1, rol.getNombreRol());
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al agregar rol: " + e.getMessage();
        }

        return mensaje;
    }

    public String actualizarRol(RolCrud rol) {
        String mensaje = "";
        String sql = "{call sp_actualizar_rol(?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection(); CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, rol.getIdRol());
            cstmt.setString(2, rol.getNombreRol());
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al actualizar rol: " + e.getMessage();
        }

        return mensaje;
    }

    public String eliminarRol(Long idRol) {
        String mensaje = "";
        String sql = "{call sp_eliminar_rol(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection(); CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, idRol);
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al eliminar rol: " + e.getMessage();
        }

        return mensaje;
    }

    public RolCrud buscarPorId(Long idRol) {
        RolCrud rol = null;
        String sql = "{call sp_obtener_rol_por_id(?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection(); CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, idRol);
            cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(2)) {
                if (rs.next()) {
                    rol = new RolCrud();
                    rol.setIdRol(rs.getLong("id_rol"));
                    rol.setNombreRol(rs.getString("nombre_rol"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar el error adecuadamente o lanzar una excepción personalizada
        }

        return rol;
    }
}

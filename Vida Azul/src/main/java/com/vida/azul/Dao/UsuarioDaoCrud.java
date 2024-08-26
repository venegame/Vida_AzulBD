package com.vida.azul.Dao;

import com.vida.azul.Domain.UsuarioCrud;
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
public class UsuarioDaoCrud {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioDaoCrud(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<UsuarioCrud> obtenerUsuarios() {
        List<UsuarioCrud> usuarios = new ArrayList<>();
        String sql = "{call sp_leer_usuarios(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(1)) {
                while (rs.next()) {
                    UsuarioCrud usuario = new UsuarioCrud();
                    usuario.setIdUsuario(rs.getLong("id_usuario"));
                    usuario.setIdRol(rs.getLong("id_rol"));
                    usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                    usuario.setApellidoUsuario(rs.getString("apellido_usuario"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setContrasenia(rs.getString("contrasenia"));
                    usuarios.add(usuario);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public String agregarUsuario(UsuarioCrud usuario) {
        String mensaje = "";
        String sql = "{call sp_agregar_usuario(?, ?, ?, ?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, usuario.getIdRol());
            cstmt.setString(2, usuario.getNombreUsuario());
            cstmt.setString(3, usuario.getApellidoUsuario());
            cstmt.setString(4, usuario.getCorreo());
            cstmt.setString(5, usuario.getContrasenia());
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al agregar usuario: " + e.getMessage();
        }

        return mensaje;
    }

    public String actualizarUsuario(UsuarioCrud usuario) {
        String mensaje = "";
        String sql = "{call sp_actualizar_usuario(?, ?, ?, ?, ?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, usuario.getIdUsuario());
            cstmt.setLong(2, usuario.getIdRol());
            cstmt.setString(3, usuario.getNombreUsuario());
            cstmt.setString(4, usuario.getApellidoUsuario());
            cstmt.setString(5, usuario.getCorreo());
            cstmt.setString(6, usuario.getContrasenia());
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al actualizar usuario: " + e.getMessage();
        }

        return mensaje;
    }

    public String eliminarUsuario(Long idUsuario) {
        String mensaje = "";
        String sql = "{call sp_eliminar_usuario(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, idUsuario);
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al eliminar usuario: " + e.getMessage();
        }

        return mensaje;
    }

    public UsuarioCrud buscarPorId(Long idUsuario) {
        UsuarioCrud usuario = null;
        String sql = "{call sp_obtener_usuario_por_id(?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, idUsuario);
            cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(2)) {
                if (rs.next()) {
                    usuario = new UsuarioCrud();
                    usuario.setIdUsuario(rs.getLong("id_usuario"));
                    usuario.setIdRol(rs.getLong("id_rol"));
                    usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                    usuario.setApellidoUsuario(rs.getString("apellido_usuario"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setContrasenia(rs.getString("contrasenia"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }
}

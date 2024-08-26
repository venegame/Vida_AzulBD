/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vida.azul.Dao;

import com.vida.azul.Domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsuarioDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public List<Usuario> obtenerUsuarios() {
        String sql = "SELECT ID_USUARIO, ID_ROL, NOMBRE_USUARIO, APELLIDO_USUARIO, CORREO, CONTRASENIA FROM USRVIDA_AZUL.USUARIO";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Usuario usuario = new Usuario();
            usuario.setId_usuario(rs.getLong("ID_USUARIO"));
            usuario.setId_rol(rs.getLong("ID_ROL"));
            usuario.setNombre_usuario(rs.getString("NOMBRE_USUARIO"));
            usuario.setApellido_usuario(rs.getString("APELLIDO_USUARIO"));
            usuario.setCorreo(rs.getString("CORREO"));
            usuario.setContrasenia(rs.getString("CONTRASENIA"));
            return usuario;
        });
    }

    public String agregarUsuario(Usuario usuario) {
    String mensaje = "";
    String sql = "{CALL SP_INGRESAR_USUARIO(?, ?, ?, ?, ?, ?)}";
    try (Connection con = jdbcTemplate.getDataSource().getConnection();
         CallableStatement cstmt = con.prepareCall(sql)) {
        cstmt.setString(1, usuario.getNombre_usuario());
        cstmt.setString(2, usuario.getApellido_usuario());
        cstmt.setString(3, usuario.getCorreo());
        cstmt.setString(4, usuario.getContrasenia());
        cstmt.setLong(5, usuario.getId_rol());
        cstmt.registerOutParameter(6, Types.VARCHAR);
        cstmt.execute();
        mensaje = cstmt.getString(6);
    } catch (SQLException e) {
        e.printStackTrace();
        mensaje = "Error al agregar usuario: " + e.getMessage();
    }
    return mensaje;
}


    public String eliminarUsuario(Long id_usuario) {
        String mensaje = "";
        String sql = "{call VIDA_AZUL.ELIMINAR_USUARIO_SP(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, id_usuario);

            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al eliminar usuario: " + e.getMessage();
        }
        return mensaje;
    }

    public String actualizarUsuario(Usuario usuario) {
        String mensaje = "";
        String sql = "{call VIDA_AZUL.MODIFICAR_USUARIO_SP(?, ?, ?, ?, ?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {
            
            cstmt.setLong(1, usuario.getId_usuario());
            cstmt.setLong(1, usuario.getId_rol());
            cstmt.setString(2, usuario.getNombre_usuario());
            cstmt.setString(2, usuario.getApellido_usuario());
            cstmt.setString(2, usuario.getCorreo());
            cstmt.setString(2, usuario.getContrasenia());
           
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al actualizar usuario: " + e.getMessage();
        }
        return mensaje;
    }
}

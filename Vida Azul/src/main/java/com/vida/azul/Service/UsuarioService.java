package com.vida.azul.Service;

import com.vida.azul.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Service
public class UsuarioService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsuarioService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Usuario> obtenerUsuarios() {
        // Implementa la lógica para obtener usuarios, posiblemente usando una consulta SQL
        String sql = "SELECT * FROM USRVIDA_AZUL.usuario";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Usuario(
            rs.getLong("id_usuario"),
            rs.getLong("id_rol"),
            rs.getString("nombre_usuario"),
            rs.getString("apellido_usuario"),
            rs.getString("correo"),
            rs.getString("contrasenia")
        ));
    }
    
    public Usuario obtenerUsuarioPorId(Long id) {
        String sql = "SELECT * FROM USRVIDA_AZUL.usuario WHERE id_usuario = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new Usuario(
            rs.getLong("id_usuario"),
            rs.getLong("id_rol"),
            rs.getString("nombre_usuario"),
            rs.getString("apellido_usuario"),
            rs.getString("correo"),
            rs.getString("contrasenia")
        ));
    }
    
    
    public String agregarUsuario(Usuario usuario) {
        String mensaje = "Usuario creado exitosamente"; // Mensaje por defecto en caso de éxito
        String sql = "{CALL USRVIDA_AZUL.SP_INGRESAR_USUARIO(?, ?, ?, ?, ?)}";
        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {
            // Configurar los parámetros del procedimiento almacenado
            cstmt.setLong(1, usuario.getId_rol()); // IDROL
            cstmt.setString(2, usuario.getNombre_usuario()); // NOMBREUSUARIO
            cstmt.setString(3, usuario.getApellido_usuario()); // APELLIDOUSUARIO
            cstmt.setString(4, usuario.getCorreo()); // CORREO
            cstmt.setString(5, usuario.getContrasenia()); // CONTRASENIA
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al agregar usuario: " + e.getMessage();
        }
        return mensaje;
    }

    public String actualizarUsuario(Usuario usuario) {
        String mensaje = "Usuario actualizado exitosamente";
        String sql = "{CALL USRVIDA_AZUL.SP_ACTUALIZAR_USUARIO(?, ?, ?, ?, ?, ?)}";
        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {
            // Configurar los parámetros del procedimiento almacenado
            cstmt.setLong(1, usuario.getId_usuario()); // IDUSUARIO
            cstmt.setString(2, usuario.getNombre_usuario()); // NOMBREUSUARIO
            cstmt.setString(3, usuario.getApellido_usuario()); // APELLIDOUSUARIO
            cstmt.setString(4, usuario.getCorreo()); // CORREOUSUARIO
            cstmt.setString(5, usuario.getContrasenia()); // CONTRASENIAUSUARIO
            cstmt.setLong(6, usuario.getId_rol()); // IDROL
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al actualizar usuario: " + e.getMessage();
        }
        return mensaje;
    }
    
    public String eliminarUsuario(Long id) {
        String mensaje = "Usuario eliminado exitosamente";
        String sql = "{CALL USRVIDA_AZUL.SP_ELIMINAR_USUARIO(?)}"; // Asegúrate de que este procedimiento esté definido
        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {
            cstmt.setLong(1, id);
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al eliminar usuario: " + e.getMessage();
        }
        return mensaje;
    }
    
}


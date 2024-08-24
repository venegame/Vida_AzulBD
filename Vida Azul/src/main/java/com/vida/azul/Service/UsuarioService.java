package com.vida.azul.Service;

import com.vida.azul.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.sql.CallableStatement;
import java.sql.Connection;
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
            
            // Ejecutar el procedimiento almacenado
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al agregar usuario: " + e.getMessage();
        }
        return mensaje;
    }

    public String eliminarUsuario(Long id_usuario) {
        String sql = "DELETE FROM USRVIDA_AZUL.usuarios WHERE id_usuario = ?";
        jdbcTemplate.update(sql, id_usuario);
        return "Usuario eliminado exitosamente";
    }

    public String actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE USRVIDA_AZUL.usuarios SET id_rol = ?, nombre_usuario = ?, apellido_usuario = ?, correo = ?, contrasenia = ? WHERE id_usuario = ?";
        jdbcTemplate.update(sql, 
            usuario.getId_rol(), 
            usuario.getNombre_usuario(), 
            usuario.getApellido_usuario(), 
            usuario.getCorreo(), 
            usuario.getContrasenia(), 
            usuario.getId_usuario()
        );
        return "Usuario actualizado exitosamente";
    }
}


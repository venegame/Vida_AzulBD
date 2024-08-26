package com.vida.azul.Service;

import com.vida.azul.domain.Usuario;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Service
public class UsuarioService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsuarioService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<Usuario> obtenerUsuarios() {
        // Configuración de SimpleJdbcCall para invocar al procedimiento almacenado
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("USRVIDA_AZUL")
                .withProcedureName("SP_LEER_USUARIOS")
                .declareParameters(new SqlOutParameter("p_cursor", OracleTypes.CURSOR));

        // Ejecución del procedimiento almacenado
        Map<String, Object> result = jdbcCall.execute();

        // Obtener el cursor de salida
        List<Usuario> usuarios = new ArrayList<>();
        List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("p_cursor");

        // Mapear los resultados a la entidad Usuario
        for (Map<String, Object> row : rows) {
            Usuario usuario = new Usuario();
            usuario.setId_usuario(((Number) row.get("ID_USUARIO")).longValue());
            usuario.setId_rol(((Number) row.get("ID_ROL")).longValue());
            usuario.setNombre_usuario((String) row.get("NOMBRE_USUARIO"));
            usuario.setApellido_usuario((String) row.get("APELLIDO_USUARIO"));
            usuario.setCorreo((String) row.get("CORREO"));
            usuario.setContrasenia((String) row.get("CONTRASENIA"));
            usuarios.add(usuario);
        }

        return usuarios;
    }

    /*
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
    */
    public Usuario obtenerUsuarioPorId(Long idUsuario) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withSchemaName("USRVIDA_AZUL")
            .withProcedureName("SP_LEER_USUARIO")
            .declareParameters(
                new SqlParameter("IDUSUARIO", Types.NUMERIC),
                new SqlOutParameter("IDROL", Types.NUMERIC),
                new SqlOutParameter("NOMBREUSUARIO", Types.VARCHAR),
                new SqlOutParameter("APELLIDOUSUARIO", Types.VARCHAR),
                new SqlOutParameter("CORREOUSUARIO", Types.VARCHAR),
                new SqlOutParameter("CONTRASENIAUSUARIO", Types.VARCHAR)
            );
        SqlParameterSource in = new MapSqlParameterSource()
            .addValue("IDUSUARIO", idUsuario);
        Map<String, Object> out = jdbcCall.execute(in);
        Usuario usuario = new Usuario();
        usuario.setId_usuario(idUsuario);
        BigDecimal idRolDecimal = (BigDecimal) out.get("IDROL");
        if (idRolDecimal != null) {
            usuario.setId_rol(idRolDecimal.longValue());
        }
        usuario.setNombre_usuario((String) out.get("NOMBREUSUARIO"));
        usuario.setApellido_usuario((String) out.get("APELLIDOUSUARIO"));
        usuario.setCorreo((String) out.get("CORREOUSUARIO"));
        usuario.setContrasenia((String) out.get("CONTRASENIAUSUARIO"));
        return usuario;
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
    
    public String RegistrarUsuario(Usuario usuario) {
        String mensaje = "Usuario creado exitosamente"; // Mensaje por defecto en caso de éxito
        String sql = "{CALL USRVIDA_AZUL.SP_INGRESAR_USUARIO(?, ?, ?, ?, ?)}";
        usuario.setId_rol(2L);
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


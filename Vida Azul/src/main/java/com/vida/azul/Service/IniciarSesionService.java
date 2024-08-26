package com.vida.azul.Service;

import com.vida.azul.Domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Types;
import java.util.Map;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;

@Service
public class IniciarSesionService {

    private final SimpleJdbcCall simpleJdbcCall;

    @Autowired
    public IniciarSesionService(DataSource dataSource) {
        this.simpleJdbcCall = new SimpleJdbcCall(dataSource)
                .withSchemaName("USRVIDA_AZUL")
                .withProcedureName("SP_INICIAR_SESION")
                .declareParameters(
                        new SqlParameter("P_CORREO", Types.VARCHAR),
                        new SqlParameter("P_CONTRASENIA", Types.VARCHAR),
                        new SqlOutParameter("P_ID_USUARIO", Types.BIGINT),
                        new SqlOutParameter("P_NOMBRE_USUARIO", Types.VARCHAR),
                        new SqlOutParameter("P_APELLIDO_USUARIO", Types.VARCHAR),
                        new SqlOutParameter("P_ID_ROL", Types.BIGINT)
                );
    }

    public Usuario iniciarSesion(String correo, String contrasenia) {
        Map<String, Object> result = simpleJdbcCall.execute(
                correo, 
                contrasenia
        );
        Long id_usuario = (Long) result.get("P_ID_USUARIO");
        String nombre_usuario = (String) result.get("P_NOMBRE_USUARIO");
        String apellido_usuario = (String) result.get("P_APELLIDO_USUARIO");
        Long id_rol = (Long) result.get("P_ID_ROL");
        if (id_usuario != null) {
            return new Usuario(id_usuario, id_rol, nombre_usuario, apellido_usuario, correo, contrasenia);
        } else {
            throw new RuntimeException("Credenciales incorrectas o no validas. Por favor intente de nuevo.");
        }
    }
}



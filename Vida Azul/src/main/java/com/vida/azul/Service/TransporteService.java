/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vida.azul.Service;

import com.vida.azul.Domain.Transporte;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.sql.Types;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.stereotype.Service;

/**
 *
 * @author Me
 */
@Service
public class TransporteService {
    
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcCall obtenerTransportesCall;

    @Autowired
    public TransporteService(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.obtenerTransportesCall = new SimpleJdbcCall(dataSource)
            .withSchemaName("USRVIDA_AZUL") // Esquema
            .withProcedureName("SP_OBTENER_TRANSPORTES") // Nombre del procedimiento
            .declareParameters(new SqlOutParameter("p_transportes", Types.REF_CURSOR, new TransporteRowMapper()));
    }

    public List<Transporte> obtenerTransportes() {
        Map<String, Object> result = obtenerTransportesCall.execute();
        return (List<Transporte>) result.get("p_transportes");
    }

    private static class TransporteRowMapper implements RowMapper<Transporte> {
        @Override
        public Transporte mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Transporte(
                rs.getInt("ID_TRANSPORTE"),
                rs.getInt("ID_USUARIO"),
                rs.getString("NOMBRE_TRANSPORTE"),
                rs.getString("RUTA_TRANSPORTE"),
                rs.getString("HORARIO_TRANSPORTE"),
                rs.getInt("PRECIO_TRANSPORTE")
            );
        }
    }
    
}

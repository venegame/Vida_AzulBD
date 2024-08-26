/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vida.azul.Service;
import com.vida.azul.Domain.Experto;
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
public class ExpertoService {
    
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcCall obtenerExpertosCall;
    
    @Autowired
    public ExpertoService(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.obtenerExpertosCall = new SimpleJdbcCall(dataSource)
            .withSchemaName("USRVIDA_AZUL")
            .withProcedureName("SP_OBTENER_EXPERTOS")
            .declareParameters(new SqlOutParameter("p_expertos", Types.REF_CURSOR, new ExpertoRowMapper()));
    }
    
    public List<Experto> obtenerExpertos() {
        Map<String, Object> result = obtenerExpertosCall.execute();
        return (List<Experto>) result.get("p_expertos");
    }
    
    private static class ExpertoRowMapper implements RowMapper<Experto> {
        @Override
        public Experto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Experto(
                rs.getInt("ID_EXPERTO"),
                rs.getInt("ID_CATEGORIA"),
                rs.getString("NOMBRE_EXPERTO"),
                rs.getString("QUIENES_SOMOS"),
                rs.getString("HISTORIA_EXPERTOS"),
                rs.getString("URL_INSTAGRAM"),
                rs.getString("URL_X"),
                rs.getString("URL_YOUTUBE"),
                rs.getString("URL_FACEBOOK")
            );
        }
    }
}

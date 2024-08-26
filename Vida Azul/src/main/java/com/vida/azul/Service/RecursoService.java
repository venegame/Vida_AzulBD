/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vida.azul.Service;

import com.vida.azul.domain.Recurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;

/**
 *
 * @author Me
 */
@Service
public class RecursoService {
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RecursoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<Recurso> obtenerRecursos() {
        String sql = "SELECT ID_RECURSO, ID_CATEGORIA, NOMBRE_RECURSO, DESCRIPCION, IMAGEN FROM USRVIDA_AZUL.RECURSOS";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Recurso(
            rs.getInt("ID_RECURSO"),
            rs.getInt("ID_CATEGORIA"),
            rs.getString("NOMBRE_RECURSO"),
            rs.getString("DESCRIPCION"),
            rs.getString("IMAGEN")
        ));
    } 
    
    public Recurso obtenerRecursoPorId(int idRecurso) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withSchemaName("USRVIDA_AZUL") // Ajusta según tu esquema
            .withProcedureName("SP_LEER_RECURSO")
            .declareParameters(
                new SqlParameter("IDRECURSO", Types.NUMERIC),
                new SqlOutParameter("IDCATEGORIA", Types.NUMERIC),
                new SqlOutParameter("NOMBRERECURSO", Types.VARCHAR),
                new SqlOutParameter("DESCRIPCIONRECURSO", Types.VARCHAR),
                new SqlOutParameter("IMAGENRECURSO", Types.VARCHAR)
            );

        SqlParameterSource in = new MapSqlParameterSource()
            .addValue("IDRECURSO", idRecurso);
        Map<String, Object> out = jdbcCall.execute(in);

        Recurso recurso = new Recurso();
        recurso.setId_recurso(idRecurso);
        recurso.setId_categoria(((Number) out.get("IDCATEGORIA")).intValue());
        recurso.setNombre_recurso((String) out.get("NOMBRERECURSO"));
        recurso.setDescripcion((String) out.get("DESCRIPCIONRECURSO"));
        recurso.setImagen((String) out.get("IMAGENRECURSO"));
        return recurso;
    }

} 

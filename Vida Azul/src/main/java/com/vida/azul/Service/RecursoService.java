package com.vida.azul.Service;

import com.vida.azul.domain.Recurso;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;

@Service
public class RecursoService {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcCall obtenerRecursosCall;

    @Autowired
    public RecursoService(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.obtenerRecursosCall = new SimpleJdbcCall(dataSource)
            .withSchemaName("USRVIDA_AZUL")
            .withProcedureName("SP_OBTENER_RECURSOS")
            .declareParameters(new SqlOutParameter("p_recursos", Types.REF_CURSOR, new RecursoRowMapper()));
    }

    public List<Recurso> obtenerRecursos() {
        Map<String, Object> result = obtenerRecursosCall.execute();
        return (List<Recurso>) result.get("p_recursos");
    }

    private static class RecursoRowMapper implements RowMapper<Recurso> {
        @Override
        public Recurso mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Recurso(
                rs.getInt("ID_RECURSO"),
                rs.getInt("ID_CATEGORIA"),
                rs.getString("NOMBRE_RECURSO"),
                rs.getString("DESCRIPCION"),
                rs.getString("IMAGEN")
            );
        }
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


package com.vida.azul.Service;

import com.vida.azul.domain.Recurso;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;
import java.sql.Types;

@Service
public class RecursoService {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcCall obtenerRecursosCall;
    
    @Autowired
    private DataSource dataSource;

    @Autowired
    public RecursoService(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.obtenerRecursosCall = new SimpleJdbcCall(dataSource)
            .withSchemaName("USRVIDA_AZUL")
            .withProcedureName("SP_OBTENER_RECURSOS")
            .declareParameters(
                new SqlParameter("IDCATEGORIA", Types.NUMERIC),
                new SqlOutParameter("CURSORC", Types.REF_CURSOR, new RecursoRowMapper())
            );
    }

    public List<Recurso> obtenerRecursos(Integer idCategoria) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
            .withSchemaName("USRVIDA_AZUL")
            .withProcedureName("SP_OBTENER_RECURSOS")
            .returningResultSet("CURSORC", new RecursoRowMapper());

        SqlParameterSource in = new MapSqlParameterSource()
            .addValue("IDCATEGORIA", idCategoria, Types.INTEGER); // Asegúrate de que el tipo sea correcto

        Map<String, Object> result = jdbcCall.execute(in);
        return (List<Recurso>) result.get("CURSORC");
    }


    private static class RecursoRowMapper implements RowMapper<Recurso> {
        @Override
        public Recurso mapRow(ResultSet rs, int rowNum) throws SQLException {
            Recurso recurso = new Recurso();
            recurso.setId_recurso(rs.getInt("ID_RECURSO"));
            recurso.setId_categoria(rs.getInt("ID_CATEGORIA"));
            recurso.setNombre_recurso(rs.getString("NOMBRE_RECURSO"));
            recurso.setDescripcion(rs.getString("DESCRIPCION"));
            recurso.setImagen(rs.getString("IMAGEN"));
            return recurso;
        }
    }

    public Recurso obtenerRecursoPorId(int idRecurso) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withSchemaName("USRVIDA_AZUL")
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




package com.vida.azul.Service;

import com.vida.azul.Domain.Galeria;
import com.vida.azul.Domain.Usuario;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import models.GaleriaModelo;
import models.UsuarioModelo;
import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Service
public class GaleriaService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GaleriaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<GaleriaModelo> obtenerImagenes() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("USRVIDA_AZUL")
                .withProcedureName("SP_LEER_TODO_GALERIA")
                .declareParameters(new SqlOutParameter("p_cursor", OracleTypes.CURSOR));
        Map<String, Object> result = jdbcCall.execute();
        List<GaleriaModelo> imagenes = new ArrayList<>();
        List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("p_cursor");
        for (Map<String, Object> row : rows) {
            GaleriaModelo imagen = new GaleriaModelo(
                ((Number) row.get("ID_IMAGEN")).longValue(),
                (String) row.get("IMAGEN"),
                (String) row.get("TITULO"),
                new UsuarioModelo(
                    ((Number) row.get("ID_USUARIO")).longValue(),
                    (String) row.get("NOMBRE_USUARIO")
                )
            );
            imagenes.add(imagen);
        }
        return imagenes;
    }
    
    public List<GaleriaModelo> obtenerImagen(Long id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("USRVIDA_AZUL")
                .withProcedureName("SP_LEER_GALERIA_POR_ID")
                .declareParameters(
                        new SqlParameter("p_id_imagen", OracleTypes.NUMBER),
                        new SqlOutParameter("p_cursor", OracleTypes.CURSOR)
                );
        Map<String, Object> result = jdbcCall.execute(id);
        List<GaleriaModelo> imagenes = new ArrayList<>();
        List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("p_cursor");
        for (Map<String, Object> row : rows) {
            GaleriaModelo imagen = new GaleriaModelo(
                ((Number) row.get("ID_IMAGEN")).longValue(),
                (String) row.get("IMAGEN"),
                (String) row.get("TITULO"),
                new UsuarioModelo(
                    ((Number) row.get("ID_USUARIO")).longValue(),
                    (String) row.get("NOMBRE_USUARIO")
                )
            );
            imagenes.add(imagen);
        }
        return imagenes;
    }
}
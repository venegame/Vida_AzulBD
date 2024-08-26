package com.vida.azul.Service;

import models.EventoModelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Service
public class EventoService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EventoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<EventoModelo> obtenerTodosEventos() {
        return jdbcTemplate.execute(
            (CallableStatementCreator) con -> {
                CallableStatement cs = con.prepareCall("{call SP_LEER_TODOS_EVENTOS(?)}");
                cs.registerOutParameter(1, Types.REF_CURSOR);
                return cs;
            },
            (CallableStatementCallback<List<EventoModelo>>) cs -> {
                cs.execute();
                try (ResultSet rs = (ResultSet) cs.getObject(1)) {
                    ResultSetExtractor<List<EventoModelo>> extractor = new ResultSetExtractor<List<EventoModelo>>() {
                        @Override
                        public List<EventoModelo> extractData(ResultSet rs) throws SQLException {
                            List<EventoModelo> eventos = new java.util.ArrayList<>();
                            while (rs.next()) {
                                EventoModelo evento = new EventoModelo(
                                    rs.getLong("id_evento"),
                                    rs.getLong("id_categoria"),
                                    rs.getString("nombre_evento"),
                                    rs.getDate("fecha_evento"),
                                    rs.getString("descripcion"),
                                    rs.getString("imagen")
                                );
                                eventos.add(evento);
                            }
                            return eventos;
                        }
                    };
                    return extractor.extractData(rs);
                }
            }
        );
    }
}
package com.vida.azul.Dao;

import com.vida.azul.Domain.ExpertoCrud;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExpertoDaoCrud {

    private final JdbcTemplate jdbcTemplate;

    public ExpertoDaoCrud(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<ExpertoCrud> obtenerExpertos() {
        List<ExpertoCrud> expertos = new ArrayList<>();
        String sql = "{call sp_leer_expertos(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(1)) {
                while (rs.next()) {
                    ExpertoCrud experto = new ExpertoCrud();
                    experto.setIdExperto(rs.getLong("id_experto"));
                    experto.setIdCategoria(rs.getLong("id_categoria"));
                    experto.setNombreExperto(rs.getString("nombre_experto"));
                    experto.setQuienesSomos(rs.getString("quienes_somos"));
                    experto.setHistoriaExpertos(rs.getString("historia_expertos"));
                    experto.setUrlInstagram(rs.getString("url_instagram"));
                    experto.setUrlX(rs.getString("url_x"));
                    experto.setUrlYoutube(rs.getString("url_youtube"));
                    experto.setUrlFacebook(rs.getString("url_facebook"));
                    expertos.add(experto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expertos;
    }

    public String agregarExperto(ExpertoCrud experto) {
        String mensaje = "";
        String sql = "{call sp_agregar_experto(?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, experto.getIdCategoria());
            cstmt.setString(2, experto.getNombreExperto());
            cstmt.setString(3, experto.getQuienesSomos());
            cstmt.setString(4, experto.getHistoriaExpertos());
            cstmt.setString(5, experto.getUrlInstagram());
            cstmt.setString(6, experto.getUrlX());
            cstmt.setString(7, experto.getUrlYoutube());
            cstmt.setString(8, experto.getUrlFacebook());
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al agregar experto: " + e.getMessage();
        }

        return mensaje;
    }

    public String actualizarExperto(ExpertoCrud experto) {
        String mensaje = "";
        String sql = "{call sp_actualizar_experto(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, experto.getIdExperto());
            cstmt.setLong(2, experto.getIdCategoria());
            cstmt.setString(3, experto.getNombreExperto());
            cstmt.setString(4, experto.getQuienesSomos());
            cstmt.setString(5, experto.getHistoriaExpertos());
            cstmt.setString(6, experto.getUrlInstagram());
            cstmt.setString(7, experto.getUrlX());
            cstmt.setString(8, experto.getUrlYoutube());
            cstmt.setString(9, experto.getUrlFacebook());
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al actualizar experto: " + e.getMessage();
        }

        return mensaje;
    }

    public String eliminarExperto(Long idExperto) {
        String mensaje = "";
        String sql = "{call sp_eliminar_experto(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, idExperto);
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al eliminar experto: " + e.getMessage();
        }

        return mensaje;
    }

    public ExpertoCrud buscarPorId(Long idExperto) {
        ExpertoCrud experto = null;
        String sql = "{call sp_obtener_experto_por_id(?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, idExperto);
            cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(2)) {
                if (rs.next()) {
                    experto = new ExpertoCrud();
                    experto.setIdExperto(rs.getLong("id_experto"));
                    experto.setIdCategoria(rs.getLong("id_categoria"));
                    experto.setNombreExperto(rs.getString("nombre_experto"));
                    experto.setQuienesSomos(rs.getString("quienes_somos"));
                    experto.setHistoriaExpertos(rs.getString("historia_expertos"));
                    experto.setUrlInstagram(rs.getString("url_instagram"));
                    experto.setUrlX(rs.getString("url_x"));
                    experto.setUrlYoutube(rs.getString("url_youtube"));
                    experto.setUrlFacebook(rs.getString("url_facebook"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return experto;
    }
}

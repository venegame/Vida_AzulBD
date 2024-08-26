package com.vida.azul.Dao;

import com.vida.azul.Domain.GaleriaCrud;
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
public class GaleriaDaoCrud {

    private final JdbcTemplate jdbcTemplate;

    public GaleriaDaoCrud(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GaleriaCrud> obtenerGalerias() {
        List<GaleriaCrud> galerias = new ArrayList<>();
        String sql = "{call sp_leer_imagenes(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(1)) {
                while (rs.next()) {
                    GaleriaCrud galeria = new GaleriaCrud();
                    galeria.setIdImagen(rs.getLong("id_imagen"));
                    galeria.setIdUsuario(rs.getLong("id_usuario"));
                    galeria.setImagen(rs.getString("imagen"));
                    galeria.setTitulo(rs.getString("titulo"));
                    galerias.add(galeria);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return galerias;
    }

    public String agregarGaleria(GaleriaCrud galeria) {
        String mensaje = "";
        String sql = "{call sp_agregar_imagen(?, ?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, galeria.getIdUsuario());
            cstmt.setString(2, galeria.getImagen());
            cstmt.setString(3, galeria.getTitulo());
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al agregar galería: " + e.getMessage();
        }

        return mensaje;
    }

    public String actualizarGaleria(GaleriaCrud galeria) {
        String mensaje = "";
        String sql = "{call sp_actualizar_imagen(?, ?, ?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, galeria.getIdImagen());
            cstmt.setLong(2, galeria.getIdUsuario());
            cstmt.setString(3, galeria.getImagen());
            cstmt.setString(4, galeria.getTitulo());
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al actualizar galería: " + e.getMessage();
        }

        return mensaje;
    }

    public String eliminarGaleria(Long idImagen) {
        String mensaje = "";
        String sql = "{call sp_eliminar_imagen(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, idImagen);
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al eliminar galería: " + e.getMessage();
        }

        return mensaje;
    }

    public GaleriaCrud buscarPorId(Long idImagen) {
        GaleriaCrud galeria = null;
        String sql = "{call sp_obtener_imagen_por_id(?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, idImagen);
            cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(2)) {
                if (rs.next()) {
                    galeria = new GaleriaCrud();
                    galeria.setIdImagen(rs.getLong("id_imagen"));
                    galeria.setIdUsuario(rs.getLong("id_usuario"));
                    galeria.setImagen(rs.getString("imagen"));
                    galeria.setTitulo(rs.getString("titulo"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return galeria;
    }
}

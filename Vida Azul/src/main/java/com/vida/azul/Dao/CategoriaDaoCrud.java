package com.vida.azul.Dao;

import com.vida.azul.Domain.CategoriaCrud;
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
public class CategoriaDaoCrud {

    private final JdbcTemplate jdbcTemplate;

    public CategoriaDaoCrud(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<CategoriaCrud> obtenerCategorias() {
        List<CategoriaCrud> categorias = new ArrayList<>();
        String sql = "{call sp_leer_categorias(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection(); CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(1)) {
                while (rs.next()) {
                    CategoriaCrud categoria = new CategoriaCrud();
                    categoria.setIdCategoria(rs.getLong("id_categoria"));
                    categoria.setNombreCategoria(rs.getString("nombre_categoria"));
                    categorias.add(categoria);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    public String agregarCategoria(CategoriaCrud categoria) {
        String mensaje = "";
        String sql = "{call sp_agregar_categoria(?)}";  

        try (Connection con = jdbcTemplate.getDataSource().getConnection(); CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setString(1, categoria.getNombreCategoria()); 
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al agregar categoría: " + e.getMessage();
        }

        return mensaje;
    }

    public String actualizarCategoria(CategoriaCrud categoria) {
        String mensaje = "";
        String sql = "{call sp_actualizar_categoria(?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection(); CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, categoria.getIdCategoria());
            cstmt.setString(2, categoria.getNombreCategoria());
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al actualizar categoría: " + e.getMessage();
        }

        return mensaje;
    }

    public String eliminarCategoria(Long idCategoria) {
        String mensaje = "";
        String sql = "{call sp_eliminar_categoria(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection(); CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, idCategoria);
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al eliminar categoría: " + e.getMessage();
        }

        return mensaje;
    }

    public CategoriaCrud buscarPorId(Long idCategoria) {
        CategoriaCrud categoria = null;
        String sql = "{call sp_obtener_categoria_por_id(?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection(); CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, idCategoria); // Primer parámetro (IN)
            cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR); // Segundo parámetro (OUT)
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(2)) { // Obtenemos el cursor
                if (rs.next()) {
                    categoria = new CategoriaCrud();
                    categoria.setIdCategoria(rs.getLong("id_categoria"));
                    categoria.setNombreCategoria(rs.getString("nombre_categoria"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoria;
    }

}

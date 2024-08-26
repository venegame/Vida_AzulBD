package com.vida.azul.Dao;

import com.vida.azul.Domain.TransporteCrud;
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
public class TransporteDaoCrud {

    private final JdbcTemplate jdbcTemplate;

    public TransporteDaoCrud(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<TransporteCrud> obtenerTransportes() {
        List<TransporteCrud> transportes = new ArrayList<>();
        String sql = "{call sp_leer_transportes(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(1)) {
                while (rs.next()) {
                    TransporteCrud transporte = new TransporteCrud();
                    transporte.setIdTransporte(rs.getLong("id_transporte"));
                    transporte.setIdUsuario(rs.getLong("id_usuario"));
                    transporte.setNombreTransporte(rs.getString("nombre_transporte"));
                    transporte.setRutaTransporte(rs.getString("ruta_transporte"));
                    transporte.setHorarioTransporte(rs.getString("horario_transporte"));
                    transporte.setPrecioTransporte(rs.getDouble("precio_transporte"));
                    transportes.add(transporte);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transportes;
    }

    public String agregarTransporte(TransporteCrud transporte) {
        String mensaje = "";
        String sql = "{call sp_agregar_transporte(?, ?, ?, ?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, transporte.getIdUsuario());
            cstmt.setString(2, transporte.getNombreTransporte());
            cstmt.setString(3, transporte.getRutaTransporte());
            cstmt.setString(4, transporte.getHorarioTransporte());
            cstmt.setDouble(5, transporte.getPrecioTransporte());
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al agregar transporte: " + e.getMessage();
        }

        return mensaje;
    }

    public String actualizarTransporte(TransporteCrud transporte) {
        String mensaje = "";
        String sql = "{call sp_actualizar_transporte(?, ?, ?, ?, ?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, transporte.getIdTransporte());
            cstmt.setLong(2, transporte.getIdUsuario());
            cstmt.setString(3, transporte.getNombreTransporte());
            cstmt.setString(4, transporte.getRutaTransporte());
            cstmt.setString(5, transporte.getHorarioTransporte());
            cstmt.setDouble(6, transporte.getPrecioTransporte());
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al actualizar transporte: " + e.getMessage();
        }

        return mensaje;
    }

    public String eliminarTransporte(Long idTransporte) {
        String mensaje = "";
        String sql = "{call sp_eliminar_transporte(?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, idTransporte);
            cstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error al eliminar transporte: " + e.getMessage();
        }

        return mensaje;
    }

    public TransporteCrud buscarPorId(Long idTransporte) {
        TransporteCrud transporte = null;
        String sql = "{call sp_buscar_transporte_por_id(?, ?)}";

        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setLong(1, idTransporte);
            cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(2)) {
                if (rs.next()) {
                    transporte = new TransporteCrud();
                    transporte.setIdTransporte(rs.getLong("id_transporte"));
                    transporte.setIdUsuario(rs.getLong("id_usuario"));
                    transporte.setNombreTransporte(rs.getString("nombre_transporte"));
                    transporte.setRutaTransporte(rs.getString("ruta_transporte"));
                    transporte.setHorarioTransporte(rs.getString("horario_transporte"));
                    transporte.setPrecioTransporte(rs.getDouble("precio_transporte"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transporte;
    }
}

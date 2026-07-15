package Aplicacion.DAO;

import Dominio.Modelo.DetalleVenta;
import Dominio.repository.ICRUD;
import Aplicacion.utils.ConexionMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetalleVentaDAO implements ICRUD<DetalleVenta, Integer> {

    public DetalleVentaDAO() {
    }
    @Override
    public int save(DetalleVenta beans) {
        Connection conexion = null;
        PreparedStatement ps = null;
        int resultado = 0;
        try {
            String sql = """
                    INSERT INTO DetalleVenta(idVenta, idAnimal, cantidad, precio, subtotal)
                    VALUES (?,?,?,?,?)
                    """;
            conexion = ConexionMySQL.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, beans.getIdVenta());
            ps.setInt(2, beans.getIdAnimal());
            ps.setInt(3, beans.getCantidad());
            ps.setDouble(4, beans.getPrecio());
            ps.setDouble(5, beans.getSubtotal());
            resultado = ps.executeUpdate();
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int update(DetalleVenta detalleVenta) {
        String sql = "UPDATE DetalleVenta "
                + "SET idVenta = ?, idAnimal = ?, cantidad = ?, precio = ?, subtotal = ? "
                + "WHERE idDetalle = ?";

        try (Connection conn = ConexionMySQL.getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, detalleVenta.getIdVenta());
            pstmt.setInt(2, detalleVenta.getIdAnimal());
            pstmt.setInt(3, detalleVenta.getCantidad());
            pstmt.setDouble(4, detalleVenta.getPrecio());
            pstmt.setDouble(5, detalleVenta.getSubtotal());
            pstmt.setInt(6, detalleVenta.getIdDetalle());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar detalle de venta", e);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM DetalleVenta WHERE idDetalle = ?";

        try (Connection conn = ConexionMySQL.getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar detalle de venta", e);
        }
    }

    @Override
    public Optional<DetalleVenta> findById(Integer id) {
    String sql = "SELECT * FROM DetalleVenta WHERE idDetalle = ?";

    try (Connection conn = ConexionMySQL.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, id);

        try (ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                DetalleVenta detalle = new DetalleVenta(
                        rs.getInt("idDetalle"),
                        rs.getInt("idVenta"),
                        rs.getInt("idAnimal"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio"),
                        rs.getDouble("subtotal")
                );

                return Optional.of(detalle);
            }
        }

        return Optional.empty();

    } catch (SQLException e) {
        throw new RuntimeException("Error al buscar detalle de venta", e);
    }
}


    @Override
public List<DetalleVenta> findAll() {
    List<DetalleVenta> lista = new ArrayList<>();
    String sql = "SELECT * FROM DetalleVenta";

    try (Connection conn = ConexionMySQL.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            lista.add(new DetalleVenta(
                    rs.getInt("idDetalle"),
                    rs.getInt("idVenta"),
                    rs.getInt("idAnimal"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio"),
                    rs.getDouble("subtotal")));
        }

        return lista;

    } catch (SQLException e) {
        throw new RuntimeException("Error al listar detalles de venta", e);
    }
}

    @Override
    public int saveAndFindId(DetalleVenta detalleVenta) {
        String sql = "INSERT INTO DetalleVenta(idVenta, idAnimal, cantidad, precio, subtotal) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, detalleVenta.getIdVenta());
            pstmt.setInt(2, detalleVenta.getIdAnimal());
            pstmt.setInt(3, detalleVenta.getCantidad());
            pstmt.setDouble(4, detalleVenta.getPrecio());
            pstmt.setDouble(5, detalleVenta.getSubtotal());

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

            return -1;

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar y obtener ID de detalle de venta", e);
        }
    }
}

package Aplicacion.repositoryimpl;

import Dominio.Modelo.DetalleCompra;
import Dominio.repository.CrudGenerico;
import Presentacion.Principal.ConexionMySQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetalleCompraRepositoryImpl implements CrudGenerico<DetalleCompra, Integer> {

    private final ProductoRepositoryImpl productoRepository;

    public DetalleCompraRepositoryImpl() {
        this.productoRepository = new ProductoRepositoryImpl();
    }

    @Override
    public int save(DetalleCompra detalleCompra) {
        String sql = "INSERT INTO detalle_compra (id_compra, id_producto, cantidad, subtotal) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, detalleCompra.getCompra().getIdCompra());
            pstmt.setInt(2, detalleCompra.getProducto().getIdProducto());
            pstmt.setDouble(3, detalleCompra.getCantidad());
            pstmt.setDouble(4, detalleCompra.getSubtotal());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar detalle de compra", e);
        }
    }

    @Override
    public int update(DetalleCompra detalleCompra) {
        String sql = "UPDATE detalle_compra "
                + "SET id_compra = ?, id_producto = ?, cantidad = ?, subtotal = ? "
                + "WHERE id_detalle = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, detalleCompra.getCompra().getIdCompra());
            pstmt.setInt(2, detalleCompra.getProducto().getIdProducto());
            pstmt.setDouble(3, detalleCompra.getCantidad());
            pstmt.setDouble(4, detalleCompra.getSubtotal());
            pstmt.setInt(5, detalleCompra.getIdDetalle());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar detalle de compra", e);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM detalle_compra WHERE id_detalle = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar detalle de compra", e);
        }
    }

    @Override
    public Optional<DetalleCompra> findById(Integer id) {
        String sql = "SELECT * FROM detalle_compra WHERE id_detalle = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapearDetalleCompra(rs));
                }
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar detalle de compra", e);
        }
    }

    @Override
    public List<DetalleCompra> findAll() {
        List<DetalleCompra> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detalle_compra";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                detalles.add(mapearDetalleCompra(rs));
            }

            return detalles;
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar detalles de compra", e);
        }
    }

    @Override
    public int saveAndFinId(DetalleCompra detalleCompra) {
        String sql = "INSERT INTO detalle_compra (id_compra, id_producto, cantidad, subtotal) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, detalleCompra.getCompra().getIdCompra());
            pstmt.setInt(2, detalleCompra.getProducto().getIdProducto());
            pstmt.setDouble(3, detalleCompra.getCantidad());
            pstmt.setDouble(4, detalleCompra.getSubtotal());

            int filas = pstmt.executeUpdate();
            if (filas == 0) {
                return -1;
            }

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

            return -1;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar y obtener ID de detalle de compra", e);
        }
    }

    public List<DetalleCompra> listarPorFecha(Date fecha, Date fecha2) {
        List<DetalleCompra> detalles = new ArrayList<>();
        String sql = "SELECT dc.* "
                + "FROM detalle_compra dc "
                + "INNER JOIN compra c ON dc.id_compra = c.id_compra "
                + "WHERE c.fecha BETWEEN ? AND ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, fecha);
            pstmt.setDate(2, fecha2);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    detalles.add(mapearDetalleCompra(rs));
                }
            }

            return detalles;
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar detalles de compra por fecha", e);
        }
    }

    public DetalleCompra ObtenerPorId(Long id) {
        return findById(id.intValue()).orElse(null);
    }

    public List<DetalleCompra> Listar() {
        return findAll();
    }

    private DetalleCompra mapearDetalleCompra(ResultSet rs) throws SQLException {
        return new DetalleCompra(
                rs.getInt("id_detalle"),
                null,
                productoRepository.findById(rs.getInt("id_producto")).orElse(null),
                rs.getDouble("cantidad"),
                rs.getDouble("subtotal")
        );
    }
}

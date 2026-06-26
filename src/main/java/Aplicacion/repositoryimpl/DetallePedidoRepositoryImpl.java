package Aplicacion.repositoryimpl;

import Dominio.Modelo.DetallePedido;
import Dominio.repository.CrudGenerico;
import Presentacion.Principal.ConexionMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetallePedidoRepositoryImpl implements CrudGenerico<DetallePedido, Integer> {

    private final ProductoRepositoryImpl productoRepository;

    public DetallePedidoRepositoryImpl() {
        this.productoRepository = new ProductoRepositoryImpl();
    }

    @Override
    public int save(DetallePedido detallePedido) {
        String sql = """
                INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, subtotal)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, detallePedido.getPedido().getIdPedido());
            pstmt.setInt(2, detallePedido.getProducto().getIdProducto());
            pstmt.setInt(3, detallePedido.getCantidad());
            pstmt.setDouble(4, detallePedido.getSubTotal());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar detalle de pedido", e);
        }
    }

    @Override
    public int update(DetallePedido detallePedido) {
        String sql = """
                UPDATE detalle_pedido
                SET id_pedido = ?, id_producto = ?, cantidad = ?, subtotal = ?
                WHERE id_detalle = ?
                """;

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, detallePedido.getPedido().getIdPedido());
            pstmt.setInt(2, detallePedido.getProducto().getIdProducto());
            pstmt.setInt(3, detallePedido.getCantidad());
            pstmt.setDouble(4, detallePedido.getSubTotal());
            pstmt.setInt(5, detallePedido.getIdDetalle());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar detalle de pedido", e);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM detalle_pedido WHERE id_detalle = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar detalle de pedido", e);
        }
    }

    @Override
    public Optional<DetallePedido> findById(Integer id) {
        String sql = "SELECT * FROM detalle_pedido WHERE id_detalle = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapearDetallePedido(rs));
                }
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar detalle de pedido", e);
        }
    }

    @Override
    public List<DetallePedido> findAll() {
        List<DetallePedido> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detalle_pedido";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                detalles.add(mapearDetallePedido(rs));
            }

            return detalles;
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar detalles de pedido", e);
        }
    }

    @Override
    public int saveAndFinId(DetallePedido detallePedido) {
        String sql = """
                INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, subtotal)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, detallePedido.getPedido().getIdPedido());
            pstmt.setInt(2, detallePedido.getProducto().getIdProducto());
            pstmt.setInt(3, detallePedido.getCantidad());
            pstmt.setDouble(4, detallePedido.getSubTotal());

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
            throw new RuntimeException("Error al guardar y obtener ID de detalle de pedido", e);
        }
    }

    public List<DetallePedido> listarDetallePedidoPorId(Integer idPedido) {
        List<DetallePedido> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detalle_pedido WHERE id_pedido = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idPedido);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    detalles.add(mapearDetallePedido(rs));
                }
            }

            return detalles;
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar detalles por pedido", e);
        }
    }

    private DetallePedido mapearDetallePedido(ResultSet rs) throws SQLException {
        return new DetallePedido(
                rs.getInt("id_detalle"),
                null,
                productoRepository.findById(rs.getInt("id_producto")),
                rs.getInt("cantidad"),
                rs.getDouble("subtotal")
        );
    }
}

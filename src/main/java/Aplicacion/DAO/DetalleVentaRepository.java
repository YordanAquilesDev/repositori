package Aplicacion.DAO;

import Aplicacion.Service.ProductoService;
import Dominio.Modelo.DetalleVenta;
import Dominio.Modelo.Venta;
import Dominio.repository.ICRUD;
import Aplicacion.utils.ConexionMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetalleVentaRepository implements ICRUD<DetalleVenta, Integer> {

    private final ProductoService productoService;
  public DetalleVentaRepository() {
       this.productoService = new ProductoService();
  }
    @Override
    public int save(DetalleVenta beans) {
        Connection conexion = null;
        PreparedStatement ps = null;
        int resultado=0;
        try {
            String sql= """
                    INSERT INTO detalleVenta VALUES (?,?,?,?,?)
                    """;
            conexion = ConexionMySQL.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, beans.getIdVenta());
            ps.setInt(2, beans.getIdAnimal());
            ps.setInt(3,beans.getCantidad());
            ps.setDouble(4, beans.getPrecio());
            ps.setDouble(4, beans.getSubtotal());
            resultado=ps.executeUpdate();
            return  resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                if (ps != null) ps.close();
                if(conexion!= null) conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int update(DetalleVenta detalleVenta) {
        String sql = "UPDATE detalle_venta "
                + "SET id_venta = ?, id_producto = ?, cantidad = ?, subtotal = ? "
                + "WHERE id_detalle = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, detalleVenta.getIdVenta());
            pstmt.setInt(2, detalleVenta.getIdProducto());
            pstmt.setDouble(3, detalleVenta.getCantidad());
            pstmt.setDouble(4, detalleVenta.getSubtotal());
            pstmt.setInt(5, detalleVenta.getIdDetalle());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar detalle de venta", e);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM detalle_venta WHERE id_detalle = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar detalle de venta", e);
        }
    }

    @Override
    public Optional<DetalleVenta> findById(Integer id) {
      Connection conexion = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
        String sql = "SELECT * FROM detalle_venta WHERE id_detalle = ?";
        try (Connection conn = ConexionMySQL.getConexionMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
           Venta venta= new Venta();
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    venta.setIdVenta(rs.getInt("id_detalle"));
                    return Optional.of(
                            new DetalleVenta(
                            rs.getInt("id_detalle"),
                            rs.getInt("id_venta"),
                            rs.getInt("id_producto"),
                            rs.getInt("cantidad"),
                            rs.getDouble("cantidad"),
                            rs.getDouble("subtotal")
                    ));
                }
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar detalle de venta", e);
        }
    }

    @Override
    public List<DetalleVenta> findAll() {
      Connection conexion = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
        List<DetalleVenta> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_venta";

        try (Connection conn = ConexionMySQL.getConexionMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new DetalleVenta(
                        rs.getInt("id_detalle"),
                        null,
                        productoService.findById(rs.getInt("id_producto")).orElse(null),
                        rs.getDouble("cantidad"),
                        rs.getDouble("subtotal")
                ));
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar detalles de venta", e);
        }
    }

    @Override
    public int saveAndFindId(DetalleVenta detalleVenta) {
        String sql = "INSERT INTO detalle_venta (id_venta, id_producto, cantidad, subtotal) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, detalleVenta.getVenta().getIdVenta());
            pstmt.setInt(2, detalleVenta.getProducto().getIdProducto());
            pstmt.setDouble(3, detalleVenta.getCantidad());
            pstmt.setDouble(4, detalleVenta.getSubtotal());

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

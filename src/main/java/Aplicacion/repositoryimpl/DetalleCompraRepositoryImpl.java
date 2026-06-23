package Aplicacion.repositoryimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dominio.Modelo.DetalleCompra;
import Dominio.Modelo.Producto;
import Presentacion.Principal.ConexionPostgresSQL;

public class DetalleCompraRepositoryImpl implements DetalleCompraRepository {
    Connection conexion;

    private final ProductoRepository productoRepository;
    public DetalleCompraRepositoryImpl() {
        this.conexion = ConexionPostgresSQL.getConexion();

        this.productoRepository = new ProductoRepositoryImpl();
    }

    @Override
    public int  save (DetalleCompra nuevoDetalle) {
        String sql = """
                INSERT INTO detalle_compra (id_compra, id_producto, cantidad, subtotal)
                VALUES (?, ?, ?, ?) RETURNING *
                """;
        int resultado=-1;

        try {
            int i = 0;

            for (Producto p : nuevoDetalle.getProductos()) {
                PreparedStatement preparar = conexion.prepareStatement(sql);
                preparar.setInt(1, nuevoDetalle.getCompra().getIdCompra());
                preparar.setInt(2, p.getIdProducto());
                preparar.setInt(3, nuevoDetalle.getCantidad().get(i));
                preparar.setDouble(4, nuevoDetalle.getSubtotal().get(i));
               resultado+= preparar.executeUpdate();

            }
            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el detalle de compra", e);
        }
    }

    public List<DetalleCompra> listarPorFecha(Date fecha, Date fecha2) {
        List<DetalleCompra> detalles = new ArrayList<>();
        List<Producto> productos = new ArrayList<>();
        List<Integer> cantidades = new ArrayList<>();
        List<Double > subtotals = new ArrayList<>();
        try {
            String sql = """
                    selet * from detalle_compra
                    where fecha between ? and ?;

                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            preparar.setDate(1, fecha);
            preparar.setDate(2, fecha2);
            ResultSet rs = preparar.executeQuery();
            int temp = 1;
            while (rs.next()) {
                productos.add(productoRepository.buscarPorId(rs.getInt("id_producto")));
                cantidades.add(rs.getInt("cantidad"));
                subtotals.add(rs.getDouble("subtotal"));
                if (temp < rs.getInt("id_detalle")) {
                    detalles.add(new DetalleCompra(
                            rs.getInt("id_detalle"),
                           null,
                           // compraRepository.traerCompraPorId(rs.getInt("id_compra")),
                            productos,
                            cantidades,
                            subtotals));

                    temp = rs.getInt("id_detalle");
                    productos = new ArrayList<>();
                    cantidades = new ArrayList<>();
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public DetalleCompra ObtenerPorId(Long id) {
        List<Producto> productos = new ArrayList<>();
        List<Integer> cantidades = new ArrayList<>();
        List<Double > subtotals = new ArrayList<>();
        try {
            String sql = """
                    SELECT * FROM detalle_compra
                    WHERE id_detalle= ?
                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            preparar.setLong(1, id);
            ResultSet rs = preparar.executeQuery();
            if (rs.next()) {
                productos.add(productoRepository.buscarPorId(rs.getInt("id_producto")));
                cantidades.add(rs.getInt("cantidad"));
                subtotals.add(rs.getDouble("subtotal"));
                if (rs.next()) {
                    return new DetalleCompra(
                            rs.getInt("id_detalle"),
                            null,// entero
                           // compraRepository.traerCompraPorId(rs.getInt("id_compra")),
                            productos,
                            cantidades,
                            subtotals);

                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<DetalleCompra> Listar() {
        List<DetalleCompra> detalles = new ArrayList<>();
        List<Producto> productos = new ArrayList<>();
        List<Integer> cantidades = new ArrayList<>();
        List<Double > subtotals = new ArrayList<>();
        try {
            String sql = """
                    SELECT * FROM detalle_compra
                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            ResultSet rs = preparar.executeQuery();
            int temp = 1;
            while (rs.next()) {
                productos.add(productoRepository.buscarPorId(rs.getInt("id_producto")));
                cantidades.add(rs.getInt("cantidad"));
                subtotals.add(rs.getDouble("subtotal"));
                if (temp < rs.getInt("id_detalle")) {
                    detalles.add(new DetalleCompra(
                            rs.getInt("id_detalle"),
                          null,
                          //  compraRepository.traerCompraPorId(rs.getInt("id_compra")),
                            productos,
                            cantidades,
                            subtotals));

                    temp = rs.getInt("id_detalle");
                    productos = new ArrayList<>();
                    cantidades = new ArrayList<>();
                }
            }
            return detalles;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

package Aplicacion.repositoryimpl;

import Dominio.Modelo.DetalleVenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DetalleVentaRepositoryImpl implements DetalleVentaRepository {

    @Override
    public int save(DetalleVenta detalleVenta) {

        int respuesta = -1;
        Connection conexion = null;
        PreparedStatement preparar = null;

        try {

            String sql = """
                    INSERT INTO detalle_venta
                    (id_venta,id_producto,cantidad,subtotal)
                    VALUES (?,?,?,?)
                    """;

            preparar = conexion.prepareStatement(sql);

            preparar.setInt(1, detalleVenta.getVenta().getIdVenta());
            preparar.setInt(2, detalleVenta.getProducto().getIdProducto());
            preparar.setInt(3, detalleVenta.getCantidad());
            preparar.setDouble(4, detalleVenta.getSubtotal());

            respuesta = preparar.executeUpdate();

            return respuesta;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {

                if (preparar != null) preparar.close();
                if (conexion != null) conexion.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int update(DetalleVenta detalleVenta) {

        int respuesta = -1;
        Connection conexion = null;
        PreparedStatement preparar = null;

        try {

            String sql = """
                    UPDATE detalle_venta
                    SET id_venta = ?,
                        id_producto = ?,
                        cantidad = ?,
                        subtotal = ?
                    WHERE id_detalle = ?
                    """;

            preparar = conexion.prepareStatement(sql);

            preparar.setInt(1, detalleVenta.getVenta().getIdVenta());
            preparar.setInt(2, detalleVenta.getProducto().getIdProducto());
            preparar.setInt(3, detalleVenta.getCantidad());
            preparar.setDouble(4, detalleVenta.getSubtotal());
            preparar.setInt(5, detalleVenta.getIdDetalle());

            respuesta = preparar.executeUpdate();

            return respuesta;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {

                if (preparar != null) preparar.close();
                if (conexion != null) conexion.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int delete(DetalleVenta detalleVenta) {
        return 0;
    }

    @Override
    public List<DetalleVenta> listarDetalleVentaPorId(Integer id) {
        return List.of();
    }
}
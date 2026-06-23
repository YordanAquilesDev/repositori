package Aplicacion.repositoryimpl;

import Dominio.Modelo.DetallePedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DetallePedidoRepositoryImpl implements DetallePedidoRepository {

    @Override
    public int save(DetallePedido detallePedido) {
        int respuesta = -1;
        Connection conexion = null;
        PreparedStatement preparar = null;
        try{
            String sql= """
                    INSERT INTO detalle_pedido (id_pedido,id_producto,cantidad,subtotal)
                    VALUES (?,?,?,?) RETURNING *;
                    """;

            preparar= conexion.prepareStatement(sql);
            preparar.setInt(1,detallePedido.getPedido().getIdPedido());
            preparar.setInt(2,detallePedido.getProducto().getIdProducto());
            preparar.setInt(3,detallePedido.getCantidad());
            preparar.setDouble(4,detallePedido.getSubTotal());
            respuesta = preparar.executeUpdate();
           return respuesta;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                if(conexion!=null) conexion.close();
                if(preparar!=null)preparar.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public int update(DetallePedido detallePedido) {
        int respuesta = -1;
        Connection conexion = null;
        PreparedStatement preparar = null;
        try{
            String sql= """
                    UPDATE detalle_pedido
                    SET 
                    """;

            preparar= conexion.prepareStatement(sql);
            preparar.setInt(1,detallePedido.getPedido().getIdPedido());
            preparar.setInt(2,detallePedido.getProducto().getIdProducto());
            preparar.setInt(3,detallePedido.getCantidad());
            preparar.setDouble(4,detallePedido.getSubTotal());
            respuesta = preparar.executeUpdate();
            return respuesta;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                if(conexion!=null) conexion.close();
                if(preparar!=null)preparar.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int delete(DetallePedido detallePedido) {
        return 0;
    }

    @Override
    public List<DetallePedido> listarDetallePedidoPorId(Integer id) {
        return List.of();
    }
}

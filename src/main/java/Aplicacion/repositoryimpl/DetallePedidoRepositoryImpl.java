package Aplicacion.repositoryimpl;

import Dominio.Modelo.DetallePedido;
import Dominio.repository.DetallePedidoRepository;
import Dominio.repository.PedidoRepository;
import Presentacion.Principal.ConexionPostgresSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetallePedidoRepositoryImpl implements DetallePedidoRepository {
    Connection conexion;
    public DetallePedidoRepositoryImpl() {
        this.conexion = ConexionPostgresSQL.getConexion();
    }
    @Override
    public DetallePedido guardar(DetallePedido detallePedido) {
        try{
            String sql= """
                    INSERT INTO detalle_pedido (id_pedido,id_producto,cantidad,subtotal)
                    VALUES (?,?,?,?) RETURNING *;
                    """;

            PreparedStatement preparar= conexion.prepareStatement(sql);
            preparar.setInt(1,detallePedido.getPedido().getIdPedido());
            preparar.setInt(2,detallePedido.getProducto().getIdProducto());
            preparar.setInt(3,detallePedido.getCantidad());
            preparar.setDouble(4,detallePedido.getSubTotal());
            ResultSet resultado= preparar.executeQuery();
            if(resultado.next()){
                detallePedido.setIdDetalle(resultado.getInt("id_detalle"));
                return detallePedido;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

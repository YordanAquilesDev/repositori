package Dominio.repository;

import Dominio.Modelo.DetallePedido;

import java.util.List;

public interface DetallePedidoRepository {
    //CRUD de la tabla detalle_pedido
    int save(DetallePedido detallePedido);

    int update(DetallePedido detallePedido);
    int delete(DetallePedido detallePedido);
    List<DetallePedido> listarDetallePedidoPorId(Integer id);


}

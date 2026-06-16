package Dominio.Service;

import java.util.List;

import Dominio.Modelo.DetallePedido;

public interface DetallePedidoService {
    //logica para la tabla detalle_pedido
    int guardarUnDetallePedido(DetallePedido detallePedido);

    DetallePedido obtenerUnDetallePedidoPorId(Long id);

    List<DetallePedido> obtenerTodosLosDetallePedidos();

}

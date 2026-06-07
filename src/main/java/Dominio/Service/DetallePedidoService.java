package Dominio.Service;

import java.util.List;

import Dominio.Modelo.DetallePedido;

public interface DetallePedidoService {
    DetallePedido guardarUnDetallePedido(DetallePedido detallePedido);

    DetallePedido obtenerUnDetallePedidoPorId(Long id);

    List<DetallePedido> obtenerTodosLosDetallePedidos();

}

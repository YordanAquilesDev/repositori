package Dominio.Service;

import java.util.List;

import Dominio.Modelo.Pedido;

public interface PedidoService {

    Pedido guardarUnPedido(Pedido pedido);

    Pedido obtenerUnPedidoPorId(Long id);

    List<Pedido> obtenerTodosLosPedidos();

    List<Pedido> obtenerPedidosEntregados();

    List<Pedido> obtenerPedidosNoEntregados();

}

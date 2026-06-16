package Dominio.Service;

import java.util.List;

import Dominio.Modelo.Pedido;

public interface PedidoService {
    //logica para la tabla pedido
    Pedido guardarUnPedido(Pedido pedido);

    Pedido obtenerUnPedidoPorId(Long id);

    List<Pedido> obtenerTodosLosPedidos();

    List<Pedido> obtenerPedidosEntregados();

    List<Pedido> obtenerPedidosNoEntregados();
    List<Pedido> pedidoPorFiltro(String filtro);
    int actualizarPedido(Pedido pedido);

}

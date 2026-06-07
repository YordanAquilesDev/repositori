package Dominio.repository;

import Dominio.Modelo.Compra;
import Dominio.Modelo.Pedido;

import java.util.List;

public interface PedidoRepository {
    List<Pedido> listarPedidos();

    Pedido ActualizarPedido(Pedido pedido);
    List<Pedido> listarPedidosEntregados();
    List<Pedido> listarPedidosNoEntregados();
}

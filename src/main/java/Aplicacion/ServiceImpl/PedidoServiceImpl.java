package Aplicacion.ServiceImpl;

import Dominio.Modelo.Pedido;
import Dominio.Service.PedidoService;

import java.util.List;

public class PedidoServiceImpl implements PedidoService {
    @Override
    public Pedido guardarUnPedido(Pedido pedido) {
        return null;
    }

    @Override
    public Pedido obtenerUnPedidoPorId(Long id) {
        return null;
    }

    @Override
    public List<Pedido> obtenerTodosLosPedidos() {
        return List.of();
    }

    @Override
    public List<Pedido> obtenerPedidosEntregados() {
        return List.of();
    }

    @Override
    public List<Pedido> obtenerPedidosNoEntregados() {
        return List.of();
    }

    @Override
    public List<Pedido> pedidoPorFiltro(String filtro) {
        return List.of();
    }

    @Override
    public int actualizarPedido(Pedido pedido) {
        return 0;
    }
}

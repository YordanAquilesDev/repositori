package Aplicacion.ServiceImpl;

import Aplicacion.repositoryimpl.PedidoRepositoryImpl;
import Dominio.Modelo.Pedido;
import Dominio.Service.ServiceGenerico;

import java.util.List;
import java.util.Optional;

public class PedidoServiceImpl implements ServiceGenerico<Pedido, Integer> {

    private final PedidoRepositoryImpl pedidoRepository;

    public PedidoServiceImpl() {
        this.pedidoRepository = new PedidoRepositoryImpl();
    }

    @Override
    public int save(Pedido beans) {
        if (beans == null || beans.getCliente() == null) return -1;
        return pedidoRepository.save(beans);
    }

    @Override
    public int update(Pedido beans) {
        if (beans == null || beans.getIdPedido() <= 0) return -1;
        return pedidoRepository.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id < 0) return -1;
        return pedidoRepository.delete(id);
    }

    @Override
    public Optional<Pedido> findById(Integer id) {
        if (id == null || id < 0) return Optional.empty();
        return pedidoRepository.findById(id);
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public int saveAndFinId(Pedido beans) {
        if (beans == null || beans.getCliente() == null) return -1;
        return pedidoRepository.saveAndFinId(beans);
    }

    public Pedido obtenerUnPedidoPorId(Long id) {
        return findById(id.intValue()).orElse(null);
    }

    public List<Pedido> obtenerTodosLosPedidos() {
        return findAll();
    }

    public int actualizarPedido(Pedido pedido) {
        return update(pedido);
    }
}

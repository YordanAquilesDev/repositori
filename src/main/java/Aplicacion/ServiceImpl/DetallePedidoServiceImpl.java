package Aplicacion.ServiceImpl;

import Aplicacion.repositoryimpl.DetallePedidoRepositoryImpl;
import Dominio.Modelo.DetallePedido;
import Dominio.Service.ServiceGenerico;

import java.util.List;
import java.util.Optional;

public class DetallePedidoServiceImpl implements ServiceGenerico<DetallePedido, Integer> {

    private final DetallePedidoRepositoryImpl detallePedidoRepository;

    public DetallePedidoServiceImpl() {
        this.detallePedidoRepository = new DetallePedidoRepositoryImpl();
    }

    @Override
    public int save(DetallePedido beans) {
        if (beans == null) return -1;
        return detallePedidoRepository.save(beans);
    }

    @Override
    public int update(DetallePedido beans) {
        if (beans == null || beans.getIdDetalle() <= 0) return -1;
        return detallePedidoRepository.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id < 0) return -1;
        return detallePedidoRepository.delete(id);
    }

    @Override
    public Optional<DetallePedido> findById(Integer id) {
        if (id == null || id < 0) return Optional.empty();
        return detallePedidoRepository.findById(id);
    }

    @Override
    public List<DetallePedido> findAll() {
        return detallePedidoRepository.findAll();
    }

    @Override
    public int saveAndFinId(DetallePedido beans) {
        if (beans == null) return -1;
        return detallePedidoRepository.saveAndFinId(beans);
    }
}

package Aplicacion.ServiceImpl;

import Aplicacion.repositoryimpl.DetalleCompraRepositoryImpl;
import Dominio.Modelo.DetalleCompra;
import Dominio.Service.ServiceGenerico;

import java.util.List;
import java.util.Optional;

public class DetalleCompraServiceImpl implements ServiceGenerico<DetalleCompra, Integer> {

    private final DetalleCompraRepositoryImpl detalleCompraRepository;

    public DetalleCompraServiceImpl() {
        this.detalleCompraRepository = new DetalleCompraRepositoryImpl();
    }

    @Override
    public int save(DetalleCompra beans) {
        if (beans == null) return -1;
        return detalleCompraRepository.save(beans);
    }

    @Override
    public int update(DetalleCompra beans) {
        if (beans == null || beans.getIdDetalle() <= 0) return -1;
        return detalleCompraRepository.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id < 0) return -1;
        return detalleCompraRepository.delete(id);
    }

    @Override
    public Optional<DetalleCompra> findById(Integer id) {
        if (id == null || id < 0) return Optional.empty();
        return detalleCompraRepository.findById(id);
    }

    @Override
    public List<DetalleCompra> findAll() {
        return detalleCompraRepository.findAll();
    }

    @Override
    public int saveAndFinId(DetalleCompra beans) {
        if (beans == null) return -1;
        return detalleCompraRepository.saveAndFinId(beans);
    }
}

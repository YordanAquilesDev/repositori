package Aplicacion.ServiceImpl;

import java.util.List;

import Aplicacion.repositoryimpl.DetalleCompraRepositoryImpl;
import Dominio.Modelo.DetalleCompra;

public class DetalleCompraServiceImpl implements DetalleCompraService {
    private final DetalleCompraRepository detalleCompraRepository;

    public DetalleCompraServiceImpl() {
        this.detalleCompraRepository = new DetalleCompraRepositoryImpl();

    }

    public int guardarUnDetalleCompra(DetalleCompra detalleCompra) {
        return detalleCompraRepository.save(detalleCompra);
    }

    public DetalleCompra obtenerUnDetalleCompraPorId(Long id) {
        return detalleCompraRepository.ObtenerPorId(id);
    }

    public List<DetalleCompra> obtenerTodosLosDetalleCompras() {
        return detalleCompraRepository.Listar();
    }
}

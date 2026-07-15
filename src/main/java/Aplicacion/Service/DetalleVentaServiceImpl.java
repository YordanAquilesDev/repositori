package Aplicacion.Service;

import Aplicacion.DAO.DetalleVentaRepository;
import Dominio.Modelo.DetalleVenta;
import Dominio.Modelo.Producto;
import Dominio.repository.ICRUD;

import java.util.List;
import java.util.Optional;

public class DetalleVentaServiceImpl implements ICRUD<DetalleVenta, Integer> {

    private final ProductoService productoService;
    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaServiceImpl() {
        // esto viene de de VentaServiceImpl
        this.productoService = new ProductoService();
        this.detalleVentaRepository = new DetalleVentaRepository();
    }

    @Override
    public int save(DetalleVenta detalleVenta) {

        return -1;
    }

    @Override
    public int update(DetalleVenta beans) {
        return detalleVentaRepository.update(beans);
    }

    @Override
    public int delete(Integer integer) {
        if (integer == null || integer < 0) {
            throw new IllegalArgumentException("valores de objetos nulos ");
        }
        return detalleVentaRepository.delete(integer);
    }

    @Override
    public Optional<DetalleVenta> findById(Integer integer) {
        if (integer == null || integer < 0) {
            throw new IllegalArgumentException("valores de objetos nulos ");
        }
        return detalleVentaRepository.findById(integer);
    }

    @Override
    public List<DetalleVenta> findAll() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public int saveAndFindId(DetalleVenta beans) {
        if (beans == null) return -1;
        return detalleVentaRepository.saveAndFindId(beans);
    }
}

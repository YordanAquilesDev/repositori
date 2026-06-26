package Aplicacion.ServiceImpl;

import Aplicacion.repositoryimpl.DetalleVentaRepositoryImpl;
import Dominio.Modelo.DetalleVenta;
import Dominio.Modelo.Producto;
import Dominio.Service.ServiceGenerico;

import java.util.List;
import java.util.Optional;

public class DetalleVentaServiceImpl implements ServiceGenerico<DetalleVenta, Integer> {

    private final ProductoServiceImpl productoService;
    private final DetalleVentaRepositoryImpl detalleVentaRepository;

    public DetalleVentaServiceImpl() {
        this.productoService = new ProductoServiceImpl();
        this.detalleVentaRepository = new DetalleVentaRepositoryImpl();
    }

    @Override
    public int save(DetalleVenta detalleVenta) {
        if (detalleVenta == null || detalleVenta.getVenta() == null || detalleVenta.getProducto() == null) {
            throw new IllegalArgumentException("valores de objetos nulos ");
        }

        Producto p = productoService.findById(detalleVenta.getProducto().getIdProducto()).orElse(null);
        if (p == null) {
            throw new IllegalArgumentException("producto no encontrado");
        }

        if (p.getStockActual() > detalleVenta.getCantidad()) {
            double subtotal = p.getPrecioUnidad() * detalleVenta.getCantidad();
            detalleVenta.setSubtotal(subtotal);
            int resultadoDetalleVenta = detalleVentaRepository.save(detalleVenta);
            if (resultadoDetalleVenta > 0) {
                p.setStockActual(p.getStockActual() - detalleVenta.getCantidad());
                int resultado = productoService.update(p);
                if (resultado > 0) {
                    return 1;
                }
                return 0;
            }
        } else {
            throw new IllegalArgumentException("stock insuficiente");
        }
        return -1;
    }

    @Override
    public int update(DetalleVenta beans) {
        if (beans == null || beans.getVenta() == null || beans.getProducto() == null) {
            throw new IllegalArgumentException("valores de objetos nulos ");
        }
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
    public int saveAndFinId(DetalleVenta beans) {
        if (beans == null) return -1;
        return detalleVentaRepository.saveAndFinId(beans);
    }
}

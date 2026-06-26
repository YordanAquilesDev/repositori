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
        int resultado = 0;
        double subtotal = 0;
        //verificamos que  DetalleVenta no sea nulo
        if (detalleVenta == null || detalleVenta.getVenta() == null || detalleVenta.getProducto() == null) {
            throw new IllegalArgumentException("valores de objetos nulos ");
        }
        // recuperamos el producto  del detalle por su id
        Producto p = productoService.findById(detalleVenta.getProducto().getIdProducto());
        // validamos si el valor del detalleVenta es mayor al de stock
        //  no puede ser mayor al stock disponible
        if (p.getStock() > detalleVenta.getCantidad()) {
            // calculamos el subtotal  del detalleVenta
            subtotal = p.getPrecio() * detalleVenta.getCantidad();
            detalleVenta.setSubtotal(subtotal);
            int resultadoDetalleVenta = detalleVentaRepository.save(detalleVenta);
            //si se guardo correctamente
            if (resultadoDetalleVenta > 0) {
                //restamos al stock la cantidad del producto vendido
                p.setStock(p.getStock() - detalleVenta.getCantidad());
                //actualizamos la tabla productos
                resultado = productoService.update(p);
                if (resultado > 0) {
                    //si se guardo el detalleVenta y se actualizo la tabla producto
                    return 1;
                }
                // si solo se guardo el detalle y no se actualizo la tabla productos
                return 0;
            }

        } else {
            throw new IllegalArgumentException("stock negativo");
        }
        return -1;
    }

    @Override
    public int update(DetalleVenta beans) {
        if (beans == null || beans.getVenta() == null || beans.getProducto() == null) {
            throw new IllegalArgumentException("valores de objetos nulos ");
        }
        return detalleVentaRepository.delete(beans.getIdDetalle());
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
        return 0;
    }
}

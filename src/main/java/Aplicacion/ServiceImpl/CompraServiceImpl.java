package Aplicacion.ServiceImpl;

import java.sql.Date;
import java.util.List;

import Aplicacion.repositoryimpl.CompraRepositoryImpl;
import Dominio.Modelo.Compra;
import Dominio.Modelo.DetalleCompra;
import Dominio.Service.CompraService;
import Dominio.repository.CompraRepository;

public class CompraServiceImpl implements CompraService {
    private final CompraRepository compraRepository;

    public CompraServiceImpl() {
        this.compraRepository = new CompraRepositoryImpl();
    }

    public Compra guardarCompra(DetalleCompra detalleCompra) {
        if (detalleCompra == null) {
            return null;
        }
        if (detalleCompra.getCompra() == null ||
                detalleCompra.getProductos() == null ||
                detalleCompra.getCantidad() == null ||
                detalleCompra.getSubtotal().isEmpty()) {
            return null;
        } else {
            return compraRepository.guardarCompra(detalleCompra);
        }

    }

    public Compra obtenerCompraPorId(Long id) {
        Integer idNativo = Integer.parseInt(id.toString());
        return compraRepository.traerCompraPorId(idNativo);
    }

    public List<Compra> obtenerTodasLasCompras() {
        return compraRepository.listarCompras();
    }

    public List<Compra> obtenerComprasPorFecha(Date fecha, Date fecha2) {
        return compraRepository.listarComprasPorFecha(fecha, fecha2);
    }

}

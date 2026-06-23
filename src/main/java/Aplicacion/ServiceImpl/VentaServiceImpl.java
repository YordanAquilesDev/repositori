package Aplicacion.ServiceImpl;

import java.util.List;

import Aplicacion.repositoryimpl.VentaRepositoryImpl;
import Dominio.Modelo.Venta;
import Dominio.Service.VentaService;
import Dominio.repository.VentaRepository;

public class VentaServiceImpl implements VentaService {
    private final VentaRepositoryImpl ventaRepository;

    public VentaServiceImpl() {
        this.ventaRepository = null;
    }

    @Override
    public Venta guardarUnaVenta(Venta venta) {
        ventaRepository.guardar(venta);
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Venta obtenerUnaVentaPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Venta> obtenerTodasLasVentas() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

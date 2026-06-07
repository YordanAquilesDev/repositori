package Aplicacion.ImplService;

import Dominio.Modelo.Compra;
import Dominio.Modelo.DetalleCompra;
import Dominio.Service.CompraService;

import java.sql.Date;
import java.util.List;
// no sirve
public class ServiceCompraImpl implements CompraService {
    @Override
    public Compra guardarCompra(DetalleCompra detalleCompra) {
        return null;
    }

    @Override
    public Compra obtenerCompraPorId(Long id) {
        return null;
    }

    @Override
    public List<Compra> obtenerTodasLasCompras() {
        return List.of();
    }

    @Override
    public List<Compra> obtenerComprasPorFecha(Date fecha, Date fecha2) {
        return List.of();
    }
}

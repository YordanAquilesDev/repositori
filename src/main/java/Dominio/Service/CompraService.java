package Dominio.Service;

import java.sql.Date;
import java.util.List;

import Dominio.Modelo.Compra;
import Dominio.Modelo.DetalleCompra;

public interface CompraService {

    int save(DetalleCompra detalleCompra);

    Compra obtenerCompraPorId(Long id);

    List<Compra> obtenerTodasLasCompras();

    List<Compra> obtenerComprasPorFecha(Date fecha, Date fecha2);

}

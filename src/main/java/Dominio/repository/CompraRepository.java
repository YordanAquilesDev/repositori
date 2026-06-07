package Dominio.repository;

import java.sql.Date;
import java.util.List;

import Dominio.Modelo.Compra;
import Dominio.Modelo.DetalleCompra;

public interface CompraRepository {
    Compra guardarCompra(DetalleCompra detalleCompra);

    Compra traerCompraPorId(Integer id);

    List<Compra> listarCompras();

    List<Compra> listarComprasMasAltos();

    List<Compra> listarComprasPorFecha(Date fecha, Date fecha2);

}

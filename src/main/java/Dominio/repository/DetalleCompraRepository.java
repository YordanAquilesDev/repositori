package Dominio.repository;

import java.sql.Date;
import java.util.List;

import Dominio.Modelo.DetalleCompra;

public interface DetalleCompraRepository {
    //CRUD de la table detalle_compra
    int save(DetalleCompra detalleCompra);

    DetalleCompra ObtenerPorId(Long id);

    List<DetalleCompra> Listar();

    List<DetalleCompra> listarPorFecha(Date fecha, Date fecha2);
}

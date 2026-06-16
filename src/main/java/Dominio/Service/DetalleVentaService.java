package Dominio.Service;

import java.util.List;

import Dominio.Modelo.DetalleVenta;

public interface DetalleVentaService {
    //logica para la tabla detalle_venta
    DetalleVenta guardarUnDetalleVenta(DetalleVenta detalleVenta);

    DetalleVenta obtenerUnDetalleVentaPorId(Long id);

    List<DetalleVenta> obtenerTodosLosDetalleVentas();

}

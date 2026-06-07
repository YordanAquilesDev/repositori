package Dominio.Service;

import java.util.List;

import Dominio.Modelo.DetalleVenta;

public interface DetalleVentaService {
    DetalleVenta guardarUnDetalleVenta(DetalleVenta detalleVenta);

    DetalleVenta obtenerUnDetalleVentaPorId(Long id);

    List<DetalleVenta> obtenerTodosLosDetalleVentas();

}

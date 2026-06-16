package Dominio.Service;

import java.util.List;

import Dominio.Modelo.Venta;

public interface VentaService {
    //logica para la tabla venta
    Venta guardarUnaVenta(Venta venta);

    Venta obtenerUnaVentaPorId(Long id);

    List<Venta> obtenerTodasLasVentas();

}

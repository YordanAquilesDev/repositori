package Dominio.repository;

import Dominio.Modelo.Venta;

import java.util.Date;
import java.util.List;

public interface VentaRepository {
    //CRUD de la tabla ventas
    Venta guardar(Venta venta);
    List<Venta> listarVentas();
    List<Venta> listarMejoresVentas();
    List<Venta> listarPorFecha(Date fecha, Date fecha2);

}

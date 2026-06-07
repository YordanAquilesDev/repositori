package Dominio.Service;

import java.sql.Date;
import java.util.List;

import Dominio.Modelo.MovimientoAlmacen;

public interface MovimientoService {

    MovimientoAlmacen guardarUnMovimiento(MovimientoAlmacen movimiento);

    MovimientoAlmacen obtenerUnMovimientoPorId(Long id);

    List<MovimientoAlmacen> obtenerTodosLosMovimientos();

    List<MovimientoAlmacen> obtenerMovimientosPorFecha(Date fecha1, Date fecha2);

}

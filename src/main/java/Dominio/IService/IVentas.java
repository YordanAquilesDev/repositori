package Dominio.IService;

import Dominio.Modelo.Producto;
import Dominio.Modelo.Venta;

import java.util.Date;
import java.util.List;

public interface IVentas {
    List<Venta> VentasEnUnAno(Date fecha);
    List<Venta> VentasEnMes(Date fecha);
    List<Venta> VentasEnUnaSemana(Date fecha);
    List<Venta> productoMasVendido(Producto producto);

}

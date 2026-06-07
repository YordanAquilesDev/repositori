package Dominio.IService;

import Dominio.Modelo.Compra;
import Dominio.Modelo.Producto;

import java.util.Date;
import java.util.List;

public interface ICompras {

    List<Compra> comprasEnUnAno(Date fecha);
    List<Compra> comprasEnUnMez(Date fecha);
    List<Compra> comprasEnUnaSemana(Date fecha);
    List<Compra> productoMasComprado(Producto producto);
}

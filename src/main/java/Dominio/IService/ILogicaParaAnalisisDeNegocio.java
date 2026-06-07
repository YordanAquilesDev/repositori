package Dominio.IService;

import Dominio.Modelo.Animal;
import Dominio.Modelo.Cliente;
import Dominio.Modelo.Pedido;
import Dominio.Modelo.Producto;

import java.util.Date;
import java.util.List;

public interface ILogicaParaAnalisisDeNegocio {
    Animal animalMasCostoso();
    List<Producto>  LosProductosMasVendidosPorFecha(Date fecha,Date fecha2);
    double gananciaNetaPorFecha(Date fecha);
    double gastosNetosPorFecha(Date fecha);
    List<Pedido> listaDePedidosEntregados();
    List<Pedido> listaDePedidosNoEntregados();
    Cliente clienteMasConsumidor();

}

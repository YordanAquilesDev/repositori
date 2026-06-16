package Dominio.Service;

import java.util.List;

import Dominio.Modelo.Producto;

public interface ProductoService {
    //logica para la tabla productos
    Producto guardarUnProducto(Producto producto);

    Producto obtenerUnProductoPorId(Long id);

    List<Producto> obtenerTodosLosProductos();

    List<Producto> obtenerProductosPorCategoria(String categoria);

    List<Producto> obtenerProductosPorAcabar();

}

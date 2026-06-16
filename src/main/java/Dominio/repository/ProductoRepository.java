package Dominio.repository;

import Dominio.Modelo.Producto;

import java.util.List;

public interface ProductoRepository {
    //CRUD de la tabla productos
    Producto buscarPorId(long id);
    List<Producto> listarProductos();
    List<Producto> listaProductosPorAcavar();



}

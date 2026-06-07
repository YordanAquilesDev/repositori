package Dominio.repository;

import Dominio.Modelo.Producto;

import java.util.List;

public interface ProductoRepository {
    Producto buscarPorId(long id);
    List<Producto> listarProductos();
    List<Producto> listaProductosPorAcavar();



}

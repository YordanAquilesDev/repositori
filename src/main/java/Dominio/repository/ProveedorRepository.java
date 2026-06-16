package Dominio.repository;

import Dominio.Modelo.Proveedor;

import java.util.List;

public interface ProveedorRepository {
    //CRUD de la tabla proveedor
    Proveedor guardar(Proveedor proveedor);
    Proveedor buscarPorId(long id);
    Proveedor Actualizar(Proveedor proveedor);
    Proveedor Eliminar(long id);
    List<Proveedor> listarProveedores();

}

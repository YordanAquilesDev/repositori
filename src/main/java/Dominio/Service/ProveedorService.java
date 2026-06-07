package Dominio.Service;

import java.util.List;

import Dominio.Modelo.Proveedor;

public interface ProveedorService {
    Proveedor guardarUnProveedor(Proveedor proveedor);

    Proveedor obtenerUnProveedorPorId(Long id);

    List<Proveedor> obtenerTodosLosProveedores();

    List<Proveedor> obtenerProveedoresActivos();

}

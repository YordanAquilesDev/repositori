package Dominio.IService;

import Dominio.Modelo.Animal;
import Dominio.Modelo.Cliente;
import Dominio.Modelo.Producto;
import Dominio.Modelo.Proveedor;
import org.postgresql.core.ProtocolVersion;

public interface LogicaUtilParaTraerPorId {
    Producto traerProductoPorId(int id);
    Cliente traerClientePorId(int id);
    Proveedor traerProveedorPorId(int id);
    Animal traerAnimalPorId(int id);

}

package Dominio.repository;

import Dominio.Modelo.Cliente;

import java.util.List;

public interface ClienteRepository {
    Cliente guardar(Cliente cliente);
    Cliente traerPorId(Integer id);
    void borrarCliente(Cliente cliente);
    List<Cliente> listarClientes();
}

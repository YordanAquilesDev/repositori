package Dominio.Service;

import java.util.List;

import Dominio.Modelo.Cliente;

public interface ClienteService {

    Cliente guardarCliente(Cliente cliente);

    Cliente obtenerClientePorId(Long id);

    List<Cliente> obtenerTodosLosClientes();

}

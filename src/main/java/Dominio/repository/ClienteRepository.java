package Dominio.repository;

import Dominio.Modelo.Cliente;

import java.util.List;

public interface ClienteRepository {
    int save(Cliente cliente);

    Cliente finById(Integer id);

    int delete(Cliente cliente);

    int update(Cliente cliente);

    List<Cliente> finAll();
}

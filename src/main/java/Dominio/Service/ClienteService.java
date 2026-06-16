package Dominio.Service;

import java.util.List;

import Dominio.Modelo.Cliente;

public interface ClienteService {
    //logica para la tabla cliente
    int save(Cliente cliente);

    Cliente finById(Integer id);

    List<Cliente>  finAll();

}

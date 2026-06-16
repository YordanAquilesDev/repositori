package Aplicacion.ServiceImpl;

import java.util.List;

import Aplicacion.repositoryimpl.ClienteRepositoryImpl;
import Dominio.Modelo.Cliente;
import Dominio.Service.ClienteService;
import Dominio.repository.ClienteRepository;

public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl() {
        this.clienteRepository = new ClienteRepositoryImpl();
    }


    @Override
    public int save(Cliente cliente) {
        if (cliente == null) {
            return -1;
        }
        // Validamos que los campos obligatorios no sean nulos
        if (cliente.getNombre() == null || cliente.getApellido() == null || cliente.getDni() == null ||
                cliente.getCelular() == null || cliente.getDireccion() == null) {
            return -1;
        }

        return clienteRepository.save(cliente); // Retorna el cliente guardado
    }

    @Override
    public Cliente finById(Integer id) {
        if(id<0) return null;
        return clienteRepository.finById(id); // Retorna el cliente encontrado por ID
    }

    @Override
    public List<Cliente> finAll() {
        return clienteRepository.finAll(); // Retorna la lista de clientes
    }

}

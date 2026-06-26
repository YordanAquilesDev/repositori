package Aplicacion.ServiceImpl;

import java.util.List;

import Aplicacion.repositoryimpl.ClienteRepositoryImpl;
import Dominio.Modelo.Cliente;
import Dominio.Service.ServiceGenerico;
import java.util.Optional;

public class ClienteServiceImpl implements ServiceGenerico<Cliente,Integer> {
    private final ClienteRepositoryImpl  clienteRepository;

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
    public Optional<Cliente> finById(Integer id) {
        if(id<0) return null;
        return clienteRepository.finById(id); // Retorna el cliente encontrado por ID
    }

    @Override
    public List<Cliente> finAll() {
        return clienteRepository.finAll(); // Retorna la lista de clientes
    }

    @Override
    public int update(Cliente beans) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int saveAndFinId(Cliente beans) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

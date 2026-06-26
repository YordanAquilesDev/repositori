package Aplicacion.ServiceImpl;

import Aplicacion.repositoryimpl.ClienteRepositoryImpl;
import Dominio.Modelo.Cliente;
import Dominio.Service.ServiceGenerico;

import java.util.List;
import java.util.Optional;

public class ClienteServiceImpl implements ServiceGenerico<Cliente, Integer> {

    private final ClienteRepositoryImpl clienteRepository;

    public ClienteServiceImpl() {
        this.clienteRepository = new ClienteRepositoryImpl();
    }

    @Override
    public int save(Cliente beans) {
        if (beans == null || beans.getNombre() == null || beans.getApellido() == null
                || beans.getDni() == null || beans.getCelular() == null || beans.getDireccion() == null) {
            return -1;
        }
        return clienteRepository.save(beans);
    }

    @Override
    public int update(Cliente beans) {
        if (beans == null || beans.getIdCliente() <= 0) return -1;
        return clienteRepository.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id < 0) return -1;
        return clienteRepository.delete(id);
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        if (id == null || id < 0) return Optional.empty();
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public int saveAndFinId(Cliente beans) {
        if (beans == null || beans.getNombre() == null || beans.getApellido() == null) {
            return -1;
        }
        return clienteRepository.saveAndFinId(beans);
    }

    public Cliente finById(Integer id) {
        return findById(id).orElse(null);
    }

    public List<Cliente> finAll() {
        return findAll();
    }
}

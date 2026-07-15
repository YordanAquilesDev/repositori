package Aplicacion.Service;

import Dominio.Modelo.Cliente;
import Aplicacion.DAO.ClienteDAO;
import Dominio.repository.ICRUD;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author user
 */
public class ClienteService implements ICRUD<Cliente, Integer> {

    private final ClienteDAO clienteDAO;

    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }

    @Override
    public int save(Cliente beans) {
        if (beans == null) {
            return -1;
        }
        return clienteDAO.save(beans);
    }

    @Override
    public int update(Cliente beans) {
        if (beans == null || beans.getIdCliente() <= 0) return -1;
        return clienteDAO.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id < 0) return -1;
        return clienteDAO.delete(id);
    }

    @Override
    public Optional<Cliente> findById(Integer integer) {
        return clienteDAO.findById(integer);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteDAO.findAll();
    }

    public Optional<Cliente> findByUsuarioId(int idUsuario) {
        return clienteDAO.findByUsuarioId(idUsuario);
    }

    @Override
    public int saveAndFindId(Cliente beans) {
        if (beans == null) return -1;
        return clienteDAO.saveAndFindId(beans);
    }
}

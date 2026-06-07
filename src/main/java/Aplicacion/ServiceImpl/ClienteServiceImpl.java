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
    public Cliente guardarCliente(Cliente cliente) {
        /**
         *     El metodo guardarCliente recive como parametro
         *         un Objeto de la clase Cliente
         *         Retorna un Objeto Cliente
         **/
        if (cliente == null) {
            return null;
        }
        // Validamos que los campos obligatorios no sean nulos
        if (cliente.getNombre() == null || cliente.getApellido() == null || cliente.getDni() == null ||
                cliente.getCelular() == null || cliente.getDireccion() == null) {
            return null; // O lanzar una excepción según tu diseño
        }

        return clienteRepository.guardar(cliente); // Retorna el cliente guardado
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        /**
         * El metodo obtenerClientePorId
         * @param Long
         * @return Cliente
         *
         */

        return clienteRepository.traerPorId(id.intValue()); // Retorna el cliente encontrado por ID
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        /**
         * El metodo obtenerTodosLosClientes
         * @return List<Cliente>
         *
         */
        return clienteRepository.listarClientes(); // Retorna la lista de clientes
    }

}

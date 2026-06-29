package Aplicacion.ServiceImpl;

import Aplicacion.repositoryimpl.ClienteRepositoryImpl;
import Dominio.Modelo.Cliente;
import Dominio.Service.ServiceGenerico;

import java.util.List;
import java.util.Optional;

public class ClienteServiceImpl implements ServiceGenerico<Cliente, Integer> {
   //funciona exelente
   //Repositorio que permite realizar operaciones CRUD sobre la tabla Cliente.
    private final ClienteRepositoryImpl clienteRepository;
    /**
     * Constructor de la clase.
     * Inicializa el repositorio que se utilizará para acceder a la base de datos.
     */
    public ClienteServiceImpl() {
        this.clienteRepository = new ClienteRepositoryImpl();
    }
    
    /**
         * Guarda un nuevo cliente en la base de datos.
         *
         * @param beans Objeto Cliente que contiene la información a registrar.
         * @return
         *  - Devuelve 1 (o el valor correspondiente) si el registro fue exitoso.
         *  - Devuelve -1 si los datos son inválidos.
         */
    @Override
    public int save(Cliente beans) {
        // Verifica que el objeto Cliente y sus datos obligatorios no sean nulos.
        if (beans == null || beans.getNombre() == null || beans.getApellido() == null
                || beans.getDni() == null || beans.getCelular() == null || beans.getDireccion() == null) {
            return -1;
        }
        // Envía el objeto al repositorio para almacenarlo en la base de datos.
        return clienteRepository.save(beans);
    }

    /**
     * Actualiza la información de un cliente existente.
     * @param beans Cliente con los nuevos datos.
     * @return
     *  - Retorna el resultado de la actualización.
     *  - Devuelve -1 si el objeto o el ID son inválidos.
     */
    @Override
    public int update(Cliente beans) {
         // Verifica que exista un objeto Cliente y que el ID sea válido.
        if (beans == null || beans.getIdCliente() <= 0) return -1;
        // Actualiza la información del cliente en la base de datos.
        return clienteRepository.update(beans);
    }

     /**
     * Elimina un cliente de la base de datos mediante su ID.
     *
     * @param id Identificador único del cliente.
     * @return
     *  - Retorna el resultado de la eliminación.
     *  - Devuelve -1 si el ID es inválido.
     */
    @Override
    public int delete(Integer id) {
        // Valida que el ID exista y sea un número positivo.
        if (id == null || id < 0) return -1;
        // Elimina el cliente utilizando el repositorio.
        return clienteRepository.delete(id);
    }

     /**
     * Busca un cliente utilizando su ID.
     * @param id Identificador del cliente.
     * @return
     *  - Un Optional que puede contener el cliente encontrado.
     *  - Optional.empty() si el ID es inválido o no existe.
     */
    @Override
    public Optional<Cliente> findById(Integer id) {
        // Verifica que el ID recibido sea válido.
        if (id == null || id < 0) return Optional.empty();
         // Consulta el cliente en la base de datos.
        return clienteRepository.findById(id);
    }

    /**
     * Obtiene todos los clientes registrados.
     * @return Lista con todos los clientes almacenados.
     */
    @Override
    public List<Cliente> findAll() {
        // Recupera todos los registros de clientes.
        return clienteRepository.findAll();
    }

    @Override
    /**
     * Guarda un cliente y devuelve el ID generado por la base de datos.
     * @param beans Objeto Cliente que será registrado.
     * @return
     *  - ID generado o resultado del repositorio.
     *  - Devuelve -1 si los datos obligatorios son inválidos.
     */
    public int saveAndFinId(Cliente beans) {
        // Verifica que el cliente tenga los datos mínimos necesarios.
        if (beans == null || beans.getNombre() == null || beans.getApellido() == null) {
            return -1;
        }
        // Guarda el cliente y obtiene el ID generado.
        return clienteRepository.saveAndFinId(beans);
    }
    
    /**
         * Busca un cliente por su ID.
         * A diferencia de findById(), este método devuelve directamente un objeto Cliente.
         * @param id Identificador del cliente.
         * @return Cliente encontrado o null si no existe.
         */
    public Cliente finById(Integer id) {
        // Convierte el Optional en un objeto Cliente.
        // Si no encuentra el cliente, devuelve null.
        return findById(id).orElse(null);
    }

    /**
     * Devuelve la lista completa de clientes.
     * Es un método auxiliar que llama directamente al repositorio.
     * @return Lista de todos los clientes.
     */
    public List<Cliente> finAll() {
        
        // Obtiene todos los clientes registrados en la base de datos.
        return clienteRepository.findAll();
    }
}

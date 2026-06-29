package Aplicacion.ServiceImpl;

import Aplicacion.repositoryimpl.DetallePedidoRepositoryImpl;
import Dominio.Modelo.DetallePedido;
import Dominio.repository.CrudGenerico;

import java.util.List;
import java.util.Optional;

/**
 * Clase encargada de gestionar la lógica de negocio de los detalles de pedido.
 * Realiza validaciones antes de acceder al repositorio encargado de la base de datos.
 */
public class DetallePedidoServiceImpl implements CrudGenerico<DetallePedido, Integer> {

    private final DetallePedidoRepositoryImpl detallePedidoRepository;

    public DetallePedidoServiceImpl() {
        this.detallePedidoRepository = new DetallePedidoRepositoryImpl();
    }

    /**
     * Guarda un nuevo detalle de pedido en la base de datos.
     * @param beans Objeto DetallePedido que contiene la información a registrar.
     * @return Devuelve el resultado del registro o -1 si el objeto es nulo.
     */
    @Override
    public int save(DetallePedido beans) {
        // Verifica que el objeto DetallePedido exista.
        if (beans == null) return -1;
        // Guarda el detalle de pedido en la base de datos.
        return detallePedidoRepository.save(beans);
    }

     /**
     * Actualiza la información de un detalle de pedido existente.
     * @param beans Objeto DetallePedido con la nueva información.
     * @return Resultado de la actualización o -1 si los datos son inválidos.
     */
    @Override
    public int update(DetallePedido beans) {
         // Verifica que el objeto exista y tenga un ID válido.
        if (beans == null || beans.getIdDetalle() <= 0) return -1;
        // Actualiza el detalle de pedido en la base de datos.
        return detallePedidoRepository.update(beans);
    }

    /**
     * Elimina un detalle de pedido utilizando su ID.
     *
     * @param id Identificador del detalle de pedido.
     * @return Resultado de la eliminación o -1 si el ID es inválido.
     */
    @Override
    public int delete(Integer id) {
        // Verifica que el ID recibido sea válido.
        if (id == null || id < 0) return -1;
        // Elimina el detalle de pedido del repositorio.
        return detallePedidoRepository.delete(id);
    }

     /**
     * Busca un detalle de pedido mediante su ID
     * @param id Identificador del detalle de pedido.
     * @return Un Optional que contiene el detalle encontrado o vacío si no existe.
     */
    @Override
    public Optional<DetallePedido> findById(Integer id) {
        // Verifica que el ID sea válido.
        if (id == null || id < 0) return Optional.empty();
        // Busca el detalle de pedido en la base de datos.
        return detallePedidoRepository.findById(id);
    }

     /**
     * Obtiene todos los detalles de pedido registrados.
     * @return Lista con todos los detalles de pedido almacenados.
     */
    @Override
    public List<DetallePedido> findAll() {
        return detallePedidoRepository.findAll();
    }

     /**
     * Guarda un detalle de pedido y devuelve el ID generado automáticamente.
     * @param beans Objeto DetallePedido que será registrado.
     * @return ID generado o -1 si el objeto es nulo.
     */
    @Override
    public int saveAndFindId(DetallePedido beans) {
        // Verifica que el objeto exista.
        if (beans == null) return -1;
        // Guarda el detalle de pedido y devuelve el ID generado.
        return detallePedidoRepository.saveAndFindId(beans);
    }
}

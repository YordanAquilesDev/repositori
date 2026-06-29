package Aplicacion.ServiceImpl;

import Aplicacion.repositoryimpl.DetalleCompraRepositoryImpl;
import Dominio.Modelo.DetalleCompra;
import Dominio.repository.CrudGenerico;

import java.util.List;
import java.util.Optional;

/**
 * Clase encargada de gestionar la lógica de negocio de los detalles de compra.
 * Realiza validaciones antes de acceder al repositorio encargado de la base de datos.
 */
public class DetalleCompraServiceImpl implements CrudGenerico<DetalleCompra, Integer> {

    private final DetalleCompraRepositoryImpl detalleCompraRepository;

    public DetalleCompraServiceImpl() {
        this.detalleCompraRepository = new DetalleCompraRepositoryImpl();
    }

    /**
     * Guarda un nuevo detalle de compra en la base de datos.
     * @param beans Objeto DetalleCompra que contiene la información a registrar.
     * @return Devuelve el resultado del registro o -1 si el objeto es nulo.
     */
    @Override
    public int save(DetalleCompra beans) {
        // Verifica que el objeto DetalleCompra exista.
        if (beans == null) return -1;
        // Guarda el detalle de compra en la base de datos.
        return detalleCompraRepository.save(beans);
    }

    /**
     * Actualiza la información de un detalle de compra existente.
     * @param beans Objeto DetalleCompra con la nueva información.
     * @return Resultado de la actualización o -1 si los datos son inválidos.
     */
    @Override
    public int update(DetalleCompra beans) {
        // Verifica que el objeto exista y tenga un ID válido
        if (beans == null || beans.getIdDetalle() <= 0) return -1;
        // Actualiza el detalle de compra en la base de datos.
        return detalleCompraRepository.update(beans);
    }

     /**
     * Elimina un detalle de compra utilizando su ID.
     * @param id Identificador del detalle de compra.
     * @return Resultado de la eliminación o -1 si el ID es inválido.
     */
    @Override
    public int delete(Integer id) {
        // Verifica que el ID recibido sea válido.
        if (id == null || id < 0) return -1;
        // Elimina el detalle de compra del repositorio.
        return detalleCompraRepository.delete(id);
    }

     /**
     * Busca un detalle de compra mediante su ID.
     * @param id Identificador del detalle de compra.
     * @return Un Optional que contiene el detalle encontrado o vacío si no existe.
     */
    @Override
    public Optional<DetalleCompra> findById(Integer id) {
        // Verifica que el ID sea válido.
        if (id == null || id < 0) return Optional.empty();
        // Busca el detalle de compra en la base de datos.
        return detalleCompraRepository.findById(id);
    }

    /**
     * Obtiene todos los detalles de compra registrados.
     * @return Lista con todos los detalles de compra almacenados.
     */
    @Override
    public List<DetalleCompra> findAll() {
        // Recupera todos los detalles de compra desde la base de datos.
        return detalleCompraRepository.findAll();
    }

     /**
     * Guarda un detalle de compra y devuelve el ID generado automáticamente.
     * @param beans Objeto DetalleCompra que será registrado.
     * @return ID generado o -1 si el objeto es nulo.
     */
    @Override
    public int saveAndFindId(DetalleCompra beans) {
         // Verifica que el objeto exista.
        if (beans == null) return -1;
        // Guarda el detalle de compra y devuelve el ID generado.
        return detalleCompraRepository.saveAndFindId(beans);
    }
}
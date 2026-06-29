package Aplicacion.ServiceImpl;

import Aplicacion.repositoryimpl.ProductoRepositoryImpl;
import Dominio.Modelo.Producto;
import Dominio.Service.ServiceGenerico;

import java.util.List;
import java.util.Optional;

/**
 * Clase encargada de gestionar la lógica de negocio relacionada con los productos.
 * Realiza validaciones antes de acceder al repositorio encargado de la base de datos.
 */
public class ProductoServiceImpl implements ServiceGenerico<Producto, Integer> {

    private final ProductoRepositoryImpl productoRepository;

    public ProductoServiceImpl() {
        this.productoRepository = new ProductoRepositoryImpl();
    }

    /**
     * Guarda un nuevo producto en la base de datos.
     * @param beans Objeto Producto que contiene la información a registrar.
     * @return Devuelve el resultado del registro o -1 si el objeto es nulo.
     */
    @Override
    public int save(Producto beans) {
         // Verifica que el objeto Producto exista.
        if (beans == null) return -1;
        //guarda el producto en la base de datos
        return productoRepository.save(beans);
    }

    /**
     * Actualiza la información de un producto existente.
     * @param beans Objeto Producto con la nueva información.
     * @return Resultado de la actualización o -1 si los datos son inválidos.
     */
    @Override
    public int update(Producto beans) {
        // Verifica que el objeto exista y tenga un ID válido.
        if (beans == null || beans.getIdProducto() <= 0) return -1;
        // Actualiza el producto en la base de datos.
        return productoRepository.update(beans);
    }

     /**
     * Elimina un producto utilizando su ID.
     * @param id Identificador del producto.
     * @return Resultado de la eliminación o -1 si el ID es inválido.
     */
    @Override
    public int delete(Integer id) {
        // Verifica que el ID recibido sea válido.
        if (id == null || id < 0) return -1;
        return productoRepository.delete(id);
    }

    /**
     * Busca un producto mediante su ID.
     * @param id Identificador del producto.
     * @return Un Optional que contiene el producto encontrado o vacío si no existe.
     */
    @Override
    public Optional<Producto> findById(Integer id) {
        // Verifica que el ID sea válido.
        if (id == null || id < 0) return Optional.empty();
        // Busca el producto en la base de datos.
        return productoRepository.findById(id);
    }

    /**
     * Obtiene todos los productos registrados
     * @return Lista con todos los productos almacenados.
     */
    @Override
    public List<Producto> findAll() {
        // Recupera todos los productos desde la base de datos.
        return productoRepository.findAll();
    }

      /**
     * Guarda un producto y devuelve el ID generado automáticamente.
     * @param beans Objeto Producto que será registrado.
     * @return ID generado o -1 si el objeto es nulo.
     */
    @Override
    public int saveAndFinId(Producto beans) {
        // Verifica que el objeto exista.
        if (beans == null) return -1;
        // Guarda el producto y devuelve el ID generado.
        return productoRepository.saveAndFinId(beans);
    }
}

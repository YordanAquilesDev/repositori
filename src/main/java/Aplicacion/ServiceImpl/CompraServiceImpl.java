package Aplicacion.ServiceImpl;

import Aplicacion.repositoryimpl.CompraRepositoryImpl;
import Dominio.Modelo.Compra;
import Dominio.Modelo.DetalleCompra;
import Dominio.repository.CrudGenerico;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * Clase encargada de gestionar la lógica de negocio de las compras.
 * Realiza validaciones antes de enviar la información al repositorio.
 */
public class CompraServiceImpl implements CrudGenerico<Compra, Integer> {

    // Repositorio encargado de realizar las operaciones CRUD de Compra en la base de datos.
    private final CompraRepositoryImpl compraRepository;
    // Servicio utilizado para registrar los detalles de cada compra.
    private final DetalleCompraServiceImpl detalleCompraService;

     /**
     * Constructor de la clase.
     * Inicializa el repositorio y el servicio de detalles de compra.
     */
    public CompraServiceImpl() {
        this.compraRepository = new CompraRepositoryImpl();
        this.detalleCompraService = new DetalleCompraServiceImpl();
    }

      /**
     * Registra una nueva compra junto con sus detalles.
     * @param beans Objeto Compra que contiene toda la información.
     * @return
     * - 1 si la compra y sus detalles fueron registrados correctamente.
     * - 0 si la compra se registró pero hubo errores al guardar los detalles.
     * - -1 si los datos son inválidos o no se pudo registrar la compra.
     */
    @Override
    public int save(Compra beans) {
         // Verifica que el objeto Compra y el proveedor existan.
        if (beans == null || beans.getProveedor() == null) return -1;

        List<DetalleCompra> detalles = beans.getDetalles();
        // Verifica que exista al menos un detalle de compra.
        if (detalles == null || detalles.isEmpty()) {
            return -1;
        }
        
        // Variable que almacena el total de la compra calculando la suma de todos los subtotales.
        double total = detalles.stream()
                .mapToDouble(DetalleCompra::getSubtotal)
                .sum();
        // Asigna el total calculado al objeto Compra.
        beans.setTotal(total);
        
        // Guarda la compra y obtiene el ID generado automáticamente.   
        int idCompra = saveAndFindId(beans);
        
         // Si la compra fue registrada correctamente.
        if (idCompra > 0) {
             // Variable final utilizada para asignar el ID de la compra a cada detalle.
            final int id = idCompra;
             // Asigna el ID generado a todos los detalles de la compra
            detalles.forEach(d -> d.getCompra().setIdCompra(id));
             // Variable que almacena la cantidad total de filas afectadas al guardar los detalles.
            int filas = detalles.stream()
                    .mapToInt(detalleCompraService::save)
                    .sum();
             // Devuelve 1 si todos los detalles fueron registrados correctamente, caso contrario devuelve 0.
            return filas > 0 ? 1 : 0;
        }
        // Retorna -1 si la compra no pudo registrarse.
        return -1;
    }

     /**
     * Actualiza la información de una compra existente.
     * @param beans Compra con los nuevos datos.
     * @return Resultado de la actualización o -1 si los datos son inválidos.
     */
    @Override
    public int update(Compra beans) {
         // Verifica que el objeto Compra exista y tenga un ID válido.
        if (beans == null || beans.getIdCompra() <= 0) return -1;
        return compraRepository.update(beans);
    }

    /**
     * Elimina una compra utilizando su identificador.
     * @param id ID de la compra.
     * @return Resultado de la eliminación o -1 si el ID es inválido.
     */
    @Override
    public int delete(Integer id) {
         // Verifica que el ID recibido sea válido.
        if (id == null || id < 0) return -1;
        
        // Elimina la compra del repositorio.
        return compraRepository.delete(id);
    }

     /**
     * Busca una compra utilizando su ID.
     *
     * @param id Identificador de la compra.
     * @return Un Optional que contiene la compra si fue encontrada o vacío si no existe.
     */
    @Override
    public Optional<Compra> findById(Integer id) {
        // Verifica que el ID recibido sea válido.
        if (id == null || id < 0) return Optional.empty();
        // Busca la compra en la base de datos.
        return compraRepository.findById(id);
    }

     /**
     * Obtiene todas las compras registradas.
     * @return Lista con todas las compras almacenadas.
     */
    @Override
    public List<Compra> findAll() {
        // Recupera todas las compras desde la base de datos.
        return compraRepository.findAll();
    }

     /**
     * Guarda una compra y devuelve el ID generado automáticamente.
     *
     * @param beans Compra que será registrada.
     * @return ID generado o -1 si los datos son inválidos.
     */
    @Override
    public int saveAndFindId(Compra beans) {
         // Verifica que la compra y el proveedor existan.
        if (beans == null || beans.getProveedor() == null) return -1;
        // Guarda la compra y devuelve el ID generado.
        return compraRepository.saveAndFindId(beans);
    }

     /**
     * Busca una compra utilizando un ID de tipo Long.
     * @param id Identificador de la compra.
     * @return Compra encontrada o null si no existe.
     */
    public Compra obtenerCompraPorId(Long id) {
        // Convierte el ID de Long a Integer y realiza la búsqueda.
        return findById(id.intValue()).orElse(null);
    }

     /**
     * Obtiene todas las compras registradas.
     * @return Lista completa de compras.
     */
    public List<Compra> obtenerTodasLasCompras() {
        return findAll();
    }

     /**
     * Busca las compras realizadas entre dos fechas.
     * @param fecha Fecha inicial.
     * @param fecha2 Fecha final.
     * @return Lista de compras comprendidas entre ambas fechas.
     */
    public List<Compra> obtenerComprasPorFecha(Date fecha, Date fecha2) {
         // Consulta al repositorio las compras registradas en el rango de fechas indicado.
        return compraRepository.listarComprasPorFecha(fecha, fecha2);
    }

    /**
     * Obtiene las compras con los montos más altos.
     * @return Lista de las compras con mayor importe.
     */
    public List<Compra> listarComprasMasAltos() {
          // Recupera desde el repositorio las compras de mayor valor.
        return compraRepository.listarComprasMasAltos();
    }
}

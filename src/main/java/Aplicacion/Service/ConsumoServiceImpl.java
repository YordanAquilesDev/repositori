package Aplicacion.Service;

import Aplicacion.repositoryimpl.ConsumoLoteRepositoryImpl;
import Dominio.Modelo.Animal;
import Dominio.Modelo.ConsumoLote;
import Dominio.repository.CrudGenerico;

import java.util.List;
import java.util.Optional;

public class ConsumoServiceImpl implements CrudGenerico<ConsumoLote, Integer> {

      // Repositorio encargado de realizar las operaciones CRUD del consumo de lotes.
    private final ConsumoLoteRepositoryImpl consumoLoteRepository;

    /**
     * Constructor de la clase.
     * Inicializa el repositorio que permitirá acceder a la base de datos.
     */
    public ConsumoServiceImpl() {
        this.consumoLoteRepository = new ConsumoLoteRepositoryImpl();
    }

    /**
     * Guarda un nuevo registro de consumo de lote.
     * @param beans Objeto ConsumoLote que contiene la información a registrar.
     * @return
     * - Devuelve el resultado del repositorio si el registro fue exitoso.
     * - Retorna -1 si el objeto recibido es nulo.
     */
    @Override
    public int save(ConsumoLote beans) {
        if (beans == null) return -1;
        return consumoLoteRepository.save(beans);
    }

     /**
     * Actualiza la información de un consumo de lote existente.
     * @param beans Objeto ConsumoLote con la nueva información.
     * @return Resultado de la actualización o -1 si los datos son inválidos.
     */
    @Override
    public int update(ConsumoLote beans) {
        // Verifica que el objeto exista y tenga un ID válido.
        if (beans == null || beans.getIdConsumo() <= 0) return -1;
         // Actualiza el registro en la base de datos.
        return consumoLoteRepository.update(beans);
    }

    /**
     * Elimina un registro de consumo utilizando su ID.
     * @param id Identificador del consumo.
     * @return Resultado de la eliminación o -1 si el ID es inválido.
     */
    @Override
    public int delete(Integer id) {
        if (id == null || id < 0) return -1;
        return consumoLoteRepository.delete(id);
    }

     /**
     * Busca un consumo de lote utilizando su ID.
     * @param id Identificador del consumo.
     * @return Un Optional que contiene el consumo encontrado o vacío si no existe.
     */
    @Override
    public Optional<ConsumoLote> findById(Integer id) {
        if (id == null || id < 0) return Optional.empty();
        return consumoLoteRepository.findById(id);
    }

     /**
     * Obtiene todos los registros de consumo de lotes.
     * @return Lista con todos los consumos registrados.
     */
    @Override
    public List<ConsumoLote> findAll() {
        //recupera todos los registrs almacenados
        return consumoLoteRepository.findAll();
    }

    /**
     * Guarda un consumo de lote y devuelve el ID generado.
     * @param beans Objeto ConsumoLote que será registrado.
     * @return ID generado o -1 si el objeto es inválido.
     */
    @Override
    public int saveAndFindId(ConsumoLote beans) {
        //verifica que el objecto exista
        if (beans == null) return -1;
        // Guarda el registro y devuelve el ID generado.
        return consumoLoteRepository.saveAndFindId(beans);
    }

     /**
     * Método auxiliar que registra un consumo de lote.
     * @param consumoLote Objeto que contiene la información del consumo.
     * @return Resultado del registro.
     */
    public int guardarConsumoLote(ConsumoLote consumoLote) {
        // Llama al método save para registrar el consumo.
        return save(consumoLote);
    }

    /**
     * Obtiene un consumo de lote utilizando un ID de tipo Long.
     * @param id Identificador del consumo.
     * @return Objeto ConsumoLote encontrado o null si no existe.
     */
    public ConsumoLote obtenerConsumoPorId(Long id) {
        return consumoLoteRepository.obtenerConsumoPorId(id);
    }

     /**
     * Obtiene todos los consumos registrados para un animal específico.
     * @param animal Animal del cual se desean consultar los consumos.
     * @return Lista de consumos asociados al animal.
     */
    public List<ConsumoLote> obtenerConsumoLotePorAnimal(Animal animal) {
         // Verifica que el objeto Animal exista y tenga un ID válido.
        if (animal == null || animal.getIdAnimal() <= 0) {
            throw new IllegalArgumentException("El animal no es valido");
        }
        // Consulta en el repositorio todos los consumos asociados al animal.
        return consumoLoteRepository.obtenerConsumoLotePorAnimal(animal);
    }
}

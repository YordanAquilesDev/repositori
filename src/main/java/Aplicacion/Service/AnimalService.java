package Aplicacion.Service;

import Aplicacion.DAO.AnimalRepositoryImpl;
import Dominio.Modelo.Animal;
import Dominio.repository.CrudGenerico;

import java.util.List;
import java.util.Optional;

/**
 * Clase encargada de gestionar la lógica de negocio relacionada con los animales.
 * Realiza validaciones antes de acceder al repositorio encargado de la base de datos.
 */
public class AnimalService implements CrudGenerico<Animal, Integer> {
//funcioan ok sin ningun problemas
    private final AnimalRepositoryImpl animalRepository;
    
     /**
     * Constructor de la clase.
     * Inicializa el repositorio de animales.
     */
    public AnimalService() {
        this.animalRepository = new AnimalRepositoryImpl();
    }

    /**
     * Guarda un nuevo animal en la base de datos.
     * @param beans Objeto Animal que contiene la información a registrar.
     * @return Devuelve el resultado del registro o -1 si los datos son inválidos.
     */
    @Override
    public int save(Animal beans) {
        // Verifica que el objeto Animal exista y que los campos obligatorios no sean nulos.
        if (beans == null || beans.getRaza() == null || beans.getEspecie() == null) {
            return -1;
        }
        // Guarda el animal en la base de datos.
        return animalRepository.save(beans);
    }

    /**
     * Actualiza la información de un animal existente.
     * @param beans Objeto Animal con la nueva información.
     * @return Resultado de la actualización o -1 si los datos son inválidos.
     */
    @Override
    public int update(Animal beans) {
         // Verifica que el objeto exista y tenga un ID válido.
        if (beans == null || beans.getIdAnimal() <= 0) return -1;
        // Actualiza el registro del animal.
        return animalRepository.update(beans);
    }

     /**
     * Elimina un animal utilizando su ID.
     * @param id Identificador del animal.
     * @return Resultado de la eliminación o -1 si el ID es inválido.
     */
    @Override
    public int delete(Integer id) {
        // Verifica que el ID recibido sea válido.
        if (id == null || id < 0) return -1;
        // Elimina el animal del repositorio.
        return animalRepository.delete(id);
    }

    /**
     * Busca un animal mediante su ID.
     * @param id Identificador del animal.
     * @return Un Optional que contiene el animal encontrado o vacío si no existe.
     */
    @Override
    public Optional<Animal> findById(Integer id) {
        // Verifica que el ID recibido sea válido.
        if (id == null || id < 0) return Optional.empty();
        // Busca el animal en la base de datos.
        return animalRepository.findById(id);
    }

     /**
     * Obtiene todos los animales registrados
     * @return Lista con todos los animales almacenados.
     */
    @Override
    public List<Animal> findAll() {
          // Recupera todos los animales desde la base de datos.
        return animalRepository.findAll();
    }

    /**
     * Guarda un animal y devuelve el ID generado automáticamente.
     * @param beans Objeto Animal que será registrado.
     * @return ID generado o -1 si los datos son inválidos.
     */
    @Override
    public int saveAndFindId(Animal beans) {
        // Verifica que el objeto exista y tenga los datos obligatorios
        if (beans == null || beans.getRaza() == null || beans.getEspecie() == null) {
            return -1;
        }
        //guarda el animal y regresa el id generado
        return animalRepository.saveAndFindId(beans);
    }

    /**
     * Obtiene la lista de animales destinada al módulo de consumo.
     * @return Lista de animales obtenida desde el repositorio.
     */
    public List<Animal> findAllConsumer() {
        // Recupera los animales mediante una consulta específica del repositorio.
        return animalRepository.findAllConsumer();
    }
}

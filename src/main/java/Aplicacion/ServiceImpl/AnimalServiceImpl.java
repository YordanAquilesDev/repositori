package Aplicacion.ServiceImpl;

import java.util.List;

import Aplicacion.repositoryimpl.AnimalRepositoryImpl;
import Dominio.Modelo.Animal;
import Dominio.Service.AnimalService;

public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalServiceImpl() {
        this.animalRepository = new AnimalRepositoryImpl();
    }

    // Funciona Correctamente
    //Cumple con su Objetivo de guardar en la DB
    //Faltaria aser pruevas más específico para decubrir
    //algún error
    public int save(Animal animal) {
        /*
        El metodo guardarAnimal resive
        objeto de la Clase Animal
        para guardarlo en la Base de Datos
         */
        if (animal == null) {
            return -1;
        }

        if (animal.getRaza() == null || animal.getEspecie() == null) {
            return -1;

        } else {
            return animalRepository.save(animal);

        }
    }


    //Funciona Correctamente
    //Cumple con su Objetivo de guardar en la DB
    //Faltaria aser pruevas más específico para decubrir
    //algún error
    public Animal finById(Integer id) {
        if(id<0){
            return null;
        }
        /*
        El metodo obtenerAnimalPorId recive como parametro un
        Long que será usado en el metodo
        traerAnimalPorId este retorna un Objeto Animal de la
        clase Animal
         */
        return animalRepository.finById(id);
    }

    // Funciona Correctamente
    //Cumple con su Objetivo de guardar en la DB
    //Faltaria aser pruevas más específico para decubrir
    //algún error
    public List<Animal> finAll() {
        /*
        El metodo obtenerTodosLosAnimales
        trae todas las filas de la tabla animal
        de la base de datos granjadb
         */
        return animalRepository.finAll();
    }


    @Override
    public List<Animal> finAllConsumer() {
        return animalRepository.finAllConsumer();
    }

}

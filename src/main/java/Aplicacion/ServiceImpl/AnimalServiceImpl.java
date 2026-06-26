package Aplicacion.ServiceImpl;

import java.util.List;

import Aplicacion.repositoryimpl.AnimalRepositoryImpl;
import Dominio.Modelo.Animal;
import Dominio.Service.ServiceGenerico;
import java.util.Optional;

public class AnimalServiceImpl implements ServiceGenerico<Animal,Integer>{
    private final AnimalRepositoryImpl animalRepository;

    public AnimalServiceImpl() {
        this.animalRepository = new AnimalRepositoryImpl();
    }

    // Funciona Correctamente
    //Cumple con su Objetivo de guardar en la DB
    //Faltaria aser pruevas más específico para decubrir
    //algún error
    @Override
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
    @Override
    public Optional<Animal> finById(Integer id) {
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
    @Override
    public List<Animal> finAll() {
        /*
        El metodo obtenerTodosLosAnimales
        trae todas las filas de la tabla animal
        de la base de datos granjadb
         */
        return animalRepository.finAll();
    }


    public List<Animal> finAllConsumer() {
        return animalRepository.finAllConsumer();
    }

    @Override
    public int update(Animal beans) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int saveAndFinId(Animal beans) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

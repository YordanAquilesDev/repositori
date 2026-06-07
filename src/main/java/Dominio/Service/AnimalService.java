package Dominio.Service;

import java.util.List;

import Dominio.Modelo.Animal;

public interface AnimalService {

    Animal guardarAnimal(Animal animal);

    Animal obtenerAnimalPorId(Long id);

    List<Animal> obtenerTodosLosAnimales();

}

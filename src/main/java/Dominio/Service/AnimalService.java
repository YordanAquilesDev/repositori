package Dominio.Service;

import java.util.List;

import Dominio.Modelo.Animal;

public interface AnimalService {
    //logica para la table animal

    int save(Animal animal);

    Animal finById(Integer id);

    List<Animal> finAll();

    List<Animal> finAllConsumer();

}

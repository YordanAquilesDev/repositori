package Dominio.repository;

import Dominio.Modelo.Animal;

import java.util.List;

public interface AnimalRepository {
    int save(Animal animal);

    Animal finById(Integer id);

    List<Animal> finAllConsumer();

    List<Animal> finAll();

    int update(Animal animal);

    int delete(Integer id);

}

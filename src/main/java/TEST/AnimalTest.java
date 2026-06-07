package TEST;

import java.util.List;

import Aplicacion.repositoryimpl.AnimalRepositoryImpl;
import Dominio.Modelo.Animal;
import Dominio.repository.AnimalRepository;

public class AnimalTest {

    static AnimalRepository testAnimal = new AnimalRepositoryImpl();

    public static Animal guardarAnimal() {
        Animal animal = new Animal(2, "Especie", "Raza");

        return testAnimal.guardarAnimal(animal);

    }

    public static Animal traerPorId(int id) {
        return testAnimal.traerAnimalPorId(id);
    }

    public static List<Animal> traerPorConsumo() {
        return testAnimal.traerAnimalesPorConsumo();
    }

}

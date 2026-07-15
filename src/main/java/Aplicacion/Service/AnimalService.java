package Aplicacion.Service;

import Aplicacion.DAO.AnimalDAO;
import Dominio.Modelo.Animal;
import Dominio.repository.ICRUD;

import java.util.List;
import java.util.Optional;

/**
 * Clase encargada de gestionar la lógica de negocio relacionada con los animales.
 * Realiza validaciones antes de acceder al repositorio encargado de la base de datos.
 */
public class AnimalService implements ICRUD<Animal, Integer> {

    private final AnimalDAO animalDAO;

    public AnimalService() {
        this.animalDAO = new AnimalDAO();
    }

    @Override
    public int save(Animal beans) {
        return animalDAO.save(beans);
    }

    @Override
    public int update(Animal beans) {
        if (beans == null || beans.getIdAnimal() <= 0) return -1;
        return animalDAO.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id < 0) return -1;
        return animalDAO.delete(id);
    }

    @Override
    public Optional<Animal> findById(Integer id) {
        if (id == null || id < 0) return Optional.empty();
        return animalDAO.findById(id);
    }

    @Override
    public List<Animal> findAll() {
        return animalDAO.findAll();
    }

    @Override
    public int saveAndFindId(Animal beans) {
        return animalDAO.saveAndFindId(beans);
    }

    public int descontarStock(int idAnimal, int cantidad) {
        if (idAnimal <= 0 || cantidad <= 0) {
            throw new IllegalArgumentException("Animal o cantidad invalida.");
        }

        Animal animal = validarDisponibilidad(idAnimal, cantidad);
        int nuevoStock = animal.getStock() - cantidad;
        animal.setStock(nuevoStock);
        animal.setEstado(nuevoStock == 0 ? "Vendido" : "Disponible");

        return update(animal);
    }

    public Animal validarDisponibilidad(int idAnimal, int cantidad) {
        if (idAnimal <= 0 || cantidad <= 0) {
            throw new IllegalArgumentException("Animal o cantidad invalida.");
        }

        Animal animal = findById(idAnimal)
                .orElseThrow(() -> new IllegalArgumentException("No se encontro el animal con ID " + idAnimal));

        if ("Vendido".equalsIgnoreCase(animal.getEstado())) {
            throw new IllegalArgumentException("El animal ya fue vendido: " + animal.getNombre());
        }

        if (animal.getStock() < cantidad) {
            throw new IllegalArgumentException("Stock insuficiente para " + animal.getNombre());
        }

        return animal;
    }
}

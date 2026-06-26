package Aplicacion.ServiceImpl;

import Aplicacion.repositoryimpl.AnimalRepositoryImpl;
import Dominio.Modelo.Animal;
import Dominio.Service.ServiceGenerico;

import java.util.List;
import java.util.Optional;

public class AnimalServiceImpl implements ServiceGenerico<Animal, Integer> {

    private final AnimalRepositoryImpl animalRepository;

    public AnimalServiceImpl() {
        this.animalRepository = new AnimalRepositoryImpl();
    }

    @Override
    public int save(Animal beans) {
        if (beans == null || beans.getRaza() == null || beans.getEspecie() == null) {
            return -1;
        }
        return animalRepository.save(beans);
    }

    @Override
    public int update(Animal beans) {
        if (beans == null || beans.getIdAnimal() <= 0) return -1;
        return animalRepository.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id < 0) return -1;
        return animalRepository.delete(id);
    }

    @Override
    public Optional<Animal> findById(Integer id) {
        if (id == null || id < 0) return Optional.empty();
        return animalRepository.findById(id);
    }

    @Override
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    @Override
    public int saveAndFinId(Animal beans) {
        if (beans == null || beans.getRaza() == null || beans.getEspecie() == null) {
            return -1;
        }
        return animalRepository.saveAndFinId(beans);
    }

    public Animal finById(Integer id) {
        return findById(id).orElse(null);
    }

    public List<Animal> finAll() {
        return findAll();
    }

    public List<Animal> finAllConsumer() {
        return animalRepository.finAllConsumer();
    }
}

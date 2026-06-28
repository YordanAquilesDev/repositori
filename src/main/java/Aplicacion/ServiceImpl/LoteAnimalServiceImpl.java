package Aplicacion.ServiceImpl;

import Aplicacion.repositoryimpl.LoteAnimalRepositoryImpl;
import Dominio.Modelo.Animal;
import Dominio.Modelo.LoteAnimal;
import Dominio.Service.ServiceGenerico;

import java.util.List;
import java.util.Optional;

public class LoteAnimalServiceImpl implements ServiceGenerico<LoteAnimal, Integer> {

    private final LoteAnimalRepositoryImpl loteAnimalRepository;

    public LoteAnimalServiceImpl() {
        this.loteAnimalRepository = new LoteAnimalRepositoryImpl();
    }

    @Override
    public int save(LoteAnimal beans) {
        if (beans == null) return -1;
        return loteAnimalRepository.save(beans);
    }

    @Override
    public int update(LoteAnimal beans) {
        if (beans == null || beans.getIdLote() <= 0) return -1;
        return loteAnimalRepository.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id < 0) return -1;
        return loteAnimalRepository.delete(id);
    }

    @Override
    public Optional<LoteAnimal> findById(Integer id) {
        if (id == null || id < 0) return Optional.empty();
        return loteAnimalRepository.findById(id);
    }

    @Override
    public List<LoteAnimal> findAll() {
        return loteAnimalRepository.findAll();
    }

    @Override
    public int saveAndFinId(LoteAnimal beans) {
        if (beans == null) return -1;
        return loteAnimalRepository.saveAndFinId(beans);
    }

    public LoteAnimal guardarUnLote(LoteAnimal lote) {
        return loteAnimalRepository.guardarLoteAnimal(lote);
    }

    public LoteAnimal obtenerUnLotePorId(Long id) {
        return loteAnimalRepository.traerPorId(id.intValue());
    }

    public List<LoteAnimal> obtenerTodosLosLotesPorAnimal(Animal animal) {
        return loteAnimalRepository.findAll();
    }
}

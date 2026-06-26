package Aplicacion.ServiceImpl;

import Aplicacion.repositoryimpl.ConsumoLoteRepositoryImpl;
import Dominio.Modelo.Animal;
import Dominio.Modelo.ConsumoLote;
import Dominio.Service.ServiceGenerico;

import java.util.List;
import java.util.Optional;

public class ConsumoServiceImpl implements ServiceGenerico<ConsumoLote, Integer> {

    private final ConsumoLoteRepositoryImpl consumoLoteRepository;

    public ConsumoServiceImpl() {
        this.consumoLoteRepository = new ConsumoLoteRepositoryImpl();
    }

    @Override
    public int save(ConsumoLote beans) {
        if (beans == null) return -1;
        return consumoLoteRepository.save(beans);
    }

    @Override
    public int update(ConsumoLote beans) {
        if (beans == null || beans.getIdConsumo() <= 0) return -1;
        return consumoLoteRepository.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id < 0) return -1;
        return consumoLoteRepository.delete(id);
    }

    @Override
    public Optional<ConsumoLote> findById(Integer id) {
        if (id == null || id < 0) return Optional.empty();
        return consumoLoteRepository.findById(id);
    }

    @Override
    public List<ConsumoLote> findAll() {
        return consumoLoteRepository.findAll();
    }

    @Override
    public int saveAndFinId(ConsumoLote beans) {
        if (beans == null) return -1;
        return consumoLoteRepository.saveAndFinId(beans);
    }

    public int guardarConsumoLote(ConsumoLote consumoLote) {
        return save(consumoLote);
    }

    public ConsumoLote obtenerConsumoPorId(Long id) {
        return consumoLoteRepository.obtenerConsumoPorId(id);
    }

    public List<ConsumoLote> obtenerConsumoLotePorAnimal(Animal animal) {
        if (animal == null || animal.getIdAnimal() <= 0) {
            throw new IllegalArgumentException("El animal no es valido");
        }
        return consumoLoteRepository.obtenerConsumoLotePorAnimal(animal);
    }
}

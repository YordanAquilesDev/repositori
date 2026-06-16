package Aplicacion.ServiceImpl;

import java.util.List;

import Aplicacion.repositoryimpl.ConsumoLoteRepositoryImpl;
import Dominio.Modelo.Animal;
import Dominio.Modelo.ConsumoLote;
import Dominio.Modelo.LoteAnimal;
import Dominio.Service.ConsumoService;
import Dominio.repository.ConsumoLoteRepository;

public class ConsumoServiceImpl implements ConsumoService {

    private final ConsumoLoteRepository consumoLoteRepository;

    public ConsumoServiceImpl() {
        this.consumoLoteRepository = new ConsumoLoteRepositoryImpl();
    }

    // ok
    public int guardarConsumoLote(ConsumoLote consumoLote) {
        return consumoLoteRepository.save(consumoLote);
    }

    // ok
    public List<ConsumoLote> obtenerConsumosPorLote(LoteAnimal lote) {
        return consumoLoteRepository.listarConsumoLotes();
    }

    // ok
    public ConsumoLote obtenerConsumoPorId(Long id) {
        return consumoLoteRepository.obtenerConsumoPorId(id);
    }

    // ok
    public List<ConsumoLote> obtenerConsumoLotePorAnimal(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("El animal no puede ser nulo");
        }
        if (animal.getIdAnimal() <= 0) {
            throw new IllegalArgumentException("El id del animal debe ser mayor a cero");
        }
        return consumoLoteRepository.obtenerConsumoLotePorAnimal(animal);
    }
}

package Dominio.repository;

import java.util.List;

import Dominio.Modelo.Animal;
import Dominio.Modelo.ConsumoLote;

public interface ConsumoLoteRepository {
    ConsumoLote guardarConsumoLote(ConsumoLote consumo);

    List<ConsumoLote> listarConsumoLotes();

    ConsumoLote loteMasConsumidor();

    ConsumoLote obtenerConsumoPorId(Long id);

    List<ConsumoLote> obtenerConsumoLotePorAnimal(Animal animal);
}

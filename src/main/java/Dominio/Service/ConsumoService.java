package Dominio.Service;

import java.util.List;

import Dominio.Modelo.Animal;
import Dominio.Modelo.ConsumoLote;
import Dominio.Modelo.LoteAnimal;

public interface ConsumoService {
    //logica para la tabla consumo_lote
    int guardarConsumoLote(ConsumoLote consumoLote);

    List<ConsumoLote> obtenerConsumosPorLote(LoteAnimal lote);

    ConsumoLote obtenerConsumoPorId(Long id);

    List<ConsumoLote> obtenerConsumoLotePorAnimal(Animal animal);

}

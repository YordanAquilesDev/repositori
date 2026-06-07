package Dominio.IService;

import Dominio.Modelo.Animal;
import Dominio.Modelo.ConsumoLote;

import java.util.List;

public interface IConsumoLote {

    List<ConsumoLote> consumoLoteDePollo(Animal animal);
    List<ConsumoLote> consumoLoteDeCerdo(Animal animal);
    List<ConsumoLote> consumoLoteDeGallina(Animal animal);
    List<ConsumoLote> consumoLoteDePato(Animal animal);

}

package Dominio.Service;

import java.util.List;

import Dominio.Modelo.Animal;
import Dominio.Modelo.LoteAnimal;

public interface LoteService {
    LoteAnimal guardarUnLote(LoteAnimal lote);

    LoteAnimal obtenerUnLotePorId(Long id);

    List<LoteAnimal> obtenerTodosLosLotesPorAnimal(Animal animal);
    
    List<LoteAnimal> obtenerPorEstado(String estado);
    
    List<LoteAnimal> todos();

}

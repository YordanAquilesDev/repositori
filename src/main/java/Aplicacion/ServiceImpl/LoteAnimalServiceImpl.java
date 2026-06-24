package Aplicacion.ServiceImpl;

import Dominio.Modelo.Animal;
import Dominio.Modelo.LoteAnimal;
import Dominio.Service.ServiceGenerico;

import java.util.List;
import java.util.Optional;

public class LoteAnimalServiceImpl implements ServiceGenerico<LoteAnimal, Integer> {

    @Override
    public int save(LoteAnimal beans) {
        return 0;
    }

    @Override
    public int update(LoteAnimal beans) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }

    @Override
    public Optional<LoteAnimal> finById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<LoteAnimal> finAll() {
        return List.of();
    }

    @Override
    public int saveAndFinId(LoteAnimal beans) {
        return 0;
    }
}

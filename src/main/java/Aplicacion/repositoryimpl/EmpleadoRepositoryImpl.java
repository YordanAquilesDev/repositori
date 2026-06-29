package Aplicacion.repositoryimpl;

import Dominio.Modelo.Empleado;
import Dominio.repository.CrudGenerico;

import java.util.List;
import java.util.Optional;

public class EmpleadoRepositoryImpl implements CrudGenerico<Empleado,Integer> {
    @Override
    public int save(Empleado beans) {
        return 0;
    }

    @Override
    public int update(Empleado beans) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }

    @Override
    public Optional<Empleado> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Empleado> findAll() {
        return List.of();
    }

    @Override
    public int saveAndFinId(Empleado beans) {
        return 0;
    }

    public Empleado login(Empleado beans) {
        return null;
    }
}

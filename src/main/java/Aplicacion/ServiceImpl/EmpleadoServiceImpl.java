package Aplicacion.ServiceImpl;

import Aplicacion.repositoryimpl.EmpleadoRepositoryImpl;
import Dominio.Modelo.Empleado;
import Dominio.Service.ServiceGenerico;

import java.util.List;
import java.util.Optional;

public class EmpleadoServiceImpl implements ServiceGenerico<Empleado,Integer> {
   private EmpleadoRepositoryImpl empleadoRepository = new EmpleadoRepositoryImpl();
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

    public Empleado login(String username, String password) {
        Empleado beans = new Empleado();
        beans.setNombreUsuario(username);
        beans.setPassword(password);
       return  empleadoRepository.login(beans);
    }
}

package Aplicacion.Service;

import Aplicacion.DAO.RolDAO;
import Dominio.Modelo.Rol;
import Dominio.repository.ICRUD;

import java.util.List;
import java.util.Optional;

public class RolService implements ICRUD<Rol, Integer> {

    private final RolDAO rolDAO;

    public RolService() {
        this.rolDAO = new RolDAO();
    }

    @Override
    public int save(Rol beans) {
        if (beans == null) return -1;
        return rolDAO.save(beans);
    }

    @Override
    public int update(Rol beans) {
        if (beans == null || beans.getIdRol() <= 0) return -1;
        return rolDAO.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id < 0) return -1;
        return rolDAO.delete(id);
    }

    @Override
    public Optional<Rol> findById(Integer id) {
        if (id == null || id < 0) return Optional.empty();
        return rolDAO.findById(id);
    }

    @Override
    public List<Rol> findAll() {
        return rolDAO.findAll();
    }

    @Override
    public int saveAndFindId(Rol beans) {
        if (beans == null) return -1;
        return rolDAO.saveAndFindId(beans);
    }
}

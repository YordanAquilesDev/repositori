package Aplicacion.Service;

import Aplicacion.DAO.EspecieDAO;
import Dominio.Modelo.Especie;
import Dominio.repository.ICRUD;

import java.util.List;
import java.util.Optional;

public class EspecieService implements ICRUD<Especie, Integer> {

    private final EspecieDAO especieDAO;

    public EspecieService() {
        this.especieDAO = new EspecieDAO();
    }

    @Override
    public int save(Especie beans) {
        if (beans == null) return -1;
        return especieDAO.save(beans);
    }

    @Override
    public int update(Especie beans) {
        if (beans == null || beans.getIdEspecie() <= 0) return -1;
        return especieDAO.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id < 0) return -1;
        return especieDAO.delete(id);
    }

    @Override
    public Optional<Especie> findById(Integer id) {
        if (id == null || id < 0) return Optional.empty();
        return especieDAO.findById(id);
    }

    @Override
    public List<Especie> findAll() {
        return especieDAO.findAll();
    }

    @Override
    public int saveAndFindId(Especie beans) {
        if (beans == null) return -1;
        return especieDAO.saveAndFindId(beans);
    }
}

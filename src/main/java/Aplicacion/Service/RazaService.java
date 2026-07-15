package Aplicacion.Service;

import Aplicacion.DAO.RazaDAO;
import Dominio.Modelo.Raza;
import Dominio.repository.ICRUD;

import java.util.List;
import java.util.Optional;

public class RazaService implements ICRUD<Raza, Integer> {

    private final RazaDAO razaDAO;

    public RazaService() {
        this.razaDAO = new RazaDAO();
    }

    @Override
    public int save(Raza beans) {
        return razaDAO.save(beans);
    }

    @Override
    public int update(Raza beans) {
        return razaDAO.update(beans);
    }

    @Override
    public int delete(Integer integer) {
        return razaDAO.delete(integer);
    }

    @Override
    public Optional<Raza> findById(Integer integer) {
        if (integer == null || integer < 0) return Optional.empty();
        return razaDAO.findById(integer);
    }

    @Override
    public List<Raza> findAll() {
        return razaDAO.findAll();
    }

    @Override
    public int saveAndFindId(Raza beans) {
        return razaDAO.saveAndFindId(beans);
    }
}

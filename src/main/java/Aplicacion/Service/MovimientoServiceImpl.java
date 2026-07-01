package Aplicacion.Service;

import Aplicacion.DAO.MovimientoRepositoryImpl;
import Dominio.Modelo.MovimientoAlmacen;
import Dominio.repository.CrudGenerico;

import java.util.List;
import java.util.Optional;

public class MovimientoServiceImpl implements CrudGenerico<MovimientoAlmacen, Integer> {

    private final MovimientoRepositoryImpl movimientoRepository;

    public MovimientoServiceImpl() {
        this.movimientoRepository = new MovimientoRepositoryImpl();
    }

    @Override
    public int save(MovimientoAlmacen beans) {
        if (beans == null) return -1;
        return movimientoRepository.save(beans);
    }

    @Override
    public int update(MovimientoAlmacen beans) {
        if (beans == null || beans.getIdMovimiento() <= 0) return -1;
        return movimientoRepository.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id < 0) return -1;
        return movimientoRepository.delete(id);
    }

    @Override
    public Optional<MovimientoAlmacen> findById(Integer id) {
        if (id == null || id < 0) return Optional.empty();
        return movimientoRepository.findById(id);
    }

    @Override
    public List<MovimientoAlmacen> findAll() {
        return movimientoRepository.findAll();
    }

    @Override
    public int saveAndFindId(MovimientoAlmacen beans) {
        if (beans == null) return -1;
        return movimientoRepository.saveAndFindId(beans);
    }
}

package Aplicacion.DAO;

import Dominio.Modelo.Producto;
import Dominio.repository.ICRUD;

import java.util.List;
import java.util.Optional;

public class ProductoRepository implements ICRUD<Producto,Integer> {
    @Override
    public int save(Producto beans) {
        return 0;
    }

    @Override
    public int update(Producto beans) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }

    @Override
    public Optional<Producto> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Producto> findAll() {
        return List.of();
    }

    @Override
    public int saveAndFindId(Producto beans) {
        return 0;
    }
}

package Aplicacion.repositoryimpl;

import java.util.List;

import Dominio.Modelo.Venta;
import Dominio.repository.CrudGenerico;

//@Repository                                JpaRepository<T, ID>
public interface VentaRepositoryImpl extends CrudGenerico<Venta, Integer> {

}

// esto ase jpaRepositori por ti
class VentaRepositoryImpl2 implements VentaRepositoryImpl {

    @Override
    public int save(Venta beans) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Venta beans) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Venta findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Venta> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
/*
 * public class VentaRepositoryImpl implements CrudGenerico<Venta,Integer> {
 * 
 * @Override
 * public int save(Venta beans) {
 * return 0;
 * }
 * 
 * @Override
 * public int update(Venta beans) {
 * return 0;
 * }
 * 
 * @Override
 * public int delete(Integer integer) {
 * return 0;
 * }
 * 
 * @Override
 * public Venta findById(Integer integer) {
 * return null;
 * }
 * 
 * @Override
 * public List<Venta> findAll() {
 * return List.of();
 * }
 * }
 */

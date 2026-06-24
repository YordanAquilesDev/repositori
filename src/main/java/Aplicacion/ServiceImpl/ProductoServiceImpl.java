package Aplicacion.ServiceImpl;

import Dominio.Modelo.Producto;
import Dominio.Service.ServiceGenerico;

import java.util.List;

public class ProductoServiceImpl implements ServiceGenerico<Producto,Integer> {
    @Override
    public int save(Producto beans) {
        return 0;
    }

    @Override
    public int update(Producto beans) {
        // luego le ago esta logic
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }

    @Override
    public Producto findById(Integer integer) {
        return null;
    }

    @Override
    public List<Producto> findAll() {
        return List.of();
    }
}

package Aplicacion.ServiceImpl;


import java.util.List;

import Dominio.Modelo.Venta;
import Dominio.Service.ServiceGenerico;


public class VentaServiceImpl  implements ServiceGenerico<Venta,Integer> {


    @Override
    public int save(Venta beans) {
        return 0;
    }

    @Override
    public int update(Venta beans) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }

    @Override
    public Venta findById(Integer integer) {
        return null;
    }

    @Override
    public List<Venta> findAll() {
        return List.of();
    }
}

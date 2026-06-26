package Aplicacion.ServiceImpl;

import Dominio.Modelo.Producto;
import Dominio.Service.ServiceGenerico;

import java.util.List;
import java.util.Optional;

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
    public Optional<Producto> finById(Integer integer) {
        return null;
    }

    @Override
    public List<Producto> finAll() {
        return List.of();
    }

    @Override
    public int saveAndFinId(Producto beans) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

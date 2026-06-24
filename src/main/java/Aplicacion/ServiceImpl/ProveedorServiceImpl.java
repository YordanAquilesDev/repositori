package Aplicacion.ServiceImpl;

import Dominio.Modelo.Proveedor;
import Dominio.Service.ServiceGenerico;

import java.util.List;
import java.util.Optional;

public class ProveedorServiceImpl implements ServiceGenerico<Proveedor, Integer> {
    @Override
    public int save(Proveedor beans) {
        return 0;
    }

    @Override
    public int update(Proveedor beans) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }

    @Override
    public Optional<Proveedor> finById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Proveedor> finAll() {
        return List.of();
    }

    @Override
    public int saveAndFinId(Proveedor beans) {
        return 0;
    }
}

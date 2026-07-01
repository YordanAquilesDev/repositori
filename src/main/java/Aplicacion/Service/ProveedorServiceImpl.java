package Aplicacion.Service;

import Aplicacion.DAO.ProveedorRepositoryImpl;
import Dominio.Modelo.Proveedor;
import Dominio.repository.CrudGenerico;

import java.util.List;
import java.util.Optional;

public class ProveedorServiceImpl implements CrudGenerico<Proveedor, Integer> {

    private final ProveedorRepositoryImpl proveedorRepository;

    public ProveedorServiceImpl() {
        this.proveedorRepository = new ProveedorRepositoryImpl();
    }

    @Override
    public int save(Proveedor beans) {
        if (beans == null) return -1;
        return proveedorRepository.save(beans);
    }

    @Override
    public int update(Proveedor beans) {
        if (beans == null || beans.getIdProveedor() <= 0) return -1;
        return proveedorRepository.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id < 0) return -1;
        return proveedorRepository.delete(id);
    }

    @Override
    public Optional<Proveedor> findById(Integer id) {
        if (id == null || id < 0) return Optional.empty();
        return proveedorRepository.findById(id);
    }

    @Override
    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    @Override
    public int saveAndFindId(Proveedor beans) {
        if (beans == null) return -1;
        return proveedorRepository.saveAndFindId(beans);
    }
}

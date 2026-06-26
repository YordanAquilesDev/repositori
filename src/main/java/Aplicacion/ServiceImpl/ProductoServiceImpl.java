package Aplicacion.ServiceImpl;

import Aplicacion.repositoryimpl.ProductoRepositoryImpl;
import Dominio.Modelo.Producto;
import Dominio.Service.ServiceGenerico;

import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ServiceGenerico<Producto, Integer> {

    private final ProductoRepositoryImpl productoRepository;

    public ProductoServiceImpl() {
        this.productoRepository = new ProductoRepositoryImpl();
    }

    @Override
    public int save(Producto beans) {
        if (beans == null) return -1;
        return productoRepository.save(beans);
    }

    @Override
    public int update(Producto beans) {
        if (beans == null || beans.getIdProducto() <= 0) return -1;
        return productoRepository.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id < 0) return -1;
        return productoRepository.delete(id);
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        if (id == null || id < 0) return Optional.empty();
        return productoRepository.findById(id);
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public int saveAndFinId(Producto beans) {
        if (beans == null) return -1;
        return productoRepository.saveAndFinId(beans);
    }
}

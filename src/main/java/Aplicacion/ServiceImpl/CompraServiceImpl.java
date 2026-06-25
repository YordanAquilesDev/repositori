package Aplicacion.ServiceImpl;

import Aplicacion.repositoryimpl.CompraRepositoryImpl;
import Dominio.Modelo.Compra;
import Dominio.Service.ServiceGenerico;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class CompraServiceImpl implements ServiceGenerico<Compra, Integer> {

    private final CompraRepositoryImpl compraRepository;

    public CompraServiceImpl() {
        this.compraRepository = new CompraRepositoryImpl();
    }

    @Override
    public int save(Compra beans) {
        if (beans == null || beans.getProveedor() == null) return -1;
        return compraRepository.save(beans);
    }

    @Override
    public int update(Compra beans) {
        if (beans == null || beans.getIdCompra() <= 0) return -1;
        return compraRepository.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id < 0) return -1;
        return compraRepository.delete(id);
    }

    @Override
    public Optional<Compra> findById(Integer id) {
        if (id == null || id < 0) return Optional.empty();
        return compraRepository.findById(id);
    }

    @Override
    public List<Compra> findAll() {
        return compraRepository.findAll();
    }

    @Override
    public int saveAndFinId(Compra beans) {
        if (beans == null || beans.getProveedor() == null) return -1;
        return compraRepository.saveAndFinId(beans);
    }

    public Compra obtenerCompraPorId(Long id) {
        return findById(id.intValue()).orElse(null);
    }

    public List<Compra> obtenerTodasLasCompras() {
        return findAll();
    }

    public List<Compra> obtenerComprasPorFecha(Date fecha, Date fecha2) {
        return compraRepository.listarComprasPorFecha(fecha, fecha2);
    }

    public List<Compra> listarComprasMasAltos() {
        return compraRepository.listarComprasMasAltos();
    }
}

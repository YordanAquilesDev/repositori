package Aplicacion.ServiceImpl;

import Aplicacion.repositoryimpl.CompraRepositoryImpl;
import Dominio.Modelo.Compra;
import Dominio.Modelo.DetalleCompra;
import Dominio.Service.ServiceGenerico;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class CompraServiceImpl implements ServiceGenerico<Compra, Integer> {

    private final CompraRepositoryImpl compraRepository;
    private final DetalleCompraServiceImpl detalleCompraService;

    public CompraServiceImpl() {
        this.compraRepository = new CompraRepositoryImpl();
        this.detalleCompraService = new DetalleCompraServiceImpl();
    }

    @Override
    public int save(Compra beans) {
        if (beans == null || beans.getProveedor() == null) return -1;

        List<DetalleCompra> detalles = beans.getDetalles();
        if (detalles == null || detalles.isEmpty()) {
            return -1;
        }

        double total = detalles.stream()
                .mapToDouble(DetalleCompra::getSubtotal)
                .sum();
        beans.setTotal(total);

        int idCompra = saveAndFinId(beans);
        if (idCompra > 0) {
            final int id = idCompra;
            detalles.forEach(d -> d.getCompra().setIdCompra(id));
            int filas = detalles.stream()
                    .mapToInt(detalleCompraService::save)
                    .sum();
            return filas > 0 ? 1 : 0;
        }
        return -1;
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

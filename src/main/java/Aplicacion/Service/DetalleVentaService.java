package Aplicacion.Service;

import Aplicacion.DAO.DetalleVentaDAO;
import Dominio.Modelo.DetalleVenta;
import Dominio.repository.ICRUD;

import java.util.List;
import java.util.Optional;

public class DetalleVentaService implements ICRUD<DetalleVenta, Integer> {

    private final DetalleVentaDAO detalleVentaDAO;

    public DetalleVentaService() {
        this.detalleVentaDAO = new DetalleVentaDAO();
    }

    @Override
    public int save(DetalleVenta detalleVenta) {
        return detalleVentaDAO.save(detalleVenta);
    }

    @Override
    public int update(DetalleVenta beans) {
        return detalleVentaDAO.update(beans);
    }

    @Override
    public int delete(Integer integer) {
        if (integer == null || integer < 0) {
            throw new IllegalArgumentException("valores de objetos nulos ");
        }
        return detalleVentaDAO.delete(integer);
    }

    @Override
    public Optional<DetalleVenta> findById(Integer integer) {
        if (integer == null || integer < 0) {
            throw new IllegalArgumentException("valores de objetos nulos ");
        }
        return detalleVentaDAO.findById(integer);
    }

    @Override
    public List<DetalleVenta> findAll() {
        return detalleVentaDAO.findAll();
    }

    @Override
    public int saveAndFindId(DetalleVenta beans) {
        if (beans == null) return -1;
        return detalleVentaDAO.saveAndFindId(beans);
    }
}

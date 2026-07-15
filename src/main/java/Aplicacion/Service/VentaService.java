package Aplicacion.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Aplicacion.DAO.VentaDAO;
import Dominio.Modelo.DetalleVenta;
import Dominio.Modelo.Venta;
import Dominio.repository.ICRUD;

public class VentaService implements ICRUD<Venta, Integer> {

    private final VentaDAO ventaDAO;
    private final DetalleVentaService detalleVentaService;
    private final AnimalService animalService;

    public VentaService() {
        this.detalleVentaService = new DetalleVentaService();
        this.ventaDAO = new VentaDAO();
        this.animalService = new AnimalService();
    }

    @Override
    public int save(Venta beans) {
        if (beans == null || beans.getDetalleVentas() == null || beans.getDetalleVentas().isEmpty()) {
            throw new IllegalArgumentException("No se puede registrar una venta sin detalles.");
        }

        beans.getDetalleVentas()
                .forEach(detalle -> animalService.validarDisponibilidad(detalle.getIdAnimal(), detalle.getCantidad()));

        double total = beans.getDetalleVentas()
                .stream()
                .mapToDouble(DetalleVenta::getSubtotal)
                .sum();
        beans.setTotal(total);

        if (beans.getFecha() == null || beans.getFecha().isEmpty()) {
            beans.setFecha(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        int idVenta = saveAndFindId(beans);
        if (idVenta <= 0) {
            return -1;
        }

        beans.getDetalleVentas()
                .forEach(bean -> bean.setIdVenta(idVenta));

        List<Integer> idsDetalleGuardados = new ArrayList<>();
        for (DetalleVenta detalle : beans.getDetalleVentas()) {
            int idDetalle = detalleVentaService.saveAndFindId(detalle);
            if (idDetalle <= 0) {
                idsDetalleGuardados.forEach(detalleVentaService::delete);
                ventaDAO.delete(idVenta);
                return 0;
            }
            detalle.setIdDetalle(idDetalle);
            idsDetalleGuardados.add(idDetalle);
        }

        for (DetalleVenta detalle : beans.getDetalleVentas()) {
            int actualizado = animalService.descontarStock(detalle.getIdAnimal(), detalle.getCantidad());
            if (actualizado <= 0) {
                idsDetalleGuardados.forEach(detalleVentaService::delete);
                ventaDAO.delete(idVenta);
                return 0;
            }
        }
        return 1;
    }

    @Override
    public Optional<Venta> findById(Integer integer) {
        if (integer == null || integer < 0) {
            throw new IllegalArgumentException("id no puede ser null o negativo");
        }
        return ventaDAO.findById(integer);
    }

    @Override
    public List<Venta> findAll() {
        return ventaDAO.findAll();
    }

    @Override
    public int saveAndFindId(Venta beans) {
        if (beans.getDetalleVentas() != null && !beans.getDetalleVentas().isEmpty()) {
            double totalCalculado = beans.getDetalleVentas().stream()
                    .mapToDouble(DetalleVenta::getSubtotal)
                    .sum();
            beans.setTotal(totalCalculado);
        } else {
            throw new IllegalArgumentException("No se puede registrar una venta sin detalles/productos.");
        }

        return ventaDAO.saveAndFindId(beans);
    }

    @Override
    public int update(Venta beans) {
        if (beans == null || beans.getIdVenta() <= 0) {
            throw new IllegalArgumentException("El objeto venta o su ID son inválidos para actualizar.");
        }

        Optional<Venta> ventaExistente = ventaDAO.findById(beans.getIdVenta());
        if (ventaExistente.isEmpty()) {
            throw new NoSuchElementException("No se encontró la venta con el ID especificado: " + beans.getIdVenta());
        }

        beans.setFecha(ventaExistente.get().getFecha());

        return ventaDAO.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID de la venta debe ser un número positivo.");
        }

        Optional<Venta> venta = ventaDAO.findById(id);
        if (venta.isEmpty()) {
            throw new NoSuchElementException("Intento de eliminar una venta inexistente.");
        }

        return ventaDAO.delete(id);
    }
}

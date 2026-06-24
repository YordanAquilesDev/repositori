package Dominio.Modelo;

import java.sql.Date;
import java.util.List;

public class Venta {
    private int idVenta;
    private Cliente cliente;
    private Date fecha;
    private double total;
    List<DetalleVenta> detalleVentas;

    public List<DetalleVenta> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }

    public Venta(int idVenta, Cliente cliente, Date fecha, double total) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;

    }
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

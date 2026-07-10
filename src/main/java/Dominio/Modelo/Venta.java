package Dominio.Modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Venta {

    private int idVenta;
    private int idCliente;
    private String fecha;
    private double total;
    private List<DetalleVenta> detalleVentas= new ArrayList<>();

    public List<DetalleVenta> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }

    public Venta() {
    }

    public Venta(int idVenta, int idCliente, String fecha, double total) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.total = total;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
}

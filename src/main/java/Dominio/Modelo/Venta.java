package Dominio.Modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Venta {
    private int idVenta;
    private Usuario usuario;
    private Date fecha;
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

    public Venta(int idVenta, Usuario usuario, Date fecha, double total) {
        this.idVenta = idVenta;
        this.usuario = usuario;
        this.fecha = fecha;
        this.total = total;

    }
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public void addDetalleVenta(DetalleVenta detalleVenta) {
        this.detalleVentas.add(detalleVenta);
    }
}

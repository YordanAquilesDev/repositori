package Dominio.Modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Compra {
    private int idCompra;
    private Proveedor proveedor;
    private Date fecha;
    private double total;
    private List<DetalleCompra> detalles;

    public Compra() {
    }

    public Compra(int idCompra,
            Date fecha,
            Proveedor proveedor,
            double total) {
        this.idCompra = idCompra;
        this.fecha = fecha;
        this.proveedor = proveedor;
        this.total = total;
        detalles = new ArrayList<>();
    }
    public void addDetalleCompra(DetalleCompra detalleCompra) {
        this.detalles.add(detalleCompra);
    }


    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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

    public List<DetalleCompra> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCompra> detalles) {
        this.detalles = detalles;
    }
}

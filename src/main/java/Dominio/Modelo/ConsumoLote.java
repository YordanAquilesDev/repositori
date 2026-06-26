package Dominio.Modelo;

import java.sql.Date;

public class ConsumoLote {
    private  int idConsumo;
    private LoteAnimal lote;
    private Producto producto;
    private double cantidad;
    private Date fecha;

    public ConsumoLote(int idConsumo,
                       LoteAnimal lote,
                       double cantidad,
                       Producto producto,
                       Date fecha) {
        this.idConsumo = idConsumo;
        this.lote = lote;
        this.cantidad = cantidad;
        this.producto = producto;
        this.fecha = fecha;
    }

    public ConsumoLote() {
    }

    public int getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(int idConsumo) {
        this.idConsumo = idConsumo;
    }

    public LoteAnimal getLote() {
        return lote;
    }

    public void setLote(LoteAnimal lote) {
        this.lote = lote;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}

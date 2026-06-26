package Dominio.Modelo;

import java.sql.Date;
import java.util.List;

public class ConsumoLote {
    private  int idConsumo;
    private LoteAnimal lote;
    private List<Producto> producto;
    private int cantidad;
    private Date fecha;

    public ConsumoLote(int idConsumo,
                       LoteAnimal lote,
                       int cantidad,
                       List<Producto> producto,
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

    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}

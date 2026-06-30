package Dominio.Modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int idPedido;
    private Usuario usuario;
    private Date fecha;
    private String estado;
    private double total;
    List<DetallePedido> detalles= new ArrayList<>();

    public Pedido() {
    }
    public Pedido(int id){
        this.idPedido=id;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    public Pedido(Date fecha, Usuario usuario, String estado, double total) {
        this.fecha = fecha;
        this.usuario = usuario;
        this.estado = estado;
        this.total = total;
    }

    public Pedido(int id,
            Date fecha,
            Usuario usuario,
            String estado,
            double total) {
        this.fecha = fecha;
        this.idPedido = id;
        this.usuario = usuario;
        this.estado = estado;
        this.total = total;

    }

    public Date getFecha() {
        return fecha;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


    public void addDetallePedido(DetallePedido detalle) {
        this.detalles.add(detalle);

    }
}

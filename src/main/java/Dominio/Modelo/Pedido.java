package Dominio.Modelo;

import java.sql.Date;

public class Pedido {

    private int idPedido;
    private Cliente cliente;
    private Date fecha;
    private String estado;
    private double total;

    public Pedido() {
    }

    public Pedido(Date fecha, Cliente cliente, String estado, double total) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.estado = estado;
        this.total = total;
    }

    public Pedido(int id,
            Date fecha,
            Cliente cliente,
            String estado,
            double total) {
        this.fecha = fecha;
        this.idPedido = id;
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

}

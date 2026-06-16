package Dominio.Service;

import java.util.List;

import Dominio.Modelo.DetalleCompra;

public interface DetalleCompraService {
    //logica para la tabla detalle_compra
    int guardarUnDetalleCompra(DetalleCompra detalleCompra);

    DetalleCompra obtenerUnDetalleCompraPorId(Long id);

    List<DetalleCompra> obtenerTodosLosDetalleCompras();

}

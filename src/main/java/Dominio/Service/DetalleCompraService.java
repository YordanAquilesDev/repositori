package Dominio.Service;

import java.util.List;

import Dominio.Modelo.DetalleCompra;

public interface DetalleCompraService {

    DetalleCompra guardarUnDetalleCompra(DetalleCompra detalleCompra);

    DetalleCompra obtenerUnDetalleCompraPorId(Long id);

    List<DetalleCompra> obtenerTodosLosDetalleCompras();

}

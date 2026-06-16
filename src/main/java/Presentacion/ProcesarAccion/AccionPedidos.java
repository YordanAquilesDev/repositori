package Presentacion.ProcesarAccion;

import Aplicacion.ServiceImpl.PedidoServiceImpl;
import Dominio.Modelo.Pedido;
import Dominio.Service.PedidoService;
import Dominio.Service.ProductoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Iterator;
import java.util.List;

public class AccionPedidos {
  private final PedidoService pedidoService;
  public AccionPedidos() {
      this.pedidoService= new PedidoServiceImpl();
  }


    public DefaultTableModel modelo(JTable tabla,int indiceCombo){

      List<Pedido> pedidos= pedidoService.obtenerTodosLosPedidos();
      if(pedidos.isEmpty()){
          return null;
      }
      String filtro= switch (indiceCombo){
          case 0 -> "Todos";
          case 1 -> "Entregados";
          case 2 -> "No Entregados";
          case 3 -> "Cancelados";

          default -> throw new IllegalStateException("Unexpected value: " + indiceCombo);
      };

      DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
      switch (indiceCombo) {
          case 0:
              for (Pedido pedido : pedidos) {
                  modelo.addRow(new Object[]{pedido.getIdPedido(),
                          pedido.getCliente().getNombre(),
                          pedido.getFecha(),
                          pedido.getEstado(),
                          pedido.getTotal()});
              }
              return modelo;

          case 1,2,3:
              List<Pedido> listaPedidosFiltros= pedidoService.pedidoPorFiltro(filtro);
              for (Pedido pedido : listaPedidosFiltros) {
                  modelo.addRow(new Object[]{pedido.getIdPedido(),
                          pedido.getCliente().getNombre(),
                          pedido.getFecha(),
                          pedido.getEstado(),
                          pedido.getTotal()});
              }
              return modelo;

              default :
              throw new IllegalStateException("Unexpected value: " + indiceCombo);

      }


    }

    public int modificarPedido(Pedido pedido){
     return pedidoService.actualizarPedido(pedido);

    }
}

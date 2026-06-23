package Aplicacion.ServiceImpl;

import Aplicacion.repositoryimpl.DetallePedidoRepositoryImpl;
import Dominio.Modelo.DetallePedido;

import java.util.List;

public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    public DetallePedidoServiceImpl() {
        this.detallePedidoRepository = new DetallePedidoRepositoryImpl();
    }
    @Override
    public int  guardarUnDetallePedido(DetallePedido detallePedido) {
        if(detallePedido== null){
            return  -1;
        }

        if(detallePedido.getIdDetalle()==-1||
                detallePedido.getCantidad()==0||
                detallePedido.getProducto().getIdProducto()==-1||
                detallePedido.getPedido().getIdPedido()==-1){
            return  -1;
        }
        return detallePedidoRepository.save(detallePedido);
    }

    @Override
    public DetallePedido obtenerUnDetallePedidoPorId(Long id) {
        return null;
    }

    @Override
    public List<DetallePedido> obtenerTodosLosDetallePedidos() {
        return List.of();
    }
}

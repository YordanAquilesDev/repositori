package Aplicacion.ServiceImpl;

import Aplicacion.repositoryimpl.DetallePedidoRepositoryImpl;
import Dominio.Modelo.DetallePedido;
import Dominio.Service.ServiceGenerico;

import java.util.List;
import java.util.Optional;

public class DetallePedidoServiceImpl implements ServiceGenerico<DetallePedido,Integer> {

    private final DetallePedidoRepositoryImpl detallePedidoRepository;
    public DetallePedidoServiceImpl() {
        this.detallePedidoRepository = new DetallePedidoRepositoryImpl();
    }

    @Override
    public int save(DetallePedido beans) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(DetallePedido beans) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<DetallePedido> findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DetallePedido> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int saveAndFinId(DetallePedido beans) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

package Aplicacion.Service;

import Aplicacion.DAO.PedidoRepositoryImpl;
import Dominio.Modelo.DetallePedido;
import Dominio.Modelo.Pedido;
import Dominio.repository.CrudGenerico;

import java.util.List;
import java.util.Optional;

public class PedidoServiceImpl implements CrudGenerico<Pedido, Integer> {

    private final PedidoRepositoryImpl pedidoRepository;
    private final DetallePedidoServiceImpl detallePedidoService;

    public PedidoServiceImpl() {
        this.pedidoRepository = new PedidoRepositoryImpl();
        this.detallePedidoService = new DetallePedidoServiceImpl();
    }
   //prueba
    @Override
    public int save(Pedido beans) {
        if (beans == null || beans.getUsuario() == null) return -1;
        double total=0;
        total=beans.getDetalles().stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
        beans.setTotal(total);
        int idGenerado=pedidoRepository.saveAndFindId(beans);
        if(idGenerado>0){
            beans.getDetalles().forEach(p->p.getPedido().setIdPedido(idGenerado));
         int filasAfectadas=   beans.getDetalles().stream()
                    .mapToInt(detallePedidoService::save).
                    sum();

         if(filasAfectadas>0){
             return 1;
         }
         return 0;
        }
        return  -1;
    }

    @Override
    public int update(Pedido beans) {
        if (beans == null || beans.getIdPedido() <= 0) return -1;
        return pedidoRepository.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id < 0) return -1;
        return pedidoRepository.delete(id);
    }

    @Override
    public Optional<Pedido> findById(Integer id) {
        if (id == null || id < 0) return Optional.empty();
        return pedidoRepository.findById(id);
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public int saveAndFindId(Pedido beans) {
        if (beans == null || beans.getUsuario() == null) return -1;
        return pedidoRepository.saveAndFindId(beans);
    }

    public Pedido obtenerUnPedidoPorId(Long id) {
        return findById(id.intValue()).orElse(null);
    }

    public List<Pedido> obtenerTodosLosPedidos() {
        return findAll();
    }

    public int actualizarPedido(Pedido pedido) {
        return update(pedido);
    }
}

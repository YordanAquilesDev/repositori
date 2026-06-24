package Aplicacion.ServiceImpl;

import java.util.List;
import java.util.Optional;

import Aplicacion.repositoryimpl.VentaRepositoryImpl;
import Dominio.Modelo.DetalleVenta;
import Dominio.Modelo.Venta;
import Dominio.Service.ServiceGenerico;



public class VentaServiceImpl  implements ServiceGenerico<Venta,Integer> {
   private final VentaRepositoryImpl ventaRepository;
   private  final DetalleVentaServiceImpl detalleVentaService;
   public VentaServiceImpl() {
       this.detalleVentaService = new DetalleVentaServiceImpl();
       this.ventaRepository =  new VentaRepositoryImpl();
   }
    @Override
    public int save(Venta beans) {
       int guardarYgenerarId = 0;
       int filasAfectadas = 0;
       double total = 0;

        if(beans.getCliente()==null||beans.getFecha()==null||beans.getTotal()<0.0){
            throw  new IllegalArgumentException("valores de la venta  tiene algunos problemas ");
        }

        total =beans.getDetalleVentas()
                .stream()
                .mapToDouble(DetalleVenta::getSubtotal)
                .sum();

        beans.setTotal(total);
        // guardamos la venta primero  debido a que
        // DetalleVenta depende del id_venta
        guardarYgenerarId = saveAndFinId(beans);
        if(guardarYgenerarId>0){
            int id_venta = guardarYgenerarId;
            //cargamos el id_venta a todos los detalles
            beans.getDetalleVentas()
                    .forEach(bean -> {
                        bean.getVenta().setIdVenta(id_venta);
                    });

            //ahora guardamos detalleVenta en la db
          filasAfectadas=  beans.getDetalleVentas()
                  .stream()
                  .mapToInt(detalleVentaService::save) //capturamos la respuesta de filas afectadas en la db
                  .sum();  // lo sumanos
          if(filasAfectadas>0){
              // si se guardo la venta y los detalles correctamente
              return 1;
          }else{
              //caso que  se guardo la venta pero no los detalles
              return 0;
          }
        }
        // caso que  no se guardo la venta
        return -1;
    }

    @Override
    public int update(Venta beans) {
       if(beans==null||beans.getCliente()==null||beans.getFecha()==null||beans.getTotal()<0.0){
           throw  new  IllegalArgumentException(" los valores del objeto venta estan  inconpatibles");
       }
       return ventaRepository.update(beans);
    }

    @Override
    public int delete(Integer integer) {
       if(integer==null||integer<0){
           throw new IllegalArgumentException("id no puede ser null o negativo");
       }
       return ventaRepository.delete(integer);
    }

    @Override
    public Optional<Venta> findById(Integer integer) {
       if(integer==null||integer<0){
           throw new IllegalArgumentException("id no puede ser null o negativo");
       }
        return ventaRepository.findById(integer);
    }

    @Override
    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    @Override
    public int saveAndFinId(Venta beans) {
        return ventaRepository.saveAndFinId(beans);
    }
}

package Aplicacion.ServiceImpl;

import java.util.List;
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
       if(beans==null){
           throw  new  IllegalArgumentException(" la venta no puede ser nula");
       }
       if(beans.getCliente()==null||beans.getFecha()==null||beans.getTotal()<0.0){
           throw new IllegalArgumentException("valores de la venta  tiene algunos problemas ");
       }
       return ventaRepository.update(beans);

    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }

    @Override
    public Venta findById(Integer integer) {
        return null;
    }

    @Override
    public List<Venta> findAll() {
        return List.of();
    }

    @Override
    public int saveAndFinId(Venta beans) {
        return ventaRepository.saveAndFinId(beans);
    }
}

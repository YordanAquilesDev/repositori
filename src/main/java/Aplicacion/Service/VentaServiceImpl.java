package Aplicacion.Service;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import Aplicacion.DAO.VentaRepositoryImpl;
import Dominio.Modelo.DetalleVenta;
import Dominio.Modelo.Venta;
import Dominio.repository.CrudGenerico;

public class VentaServiceImpl  implements CrudGenerico<Venta,Integer> {
   private final VentaRepositoryImpl ventaRepository;
   private  final DetalleVentaServiceImpl detalleVentaService;
   public VentaServiceImpl() {
  //
       this.detalleVentaService = new DetalleVentaServiceImpl();
       this.ventaRepository =  new VentaRepositoryImpl();
   }

    @Override
    public int save(Venta beans) {
       int guardarYgenerarId = 0;
       int filasAfectadas = 0;
       double total = 0;

        if(beans.getUsuario()==null||beans.getFecha()==null||beans.getTotal()<0.0){
            throw  new IllegalArgumentException("valores de la venta  tiene algunos problemas ");
        }

        total =beans.getDetalleVentas()
                .stream()
                .mapToDouble(DetalleVenta::getSubtotal)
                .sum();

        beans.setTotal(total);
        // guardamos la venta primero  debido a que
        // DetalleVenta depende del idVenta
        guardarYgenerarId = saveAndFindId(beans);
        if(guardarYgenerarId>0){
            int idVenta = guardarYgenerarId;
            //cargamos el idVenta a todos los detalles
            beans.getDetalleVentas()
                    .forEach(bean -> {
                        bean.getVenta().setIdVenta(idVenta);
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
    public int saveAndFindId(Venta beans) {
        // 1. Validaciones estructurales básicas
        if (beans == null || beans.getUsuario() == null) {
            throw new IllegalArgumentException("La venta o el cliente no pueden ser nulos.");
        }

        // 2. Automatizar datos: Asignar la fecha actual del sistema
        // 3. Regla de negocio: Calcular el total dinámicamente si hay detalles
        if (beans.getDetalleVentas() != null && !beans.getDetalleVentas().isEmpty()) {
            double totalCalculado = beans.getDetalleVentas().stream()
                    .mapToDouble(DetalleVenta::getSubtotal)
                    .sum();
            beans.setTotal(totalCalculado);
        } else {
            throw new IllegalArgumentException("No se puede registrar una venta sin detalles/productos.");
        }

        // 4. Regla de negocio avanzada: Verificar y descontar stock aquí si fuera necesario
        // productoService.descontarStock(beans.getDetalleVentas());
        return ventaRepository.saveAndFindId(beans);
    }

    @Override
    public int update(Venta beans) {
        if (beans == null || beans.getIdVenta() <= 0) {
            throw new IllegalArgumentException("El objeto venta o su ID son inválidos para actualizar.");
        }

        // 1. Regla de negocio: Verificar si la venta realmente existe en la DB antes de modificarla
        Optional<Venta> ventaExistente = ventaRepository.findById(beans.getIdVenta());
        if (ventaExistente.isEmpty()) {
            throw new NoSuchElementException("No se encontró la venta con el ID especificado: " + beans.getIdVenta());
        }

        // 2. Mantener la fecha original si no se desea cambiar en la edición
        beans.setFecha(ventaExistente.get().getFecha());

        return ventaRepository.update(beans);
    }

    @Override
    public int delete(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID de la venta debe ser un número positivo.");
        }

        // Regla de negocio: Verificar existencia antes de eliminar
        Optional<Venta> venta = ventaRepository.findById(id);
        if (venta.isEmpty()) {
            throw new NoSuchElementException("Intento de eliminar una venta inexistente.");
        }

        // Aquí podrías validar: si la venta tiene más de X días, impedir su eliminación.

        return ventaRepository.delete(id);
    }



}

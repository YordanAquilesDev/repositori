package Aplicacion.ServiceImpl;

import Dominio.Modelo.DetalleVenta;
import Dominio.Modelo.Producto;
import Dominio.Service.ServiceGenerico;

import java.util.List;

public class DetalleVentaServiceImpl  implements ServiceGenerico<DetalleVenta,Integer> {
    private final ProductoServiceImpl productoService;

    public DetalleVentaServiceImpl() {
        this.productoService = new ProductoServiceImpl();
    }
    @Override
    public int save(DetalleVenta beans) {
        int resultado = 0;
        //verificamos que  DetalleVenta no sea nulo
        if(beans==null||beans.getVenta()==null||beans.getProducto()==null){
            throw  new IllegalArgumentException("valores de objetos nulos ");
        }
        // recuperamos el producto  del detalle por su id
       Producto p = productoService.findById(beans.getProducto().getIdProducto());
        // validamos si el valor del detalleVenta es mayor al de stock
        //  no puede ser mayor al stock disponible
       if(p.getStock()>beans.getCantidad()) {
           //restamos al stock la cantidad del producto vendido
           p.setStock(p.getStock()-beans.getCantidad());
           //actualizamos la tabla productos
           resultado= productoService.update(p);
       }else{
           throw new IllegalArgumentException("stock negativo");
       }
       //  si resultado es negativo es porque uvo error en actulizar la tabla productos
       if(resultado<=0){
           throw  new IllegalArgumentException("No se actualizado la tabla productos");
       }
       return resultado;
    }

    @Override
    public int update(DetalleVenta beans) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }

    @Override
    public DetalleVenta findById(Integer integer) {
        return null;
    }

    @Override
    public List<DetalleVenta> findAll() {
        return List.of();
    }

    @Override
    public int saveAndFinId(DetalleVenta beans) {
        return 0;
    }
}

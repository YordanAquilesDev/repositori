package testAplication;

import Aplicacion.ServiceImpl.*;
import Aplicacion.utils.ConexionMySQL;
import Dominio.Modelo.*;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

public class AplicacionTest {


    public static void main(String[] args) {
        // listo para produccion
        // solo falta aplicarle la UI

     //testearAnimalService();
       //testearCliente();
       //testearCompra();
       // testearConsumoLote();
        //testearPeidos();
        //testearVentas();
       // testearLoteAnimal();
        //testearMovimiento();
    }

    public static void  testearAnimalService(){
        AnimalServiceImpl animalService= new AnimalServiceImpl();
        Connection conexion=null;
        try {
           conexion=  ConexionMySQL.getConexionMySQL();
           if(conexion==null){
               System.out.println("No se ha establecido conexion");
           }else{
               System.out.println("Conexion establecida");
           }

            //save
            if (animalService.save(new Animal("Test","Pruvea")) > 0) {
                System.out.println("Animal guardado exitosamente");
            } else {
                System.out.println("Error guardando");
            }
            if (animalService.save(new Animal("Test","Pruvea")) > 0) {
                System.out.println("Animal guardado exitosamente");
            } else {
                System.out.println("Error guardando");
            }
            System.out.println();
            //update
            if (animalService.update(new Animal(1,"NuevaRaza","NuevaEspecie")) > 0) {
                System.out.println("Animal actualizado exitosamente");
            } else {
                System.out.println("Error actualizando");
            }
            System.out.println();
            //delete
            if (animalService.delete(1) > 0) {
                System.out.println("Animal eliminado exitosamente");
            } else {
                System.out.println("Error eliminando");
            }
            System.out.println();
            //findById
            if (animalService.findById(2) != null) {
                System.out.println("animal encontrado exitosamente");
            } else {
                System.out.println("Error encontrando");
            }
            System.out.println();
            //findAll
            animalService.findAll().forEach(System.out::println);
            System.out.println();
            //saveAndFindId
            if (animalService.save(new Animal("NuevaEspecie33","NuevaRaza")) > 0) {
                System.out.println("Animal guardado exitosamente");
            } else {
                System.out.println("Error guardando");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public  static void testearCliente(){
        UsuarioServiceImpl usuarioService= new UsuarioServiceImpl();
        Connection conexion=null;
        try {
            conexion=  ConexionMySQL.getConexionMySQL();
            if(conexion==null){
                System.out.println("\t\t\tNo se ha establecido conexion");

            }else{
                System.out.println("\t\t\tConexion establecida");
            }

            //save
            if(usuarioService.save(new Usuario(
                    0,"N","A",null,"CLIENTE",
                    "N","A","D","C","Dir",null,"ACTIVO"))>0){
                System.out.println("\t\t\tUsuario guardado exitosamente");

            }else{
                System.out.println("Error guardando");
            }
            //save
            if(usuarioService.save(new Usuario(
                    0,"N","A",null,"CLIENTE",
                    "N","A","D","C","Dir",null,"ACTIVO"))>0){
                System.out.println("\t\t\tUsuario guardado exitosamente");

            }else{
                System.out.println("Error guardando");
            }
            //update
            if(usuarioService.update(new Usuario(1,"ONN","ABVKH",null,"CLIENTE",
                    "ONN","ABVKH","DHIj","C","Dir",null,"ACTIVO"))>0){
                System.out.println("\t\t\tUsuario actualizado exitosamente");
            }else{
                System.out.println("Error actualizando");
            }

            //delete
            if(usuarioService.delete(1)>0){
                System.out.println("\t\t\tCliente eliminado exitosamente");
            }else{
                System.out.println("\t\t\tError eliminando");
            }

            //findById
            if(usuarioService.findById(2)!=null){
                System.out.println("\t\t\tCliente buscando exitosamente");
            }else{
                System.out.println("Error buscando");
            }

            //findAll
             if(usuarioService.findAll()!=null) {
                usuarioService.findAll().forEach(System.out::println);

             }else{
                 System.out.println("Error buscando");
             }


            //saveAndFindId
            if(usuarioService.save(new Usuario( 0,"N","A",null,"CLIENTE",
                    "N","A","D","C","Dir",null,"ACTIVO"))>0){
                System.out.println("\t\t\t Usuario guardado exitosamente");
            }else{
                System.out.println("Error guardando");
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  static void testearCompra(){
        ProveedorServiceImpl proveedorService= new ProveedorServiceImpl();
        CompraServiceImpl compraService= new CompraServiceImpl();
        Connection conexion=null;
        try {
            conexion=  ConexionMySQL.getConexionMySQL();
            if(conexion==null){
                System.out.println("\t\t\tNo se ha establecido conexion");
            }else{
                System.out.println("\t\t\tConexion establecida");
            }
            //save
            ProductoServiceImpl productoService = new ProductoServiceImpl();
            // logica para aser una compra completa
            Proveedor proveedor = proveedorService.findById(22).orElse(null);
            Compra compra = new Compra();
            compra.setIdCompra(0);
            compra.setFecha(Date.valueOf(LocalDate.now()));
            compra.setProveedor(proveedor);
            Producto producto1 = productoService.findById(22).orElse(null);
            int cantidad= 10;
            assert producto1 != null;
            compra.addDetalleCompra(new DetalleCompra(0,compra
                    ,producto1
                    ,cantidad
                    ,producto1.getPrecioUnidad()* cantidad));
            Producto producto2 = productoService.findById(23).orElse(null);
            assert producto2 != null;
            compra.addDetalleCompra(new DetalleCompra(0,compra
                    ,producto2
                    ,cantidad
                    ,producto2.getPrecioUnidad()* cantidad));

            if( compraService.save(compra)>0){
                System.out.println("Se ha producido un compra con exito");
            }



            //update
            if(compraService.update(
                    new Compra(12,
                            new Date(22,11,05),
                            null,100))>0){
                System.out.println("\t\t\tCliente actualizado exitosamente");
            }else{
                System.out.println("Error actualizando");
            }

            //delete
            if(compraService.delete(1)>0){
                System.out.println("\t\t\tCliente eliminado exitosamente");
            }else{
                System.out.println("\t\t\tError eliminando");
            }

            //findById
            if(compraService.findById(2).isPresent()){
                System.out.println("\t\t\tCliente buscando exitosamente");
            }else{
                System.out.println("Error buscando");
            }

            //findAll
            if(compraService.findAll()!=null) {
                compraService.findAll().forEach(System.out::println);
            }else{
                System.out.println("Error listando");
            }

            //saveAndFindId
            if(compraService.save(new Compra(0,
                    new Date(20,16,03),
                    proveedorService.findById(23).orElse(null),120))>0){
                System.out.println("\t\t\t Cliente guardado exitosamente");
            }else{
                System.out.println("Error guardando");
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void testearConsumoLote(){
        AnimalServiceImpl animalService= new AnimalServiceImpl();
        LoteAnimalServiceImpl loteService= new LoteAnimalServiceImpl();
        ProductoServiceImpl productoService= new ProductoServiceImpl();
        ConsumoServiceImpl consumoService= new ConsumoServiceImpl();
        Connection conexion=null;
        try {
            conexion=  ConexionMySQL.getConexionMySQL();
            if(conexion==null){
                System.out.println("\t\t\tNo se ha establecido conexion");
            }else{
                System.out.println("\t\t\tConexion establecida");
            }

            //save
            LoteAnimal lote = loteService.findById(22).orElse(null);
            Producto producto= productoService.findById(23).orElse(null);
            int cantidad= 10;
            ConsumoLote consumo = new ConsumoLote();
            consumo.setIdConsumo(0);
            consumo.setFecha(Date.valueOf(LocalDate.now()));
            consumo.setLote(lote);
            consumo.setCantidad(cantidad);
            consumo.setProducto(producto);

            assert lote != null;
            lote.addConsumos(consumo);
            if(consumoService.save(consumo)>0){
                System.out.println("\t\t\tConsumo guardado exitosamente");
            }else{
                System.out.println("Error guardando");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void testearPeidos(){
        PedidoServiceImpl pedidoService= new PedidoServiceImpl();
        ProductoServiceImpl productoService= new ProductoServiceImpl();
        UsuarioServiceImpl usuarioService= new UsuarioServiceImpl();
        Connection conn=null;
        try{
            conn=ConexionMySQL.getConexionMySQL();
            if(conn==null){
                System.out.println("\t\t\tNo se ha establecido conexion");
            }else{
                System.out.println("\t\t\tConexion establecida");
            }

            //save
            Usuario usuario= usuarioService.findById(2).orElse(null);
            Pedido pedido = new Pedido();
            pedido.setUsuario(usuario);
            pedido.setEstado("Entregado");
            pedido.setFecha(Date.valueOf(LocalDate.now()));

            Producto producto = productoService.findById(22).orElse(null);
            DetallePedido detalle= new DetallePedido();
            detalle.setCantidad(1);
            detalle.setPedido(pedido);
            detalle.setProducto(producto);

            pedido.addDetallePedido(detalle);

            if(pedidoService.save(pedido)>0){
                System.out.println("pedido guardado exitosamente");
            }else{
                System.out.println("Error guardando");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void testearVentas(){
        VentaServiceImpl ventaService= new VentaServiceImpl();

        UsuarioServiceImpl usuarioService= new UsuarioServiceImpl();
        ProductoServiceImpl productoService= new ProductoServiceImpl();
        DetalleVentaServiceImpl detalleVentaService= new DetalleVentaServiceImpl();
        Connection conn=null;

        try{
            conn=ConexionMySQL.getConexionMySQL();
            if(conn==null){
                System.out.println("\t\t\tNo se ha establecido conexion");
            }else{
                System.out.println("\t\t\tConexion establecida");
            }
            // save
            Usuario usuario= usuarioService.findById(10).orElse(null);
            Producto producto=productoService.findById(49).orElse(null);


            Venta venta= new Venta();
            venta.setUsuario(usuario);
            venta.setFecha(Date.valueOf(LocalDate.now()));

            DetalleVenta detalleVenta= new DetalleVenta();
            int cantidad=10;
            detalleVenta.setCantidad(cantidad);
            detalleVenta.setProducto(producto);
            detalleVenta.setVenta(venta);
            assert producto != null;
            detalleVenta.setSubtotal(producto.getPrecioUnidad()* cantidad);

            venta.addDetalleVenta(detalleVenta);

            DetalleVenta detalleVenta1= new DetalleVenta();
            Producto producto1= productoService.findById(1).orElse(null);
            detalleVenta1.setCantidad(cantidad);
            detalleVenta1.setProducto(producto1);
            detalleVenta1.setVenta(venta);
            assert producto1 != null;
            detalleVenta1.setSubtotal(producto1.getPrecioUnidad()* cantidad);
            venta.addDetalleVenta(detalleVenta1);
           if(ventaService.save(venta)>0){
               System.out.println("Venta guardado exitosamente");
           }else{
               System.out.println("Error guardando");
           }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void testearLoteAnimal(){
        AnimalServiceImpl animalService= new AnimalServiceImpl();
        LoteAnimalServiceImpl loteAnimalService= new LoteAnimalServiceImpl();

        Connection conn=null;
        try{
            conn=ConexionMySQL.getConexionMySQL();
            if(conn==null){
                System.out.println("\t\t\tNo se ha establecido conexion");

            }else{
                System.out.println("\t\t\tConexion establecida");
            }
         /*   // save
            LoteAnimal loteAnimal= new LoteAnimal();
            Animal animal= animalService.findById(2).orElse(null);
            loteAnimal.setAnimal(animal);
            loteAnimal.setFechaInicio(Date.valueOf(LocalDate.now()));
            loteAnimal.setCantidadInicio(100);
            loteAnimal.setCantidadActual(100);
            loteAnimal.setEstadoLote("Entreda");
            loteAnimal.setPesoPromedio(0.0);
           if(loteAnimalService.save(loteAnimal)>0){
               System.out.println("Lote animal guardado exitosamente");
           }else{
               System.out.println("Error guardando");
           }*/

           //update
            LoteAnimal loteAnimal1= loteAnimalService.findById(41).orElse(null);
           loteAnimal1.setPesoPromedio(12.0);
           loteAnimal1.setEstadoLote("Desarrollo");
           loteAnimal1.setCantidadActual(80);
           if(loteAnimalService.update(loteAnimal1)>0){
               System.out.println("Lote animal actualizado xitosamente");
           }else{
               System.out.println("Error actualizando");
           }

           //findAll
            loteAnimalService.findAll().forEach(
                    loteAnimal -> {
                        System.out.println("\t\t "+ loteAnimal);
                    }
            );


        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void testearMovimiento(){
        MovimientoServiceImpl movimientoService= new MovimientoServiceImpl();
        ProductoServiceImpl productoService= new ProductoServiceImpl();

        Connection conn=null;
        try{
            conn=ConexionMySQL.getConexionMySQL();
            if(conn==null){
                System.out.println("\t\t\tNo se ha establecido conexion");
            }else{
                System.out.println("\t\t\tConexion establecida");
            }
            //save
            MovimientoAlmacen movimientoAlmacen= new MovimientoAlmacen();
            Producto producto= productoService.findById(49).orElse(null);
            movimientoAlmacen.setProducto(producto);
            movimientoAlmacen.setCantidad(10);
            movimientoAlmacen.setTipoMovimiento("Salida");
            movimientoAlmacen.setFecha(Date.valueOf(LocalDate.now()));
            movimientoAlmacen.setContexto("consumo interno vacunacion de las gallinas");

            if(movimientoService.save(movimientoAlmacen)>0){
                System.out.println("Movimiento guardado xitosamente");
            }else{
                System.out.println("Error actualizando");
            }



        }catch (Exception e){
            e.printStackTrace();
        }

    }
}



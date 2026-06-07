package TEST;

import java.sql.Date;
import java.util.List;

import Aplicacion.ServiceImpl.AnimalServiceImpl;
import Dominio.Modelo.Animal;
import Dominio.Modelo.Compra;
import Dominio.Service.AnimalService;

public class TesteoDePrograma {

    public static void main(String[] args) {
       AnimalService servicioPruebaSQL = new AnimalServiceImpl();
       List<Animal> animales= servicioPruebaSQL.obtenerTodosLosAnimales();
       for(Animal a: animales){
           System.out.println("\t\t\t\t\t"+a.getIdAnimal());
           System.out.println("\t\t\t\t\t"+a.getRaza());
           System.out.println("\t\t\t\t\t"+a.getEspecie());
       }
       /*
        Animal animal = servicioPruebaSQL.obtenerAnimalPorId(10L);
        System.out.println("\t\t\t\t\t" + animal.getIdAnimal());
        System.out.println("\t\t\t\t\t" + animal.getRaza());
        System.out.println("\t\t\t\t\t" + animal.getEspecie());*/

      /*  Animal animalAguardar= new Animal("ESPECIE","RAZA");
        Animal animal1=servicioPruebaSQL.guardarAnimal(animalAguardar);
        System.out.println("\t\t\t\t\t" + animal1.getIdAnimal());
        System.out.println("\t\t\t\t\t" + animal1.getRaza());
        System.out.println("\t\t\t\t\t" + animal1.getEspecie());
*/
        /*
         * for(Animal a: AnimalTest.traerPorConsumo()){
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t"+a.getIdAnimal());
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t"+a.getRaza());
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t"+a.getEspecie());
         * }
         */

        /*
         * Cliente c=ClienteTest.buscarPorId(11);
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t"+c.getIdCliente());
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t"+c.getNombre());
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t"+c.getApellido());
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t"+c.getDireccion());
         * 
         * }
         */
        /*
         * List<Cliente> clientes = ClienteTest.Todos();
         * for (Cliente c : clientes) {
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t" + c.getIdCliente());
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t" + c.getNombre());
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t" + c.getApellido());
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t" + c.getDireccion());
         * }
         * }
         */
        /*
         * ProveedorRepository provedor= new ProveedorRepositoryImpl();
         * Proveedor p=provedor.buscarPorId(1);
         * java.util.Date fecha= new java.util.Date();
         * Date fechaSql= new Date(fecha.getTime());
         * Compra compra= new Compra(1,fechaSql, p,0.0);
         * 
         * ProductoRepository productoRepository= new ProductoRepositoryImpl();
         * List<Producto> productos= new ArrayList<>();
         * productos.add(productoRepository.buscarPorId(1));
         * productos.add(productoRepository.buscarPorId(2));
         * productos.add(productoRepository.buscarPorId(4));
         * productos.add(productoRepository.buscarPorId(5));
         * List<Integer> cantidades= new ArrayList<>();
         * for(Producto producto:productos){
         * cantidades.add(100);
         * }
         * 
         * DetalleCompra detalleCompra = new DetalleCompra(1,
         * compra,productos,cantidades,0.0
         * 
         * );
         * Compra c=CompraTest.nuevaCompra(detalleCompra);
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t" +c.getProveedor().getNombre());
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t" + c.getFecha());
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t" + c.getTotal());
         */
        /*
         * List<Compra> compras = CompraTest.todasCompras();
         * for(Compra c : compras){
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t" +c.getProveedor().getNombre());
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t" + c.getFecha());
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t" + c.getTotal());
         * 
         * }
         * 
         */
        /*
         * List<Compra> comprasMasAltos = CompraTest.comprasMasAltos();
         * for(Compra c: comprasMasAltos){
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t" +c.getProveedor().getNombre());
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t" + c.getFecha());
         * System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t" + c.getTotal());
         * 
         * }
         * 
         */

     /*   List<Compra> comprasMasAltos = CompraTest.comprasPorFecha(
                new Date(122, 05, 21), new Date(126, 4, 28));
        for (Compra c : comprasMasAltos) {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t" + c.getProveedor().getNombre());
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t" + c.getFecha());
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t" + c.getTotal());
        }*/
    }
}